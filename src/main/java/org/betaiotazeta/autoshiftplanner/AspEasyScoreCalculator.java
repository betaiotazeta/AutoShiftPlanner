package org.betaiotazeta.autoshiftplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

/**
 *
 * @author betaiotazeta
 */
public class AspEasyScoreCalculator implements EasyScoreCalculator<Solution> {

    @Override
    public HardSoftScore calculateScore(Solution solution) {
        
        // Values are in half-hour units of time
        Configurator configurator = solution.getConfigurator();
        int shiftsPerDay = (configurator.getShiftsPerDay() * 2);
        int breakLenght = (int) (configurator.getBreakLenght() * 2);
        int shiftLenghtMin = (int) (configurator.getShiftLenghtMin() * 2);
        int shiftLenghtMax = (int) (configurator.getShiftLenghtMax() * 2);
        int employeesPerPeriod = configurator.getEmployeesPerPeriod();
        int hoursPerDay = (int) (configurator.getHoursPerDay() * 2);
        int overnightRest = (int) (configurator.getOvernightRest() * 60);
        
        Table tableScore = solution.getTableScore();
        int nR = tableScore.getNumberOfRows();
        int nC = tableScore.getnumberOfColumns();
        ArrayList<Employee> staffScore = solution.getStaffScore();
        List<ShiftAssignment> shiftAssignmentList = solution.getShiftAssignmentList();
        int numberOfEmployees = staffScore.size();
        Employee employee;
                
        int hardScore = 0;
        int softScore = 0;
        
        // reset the tableScore
        for (int i = 0; i < nR; i++) {
            for (int j = 0; j < nC; j++) {
                tableScore.getCell(i, j).setWorked(false);
            }
        }
        
        // convert shifts into tableScore
        for (ShiftAssignment shiftAssignment : shiftAssignmentList) {
            if ((shiftAssignment.getTimeGrain() != null) && (shiftAssignment.getShiftDuration() != null)) {
                int startingGrainOfDay = shiftAssignment.getTimeGrain().getStartingGrainOfDay();
                int dayOfWeek = shiftAssignment.getTimeGrain().getDay().getDayOfWeek();
                int durationInGrains = shiftAssignment.getShiftDuration().getDurationInGrains();
                int indexOfEmployee = staffScore.indexOf(shiftAssignment.getShift().getEmployee());
                int i = indexOfEmployee + (dayOfWeek * numberOfEmployees);
                int finalGrainOfDay = startingGrainOfDay + durationInGrains;
                if (finalGrainOfDay >= nC) {
                    int overflow = finalGrainOfDay - nC;
                    finalGrainOfDay = finalGrainOfDay - overflow;
                    hardScore = hardScore - overflow;
                }
                for (int j = startingGrainOfDay; j < finalGrainOfDay; j++) {
                    /*
                    if (tableScore.getCell(i, j).isWorked()) {
                        // allow shifts to overlap may help during solving!
                        hardScore = hardScore - 1; // overlapping shifts
                    }
                    */
                    tableScore.getCell(i, j).setWorked(true);
                }
            }
        }
                
        // reset of all hours performed for everyone
        for (int i = 0; i < numberOfEmployees; i++) {
            employee = staffScore.get(i);
            employee.setHoursWorked(0);
        }

        // update the hours worked for all employees from the data in the tableScore
        // attention: values of nR and nC start at 0
        for (int i = 0; i < nR; i++) {
            for (int j = 0; j < nC; j++) {
                boolean status = tableScore.getCell(i, j).isWorked();
                if (status) {
                    byte idDip = tableScore.getCell(i, j).getIdEmployee();
                    // IdDip count starts at 1.
                    // Position in arrayList starts at 0. Subtracting 1.
                    employee = staffScore.get(idDip - 1);
                    employee.setHoursWorked(employee.getHoursWorked() + 0.5);
                }
            }
        }        
        
        /* ----- SCORE ----- */
        
        // score for all employees to do the required number of hours per week      
        if (configurator.isHoursPerWeekCheck()) {
            for (int i = 0; i < numberOfEmployees; i++) {
                employee = staffScore.get(i);
                int halfHoursWorked = (int) (employee.getHoursWorked() * 2);
                int halfHoursPerWeek = employee.getHoursPerWeek() * 2;
                hardScore = hardScore - Math.abs(halfHoursPerWeek - halfHoursWorked);
            }
        }
        
        // score so that each employee does not exceed the number of shifts per day
        if (configurator.isShiftsPerDayCheck()) {
            for (int i = 0; i < nR; i++) {
                int k = 0;
                for (int j = 1; j < nC; j++) {
                    boolean status1 = tableScore.getCell(i, j - 1).isWorked();
                    if ((j == 1) && (status1)) {
                        k = k + 1;
                    }
                    boolean status2 = tableScore.getCell(i, j).isWorked();
                    if (status1 != status2) {
                        k = k + 1;
                    }
                    if ((j == nC - 1) && (k > shiftsPerDay)) {
                        hardScore = hardScore - k;
                        k = 0;
                    }
                }
            }
        }

        // score so that every employee has some eventual breaks of the desired break duration
        // k indicates the lenght of the break in half-hour units
        if (configurator.isBreakLenghtCheck()) {
            for (int i = 0; i < nR; i++) {
                boolean status1 = false;
                boolean status2 = false;
                boolean status3 = false;
                int k = 0;
                for (int j = 0; j < nC; j++) {
                    status1 = tableScore.getCell(i, j).isWorked();
                    // follows the cycle of hours worked
                    while (status1) {
                        status2 = true;
                        j = j + 1;
                        if (j < nC) {
                            status1 = tableScore.getCell(i, j).isWorked();
                        } else {
                            status1 = false;
                            status2 = false;
                        }
                    }
                    if (status2) {
                        // follows the pause cycle
                        // punishment if (s)he resumes work with a different break duration
                        while (!status1) {
                            status3 = true;
                            k = k + 1; // k = half-hours units during the break
                            j = j + 1;
                            if (j < nC) {
                                status1 = tableScore.getCell(i, j).isWorked();
                            } else {
                                status1 = true;
                                status3 = false; // only if (s)he resumes must be checked
                            }
                        }
                    }
                    if (status3) {
                        // follows the punishment cycle
                        if (k != breakLenght) {
                            hardScore = hardScore - Math.abs(k - breakLenght);
                        }
                        k = 0;
                        j = j - 1;
                        status2 = false;
                        status3 = false;
                    }
                }
            }
        }
        
        // score for each employee to work the required number of consecutive hours
        if (configurator.isShiftLenghtCheck()) {
            for (int i = 0; i < nR; i++) {
                for (int j = 0; j < nC; j++) {
                    boolean status = false;
                    int k = 0;
                    status = tableScore.getCell(i, j).isWorked();
                    while (status) {
                        k = k + 1;
                        j = j + 1;
                        if (j < nC) {
                            status = tableScore.getCell(i, j).isWorked();
                        } else {
                            status = false;
                        }
                    }                    
                    if (k == 0) {
                        // do nothing
                    }
                    else if (k < shiftLenghtMin) {
                        hardScore = hardScore - Math.abs(k - shiftLenghtMin);
                    }                    
                    else if (k > shiftLenghtMax) {
                        hardScore = hardScore - (k - shiftLenghtMax);
                    }                    
                    k = 0; // here value of k should be zero, ready for next loop
                }
            }
        }

        // score so that there is the required number of employees per period
        if (configurator.isEmployeesPerPeriodCheck()) {
            int k = 0;
            for (int j = 0; j < nC; j++) {
                int i = 0;
                int m = 0;
                while (i < nR) {
                    while (m < numberOfEmployees) {
                        if (tableScore.getCell(i + m, j).isWorked()) {
                            k = k + 1;
                        }
                        m = m + 1;
                    }
                    if (k < employeesPerPeriod) {
                        hardScore = hardScore - Math.abs(k - employeesPerPeriod);
                    }
                    k = 0;
                    m = 0;
                    i = i + numberOfEmployees;
                }
            }
            hardScore = hardScore + solution.getBonus();
        }
        
        // score so that each employee does not exceed the expected daily hours
        if (configurator.isHoursPerDayCheck()) {
            int k = 0;
            for (int i = 0; i < nR; i++) {
                boolean status;
                if (k > hoursPerDay) {
                    hardScore = hardScore - (k - hoursPerDay) * 2; // punishment augmented by two
                }
                k = 0;
                for (int j = 0; j < nC; j++) {
                    status = tableScore.getCell(i, j).isWorked();
                    if (status) {
                        k = k + 1;
                    }
                }
            }
            if (k > hoursPerDay) {
                hardScore = hardScore - (k - hoursPerDay) * 2; // punishment augmented by two
            }
            k = 0;
        }

        // score for all the mandatory cells to be worked
        if (configurator.isMandatoryShiftsCheck()) {
            for (int i = 0; i < nR; i++) {
                for (int j = 0; j < nC; j++) {
                    Cell cell = tableScore.getCell(i, j);
                    if (cell.isMandatory() && !cell.isWorked()) {
                        hardScore = hardScore - 10;
                    }
                }
            }
        }

        // score for all the forbidden cells not to be worked
        // This will be run anyway because a cell in gui cannot be forbidden and
        // also worked or mandatory at the same time.
        for (int i = 0; i < nR; i++) {
            for (int j = 0; j < nC; j++) {
                Cell cell = tableScore.getCell(i, j);
                if (cell.isForbidden() && cell.isWorked()) {
                    hardScore = hardScore - 10;
                    // System.out.println("Punishing extra filter");
                }
            }
        }

        // score for minimum amount of overnight rest
        if (configurator.isOvernightRestCheck()) {
            for (int indexOfEmployee = 0; indexOfEmployee < numberOfEmployees; indexOfEmployee++) {
                int firstWorkedCellMinute = 0;
                int lastWorkedCellMinute = 0;
                for (int i = indexOfEmployee; i < nR; i = i + numberOfEmployees) {
                    boolean flag = false;
                    for (int j = 0; j < nC; j++) {
                        Cell cell = tableScore.getCell(i, j);
                        if (cell.isWorked()) {
                            j = nC;
                            firstWorkedCellMinute = cell.getStartingMinuteOfDay();
                            flag = true;
                        }
                    }
                    if (flag == true) {
                        // 1440: means midnight in minutes
                        int calculatedRest = (1440 - lastWorkedCellMinute) + firstWorkedCellMinute;
                        if (calculatedRest < overnightRest) {
                            // punishment in "quantity of missing cells" 
                            hardScore = hardScore - ((720 - calculatedRest) / 30);
                        }
                        for (int j = (nC - 1); j > -1; j--) {
                            Cell cell = tableScore.getCell(i, j);
                            if (cell.isWorked()) {
                                j = -1;
                                // +30: adding the duration of the last cell in minutes
                                lastWorkedCellMinute = cell.getStartingMinuteOfDay() + 30;
                            }
                        }
                    } else {
                        lastWorkedCellMinute = 0;
                    }
                }
            }
        }
        
        // score so that employees are evenly distributed
        if (configurator.isUniformEmployeesDistributionCheck()) {
            short numberOfPeriods = tableScore.getCell(nR - 1, nC - 1).getIdPeriod();
            Map<Short, Byte> periodUsageMap = new HashMap<>(numberOfPeriods);
            for (short idPeriod = 1; idPeriod <= numberOfPeriods; idPeriod++) {
                periodUsageMap.put(idPeriod, (byte) 0);
            }
            for (int i = 0; i < nR; i++) {
                for (int j = 0; j < nC; j++) {
                    if (tableScore.getCell(i, j).isWorked()) {
                        Short idPeriod = tableScore.getCell(i, j).getIdPeriod();
                        Byte value = periodUsageMap.get(idPeriod);
                        value++;
                        periodUsageMap.put(idPeriod, value);
                    }
                }
            }
            for (Map.Entry<Short, Byte> usageEntry : periodUsageMap.entrySet()) {
                Byte value = usageEntry.getValue();
                softScore = softScore - (value * value);
            }
        }

        return HardSoftScore.valueOf(hardScore, softScore);
    }
}
