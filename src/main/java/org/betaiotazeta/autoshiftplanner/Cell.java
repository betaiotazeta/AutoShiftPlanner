package org.betaiotazeta.autoshiftplanner;

/**
 *
 * @author betaiotazeta
 */
public class Cell implements Cloneable {

    public Cell(byte idEmployee, short idPeriod, int dayOfWeek, int startingMinuteOfDay, boolean worked, boolean mandatory, boolean forbidden) {
        this.idEmployee = idEmployee;
        this.idPeriod = idPeriod;
        this.dayOfWeek = dayOfWeek;
        this.startingMinuteOfDay = startingMinuteOfDay;
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

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getStartingMinuteOfDay() {
        return startingMinuteOfDay;
    }

    public void setStartingMinuteOfDay(int startingMinuteOfDay) {
        this.startingMinuteOfDay = startingMinuteOfDay;
    }

    public boolean isWorked() {
        return worked;
    }

    public void setWorked(boolean worked) {
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
    // N.b: a timeGrain and a period are essentially the same thing.
    private short idPeriod;
    private int dayOfWeek;
    private int startingMinuteOfDay;
    private boolean worked;
    private boolean mandatory;
    private boolean forbidden;    
}
