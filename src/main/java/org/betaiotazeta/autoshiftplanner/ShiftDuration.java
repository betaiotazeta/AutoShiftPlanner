package org.betaiotazeta.autoshiftplanner;

/**
 *
 * @author betaiotazeta
 */
public class ShiftDuration {

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getDurationInGrains() {
        return durationInGrains;
    }

    public void setDurationInGrains(int durationInGrains) {
        this.durationInGrains = durationInGrains;
    }

    public int getDurationIndex() {
        return durationIndex;
    }

    public void setDurationIndex(int durationIndex) {
        this.durationIndex = durationIndex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeString() {
        int hourOfDay = durationInMinutes / 60;
        int minuteOfHour = durationInMinutes % 60;
        return (hourOfDay < 10 ? "0" : "") + hourOfDay
                + ":" + (minuteOfHour < 10 ? "0" : "") + minuteOfHour;
    }
    
    @Override
    public String toString() {
        return "sd" + durationIndex + "(" + getTimeString() + ")";
    }    

    private int durationInMinutes;
    private int durationInGrains;
    private int durationIndex;
    private long id;
}