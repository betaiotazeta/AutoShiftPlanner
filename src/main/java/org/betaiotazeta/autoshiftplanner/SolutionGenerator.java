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
        
        // Cloning table and staff for parallel benchmarking
        // cell cloning
        Table table = aspApp.getTable();
        int nR = table.getNumberOfRows();
        int nC = table.getnumberOfColumns();

        Table tableScore = new Table(nR, nC);
       
        // attention: values of nR and nC start at 0
        for (int i = 0; i < nR; i++) {
            for (int j = 0; j < nC; j++) {
                Cell cell = table.getCell(i, j);
                Cell cellScore = cell.clone();
                if (cellScore.getWorked() == false && !cellScore.isForbidden()) {
                    cellScore.setWorked(null);
                }
                tableScore.setCell(i, j, cellScore);
            }
        }

        // employees cloning
        ArrayList<Employee> staff = aspApp.getStaff();
        ArrayList<Employee> staffScore = new ArrayList<Employee>(staff.size());
        for (Employee employee : staff) {
            Employee employeeScore = employee.clone();
            staffScore.add(employeeScore);
        }        

        // A solution object is created
        Solution solution = new Solution();
        
        // Inserts the cells from the tableScore into the cellList arrayList
        List<Cell> cellList = new ArrayList<>();
        // attention: values of nR and nC start at 0
        for (int i = 0; i < nR; i++) {
            for (int j = 0; j < nC; j++) {                
                cellList.add(tableScore.getCell(i, j));
            }        
        }
        
        // Creation of an arrayList containing two objects of type Boolean
        List<Boolean> booleanList = new ArrayList<>();
        Boolean planningVariableValueTrue = true;
        Boolean planningVariableValueFalse = false;
        booleanList.add(planningVariableValueTrue);
        booleanList.add(planningVariableValueFalse);

                
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
      
        solution.setCellList(cellList);
        solution.setBooleanList(booleanList);
        solution.setStaff(staffScore);
        solution.setTable(tableScore);
        solution.setBusiness(aspApp.getBusiness());
        solution.setConfigurator(aspApp.getConfigurator());
        solution.setBonus(bonus);
        solution.setAspApp(aspApp);
        aspApp.setSolution(solution);
        
        return solution;
    }
    
    private AspApp aspApp;    
}