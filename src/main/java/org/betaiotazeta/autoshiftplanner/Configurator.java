package org.betaiotazeta.autoshiftplanner;

/**
 *
 * @author betaiotazeta
 */
public class Configurator {

    public boolean isHoursPerWeekCheck() {
        return hoursPerWeekCheck;
    }

    public void setHoursPerWeekCheck(boolean hoursPerWeekCheck) {
        this.hoursPerWeekCheck = hoursPerWeekCheck;
    }

    public boolean isShiftsPerDayCheck() {
        return shiftsPerDayCheck;
    }

    public void setShiftsPerDayCheck(boolean shiftsPerDayCheck) {
        this.shiftsPerDayCheck = shiftsPerDayCheck;
    }

    public int getShiftsPerDay() {
        return shiftsPerDay;
    }

    public void setShiftsPerDay(int shiftsPerDay) {
        this.shiftsPerDay = shiftsPerDay;
    }

    public boolean isBreakLenghtCheck() {
        return breakLenghtCheck;
    }

    public void setBreakLenghtCheck(boolean breakLenghtCheck) {
        this.breakLenghtCheck = breakLenghtCheck;
    }

    public double getBreakLenght() {
        return breakLenght;
    }

    public void setBreakLenght(double breakLenght) {
        this.breakLenght = breakLenght;
    }

    public boolean isShiftLenghtCheck() {
        return shiftLenghtCheck;
    }

    public void setShiftLenghtCheck(boolean shiftLenghtCheck) {
        this.shiftLenghtCheck = shiftLenghtCheck;
    }

    public double getShiftLenghtMin() {
        return shiftLenghtMin;
    }

    public void setShiftLenghtMin(double shiftLenghtMin) {
        this.shiftLenghtMin = shiftLenghtMin;
    }

    public double getShiftLenghtMax() {
        return shiftLenghtMax;
    }

    public void setShiftLenghtMax(double shiftLenghtMax) {
        this.shiftLenghtMax = shiftLenghtMax;
    }

    public boolean isEmployeesPerPeriodCheck() {
        return employeesPerPeriodCheck;
    }

    public void setEmployeesPerPeriodCheck(boolean employeesPerPeriodCheck) {
        this.employeesPerPeriodCheck = employeesPerPeriodCheck;
    }

    public int getEmployeesPerPeriod() {
        return employeesPerPeriod;
    }

    public void setEmployeesPerPeriod(int employeesPerPeriod) {
        this.employeesPerPeriod = employeesPerPeriod;
    }

    public boolean isHoursPerDayCheck() {
        return hoursPerDayCheck;
    }

    public void setHoursPerDayCheck(boolean hoursPerDayCheck) {
        this.hoursPerDayCheck = hoursPerDayCheck;
    }

    public double getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(double hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public boolean isMandatoryShiftsCheck() {
        return mandatoryShiftsCheck;
    }

    public void setMandatoryShiftsCheck(boolean mandatoryShiftsCheck) {
        this.mandatoryShiftsCheck = mandatoryShiftsCheck;
    }

    public boolean isUniformEmployeesDistributionCheck() {
        return uniformEmployeesDistributionCheck;
    }

    public void setUniformEmployeesDistributionCheck(boolean uniformEmployeesDistributionCheck) {
        this.uniformEmployeesDistributionCheck = uniformEmployeesDistributionCheck;
    }
    
    private boolean hoursPerWeekCheck;
    private boolean shiftsPerDayCheck;
    private int shiftsPerDay;
    private boolean breakLenghtCheck;
    private double breakLenght;
    private boolean shiftLenghtCheck;
    private double shiftLenghtMin;
    private double shiftLenghtMax;
    private boolean employeesPerPeriodCheck;
    private int employeesPerPeriod;
    private boolean hoursPerDayCheck;
    private double hoursPerDay;
    private boolean mandatoryShiftsCheck;
    private boolean uniformEmployeesDistributionCheck;
}