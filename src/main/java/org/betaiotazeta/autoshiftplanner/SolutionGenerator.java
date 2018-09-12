package org.betaiotazeta.autoshiftplanner;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author betaiotazeta
 */
public class SolutionGenerator {

    public SolutionGenerator(AspApp aspApp) {
        this.aspApp = aspApp;
    }

    public Solution createSolution() {

        aspApp.updateConfiguratorFromGui();

        // A solution object is created
        Solution solution = new Solution();
        solution.setBusiness(aspApp.getBusiness());
        solution.setConfigurator(aspApp.getConfigurator());
        solution.setAspApp(aspApp);
        aspApp.setSolution(solution);

        // Cloning table and staff for parallel benchmarking
        // cell cloning
        Table table = aspApp.getTable();
        Business business = aspApp.getBusiness();
        int nR = table.getNumberOfRows();
        int nC = table.getnumberOfColumns();

        Table tableScore = new Table(nR, nC, business);

        // attention: values of nR and nC start at 0
        for (int i = 0; i < nR; i++) {
            for (int j = 0; j < nC; j++) {
                Cell cell = table.getCell(i, j);
                Cell cellScore = cell.clone();
                tableScore.setCell(i, j, cellScore);
            }
        }
        solution.setTableScore(tableScore);

        // employees cloning
        ArrayList<Employee> staff = aspApp.getStaff();
        ArrayList<Employee> staffScore = new ArrayList<Employee>(staff.size());
        for (Employee employee : staff) {
            Employee employeeScore = employee.clone();
            staffScore.add(employeeScore);
        }
        solution.setStaffScore(staffScore);

        // Bonus if all the period is denied (reducing unnecessary punishment)
        // in order to avoid calculations in easyscorecalculator
        // The period represents the set of all the cells belonging to the
        // various employees for a period of time that is half an hour long.
        // See Cell class
        // E.g.: from 8 to 8.30.
        int bonus = 0;
        short numberOfPeriods = tableScore.getCell(nR - 1, nC - 1).getIdPeriod();
        Map<Short, Byte> periodUsageMap = new HashMap<>(numberOfPeriods);
        for (short idPeriod = 1; idPeriod <= numberOfPeriods; idPeriod++) {
            periodUsageMap.put(idPeriod, (byte) 0);
        }
        for (int i = 0; i < nR; i++) {
            for (int j = 0; j < nC; j++) {
                if (tableScore.getCell(i, j).isForbidden()) {
                    Short idPeriod = tableScore.getCell(i, j).getIdPeriod();
                    Byte value = periodUsageMap.get(idPeriod);
                    value++;
                    periodUsageMap.put(idPeriod, value);
                }
            }
        }
        for (Map.Entry<Short, Byte> usageEntry : periodUsageMap.entrySet()) {
            // Short idPeriod = usageEntry.getKey();
            Byte value = usageEntry.getValue();
            if (value == aspApp.getStaff().size()) {
                bonus = bonus + aspApp.getConfigurator().getEmployeesPerPeriod();
            }
        }
        solution.setBonus(bonus);

        createTimeGrainList(solution);
        createShiftDurationList(solution);
        createShiftAssignmentList(solution);
        aspApp.convertTableIntoShifts();

        return solution;
    }

    private void createShiftDurationList(Solution solution) {
        double shiftLenghtMin = solution.getConfigurator().getShiftLenghtMin();
        double shiftLenghtMax = solution.getConfigurator().getShiftLenghtMax();
        int shiftDurationListSize = (int) Math.round(shiftLenghtMax - shiftLenghtMin) * 2; // *2 in half hours

        List<ShiftDuration> shiftDurationList = new ArrayList<>(shiftDurationListSize);

        for (int i = 0; i <= shiftDurationListSize; i++) {
            ShiftDuration shiftDuration = new ShiftDuration();
            shiftDuration.setId((long) i);
            int durationIndex = i;
            shiftDuration.setDurationIndex(durationIndex);
            int durationInMinutes = (int) ((shiftLenghtMin * 60) + (i * 30)); // *30 in half hours
            shiftDuration.setDurationInMinutes(durationInMinutes);
            int durationInGrains = (int) ((shiftLenghtMin * 2) + i); // *2 in half hours
            shiftDuration.setDurationInGrains(durationInGrains);
            shiftDurationList.add(shiftDuration);
        }
        solution.setShiftDurationList(shiftDurationList);
    }

    private void createTimeGrainList(Solution solution) {
        double startTime = solution.getBusiness().getStartTime();
        double endTime = solution.getBusiness().getEndTime();
        int numberOfEmployees = solution.getBusiness().getNumberOfEmployees();
        int columns = solution.getTableScore().getnumberOfColumns();

        List<Day> dayList = new ArrayList<>(7); // 7 days per week

        int timeGrainListSize = (int) (endTime - startTime) * numberOfEmployees * 7 * 2; // *2 in half hours
        List<TimeGrain> timeGrainList = new ArrayList<>(timeGrainListSize);

        long dayId = 0;
        int dayOfWeek = 0;

        long grainId = 0;
        int grainIndex = 0;

        for (int i = 0; i < 7; i++) { // 7 days per week
            Day day = new Day();
            day.setId(dayId);
            day.setDayOfWeek(dayOfWeek);
            dayList.add(day);
            dayId++;
            dayOfWeek++;
            int startingMinuteOfDay = (int) (startTime * 60); // *60 in minutes;
            int startingGrainOfDay = 0;
            for (int j = 0; j < columns; j++) { // every column requires a new timeGrain
                TimeGrain timeGrain = new TimeGrain();
                timeGrain.setId(grainId);
                timeGrain.setGrainIndex(grainIndex);
                timeGrain.setDay(day);
                timeGrain.setStartingMinuteOfDay(startingMinuteOfDay);
                timeGrain.setStartingGrainOfDay(startingGrainOfDay);
                timeGrainList.add(timeGrain);
                grainId++;
                grainIndex++;
                startingMinuteOfDay = startingMinuteOfDay + 30; // 30 for half hours
                startingGrainOfDay = startingGrainOfDay + 1;
            }
        }
        solution.setDayList(dayList);
        solution.setTimeGrainList(timeGrainList);
    }

    private void createShiftAssignmentList(Solution solution) {
        // Do we really need Shift AND ShiftAssignment? Why? Easier for implementing drools?
        int numberOfEmployees = solution.getBusiness().getNumberOfEmployees();
        int shiftsPerDay = solution.getConfigurator().getShiftsPerDay();
        int shiftIndex = 0;

        int shiftListSize = numberOfEmployees * shiftsPerDay * 7;
        List<Shift> shiftList = new ArrayList<>(shiftListSize);

        ArrayList<Employee> staff = solution.getStaffScore();
        for (Employee employee : staff) {
            for (int i = 0; i < (shiftsPerDay * 7); i++) {
                Shift shift = new Shift();
                shift.setShiftIndex(shiftIndex);
                shift.setEmployee(employee);
                shiftList.add(shift);
                shiftIndex++;
            }
        }

        int shiftAssignmentListSize = shiftListSize; // Do we really need two classes?
        List<ShiftAssignment> shiftAssignmentList = new ArrayList<>(shiftAssignmentListSize);

        for (Shift shift : shiftList) {
            ShiftAssignment shiftAssignment = new ShiftAssignment();
            shiftAssignment.setShift(shift);
            shiftAssignmentList.add(shiftAssignment);
        }
        solution.setShiftList(shiftList);
        solution.setShiftAssignmentList(shiftAssignmentList);
    }

    private AspApp aspApp;
}