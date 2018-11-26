package org.betaiotazeta.autoshiftplanner;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author betaiotazeta
 */
@XStreamAlias("shift")
public class Shift {

    public int getShiftIndex() {
        return shiftIndex;
    }

    public void setShiftIndex(int shiftIndex) {
        this.shiftIndex = shiftIndex;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "s" + shiftIndex + "(" + employee.getName() + ")";
    }
    
    private int shiftIndex;
    private Employee employee;
}
