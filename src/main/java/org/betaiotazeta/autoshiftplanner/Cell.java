package org.betaiotazeta.autoshiftplanner;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

/**
 *
 * @author betaiotazeta
 */

@PlanningEntity(movableEntitySelectionFilter = MovableCellSelectionFilter.class)
public class Cell implements Cloneable {
    
    // It seems that Optaplanner requires a no param constructor for cloning.
    public Cell() {        
    }
   
    public Cell(byte idEmployee, short idPeriod, Boolean worked, boolean mandatory, boolean forbidden) {
        this.idEmployee = idEmployee;
        this.idPeriod = idPeriod;
        this.worked = worked;
        this.mandatory = mandatory;
        this.forbidden = forbidden;
    }


    public byte getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(byte idEmployee) {
        this.idEmployee = idEmployee;
    }

    public short getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(short idPeriod) {
        this.idPeriod = idPeriod;
    }

    @PlanningVariable(valueRangeProviderRefs = {"booleanRange"})
    public Boolean getWorked() {
        return worked;
    }

    public void setWorked(Boolean worked) {
        this.worked = worked;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isForbidden() {
        return forbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }

    @Override
    public Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }    

    private byte idEmployee;
    // The period represents the set of all the cells belonging to the
    // various employees for a period of time that is half an hour long.
    // E.g.: from 8 to 8.30.
    private short idPeriod;
    private Boolean worked;
    private boolean mandatory;
    private boolean forbidden;    
}
