package org.betaiotazeta.autoshiftplanner;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.heuristic.selector.move.generic.ChangeMove;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 *
 * @author betaiotazeta
 */
public class ForbiddenCellSelectionFilter implements SelectionFilter<Solution, ChangeMove> {

    @Override // short version: only checking the timeGrain
    public boolean accept(ScoreDirector<Solution> scoreDirector, ChangeMove changeMove) {
        Object object = changeMove.getToPlanningValue();
        if (object != null) {
            if ("TimeGrain".equals(object.getClass().getSimpleName())) {
                Solution solution = scoreDirector.getWorkingSolution();
                ShiftAssignment shiftAssignment = (ShiftAssignment) changeMove.getEntity();
                TimeGrain timeGrain = (TimeGrain) object;
                int startingGrainOfDay = timeGrain.getStartingGrainOfDay();
                int dayOfWeek = timeGrain.getDay().getDayOfWeek();
                int indexOfEmployee = solution.getStaffScore().indexOf(shiftAssignment.getShift().getEmployee());
                int row = indexOfEmployee + (dayOfWeek * solution.getBusiness().getNumberOfEmployees());
                if (solution.getTableScore().getCell(row, startingGrainOfDay).isForbidden()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /*
    @Override // long version: checking the timeGrain and shiftDuration (probably slower)
    public boolean accept(ScoreDirector<Solution> scoreDirector, ChangeMove changeMove) {
        Object object = changeMove.getToPlanningValue();
        if (object != null) {
            Solution solution = scoreDirector.getWorkingSolution();
            ShiftAssignment shiftAssignment = (ShiftAssignment) changeMove.getEntity();
            String className = object.getClass().getSimpleName();
            if ("TimeGrain".equals(className)) {
                TimeGrain timeGrain = (TimeGrain) object;
                int startingGrainOfDay = timeGrain.getStartingGrainOfDay();
                int dayOfWeek = timeGrain.getDay().getDayOfWeek();
                int indexOfEmployee = solution.getStaffScore().indexOf(shiftAssignment.getShift().getEmployee());
                int i = indexOfEmployee + (dayOfWeek * solution.getBusiness().getNumberOfEmployees());
                int durationInGrains = 1;
                if (shiftAssignment.getShiftDuration() != null) {
                    durationInGrains = shiftAssignment.getShiftDuration().getDurationInGrains();
                }
                int finalGrainOfDay = startingGrainOfDay + durationInGrains;
                if (finalGrainOfDay >= solution.getTableScore().getnumberOfColumns()) {
                    int overflow = finalGrainOfDay - solution.getTableScore().getnumberOfColumns();
                    finalGrainOfDay = finalGrainOfDay - overflow;
                }
                for (int j = startingGrainOfDay; j < finalGrainOfDay; j++) {
                    if (solution.getTableScore().getCell(i, j).isForbidden()) {
                        return false;
                        // System.out.println("Intercepting move in filter");
                    }
                }
            } else {
                TimeGrain timeGrain = shiftAssignment.getTimeGrain();
                if (timeGrain != null) {
                    ShiftDuration shiftDuration = (ShiftDuration) object;
                    int startingGrainOfDay = timeGrain.getStartingGrainOfDay();
                    int dayOfWeek = timeGrain.getDay().getDayOfWeek();
                    int indexOfEmployee = solution.getStaffScore().indexOf(shiftAssignment.getShift().getEmployee());
                    int i = indexOfEmployee + (dayOfWeek * solution.getBusiness().getNumberOfEmployees());
                    int durationInGrains = shiftDuration.getDurationInGrains();
                    int finalGrainOfDay = startingGrainOfDay + durationInGrains;
                    if (finalGrainOfDay >= solution.getTableScore().getnumberOfColumns()) {
                        int overflow = finalGrainOfDay - solution.getTableScore().getnumberOfColumns();
                        finalGrainOfDay = finalGrainOfDay - overflow;
                    }
                    for (int j = startingGrainOfDay; j < finalGrainOfDay; j++) {
                        if (solution.getTableScore().getCell(i, j).isForbidden()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    */
}
