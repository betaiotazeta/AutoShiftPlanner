package org.betaiotazeta.autoshiftplanner;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

/**
 *
 * @author betaiotazeta
 */
@PlanningEntity
public class ShiftAssignment {

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @PlanningVariable(valueRangeProviderRefs = {"timeGrainRange"}, nullable = true)
    public TimeGrain getTimeGrain() {
        return timeGrain;
    }

    public void setTimeGrain(TimeGrain timeGrain) {
        this.timeGrain = timeGrain;
    }

    @PlanningVariable(valueRangeProviderRefs = {"shiftDurationRange"}, nullable = true)
    public ShiftDuration getShiftDuration() {
        return shiftDuration;
    }

    public void setShiftDuration(ShiftDuration shiftDuration) {
        this.shiftDuration = shiftDuration;
    }

    @Override
    public String toString() {
        return "s" + shift.getShiftIndex() + "(" + shift.getEmployee().getName() + ")";
    }
    
    private Shift shift;
    private TimeGrain timeGrain;
    private ShiftDuration shiftDuration;   
}