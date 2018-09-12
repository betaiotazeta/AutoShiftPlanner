package org.betaiotazeta.autoshiftplanner;

/**
 *
 * @author betaiotazeta
 */
public class Day {

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDateString() {
        return "day:" + dayOfWeek;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return getDateString();
    }

    private int dayOfWeek;
    private long id;
}