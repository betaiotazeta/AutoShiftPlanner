package org.betaiotazeta.autoshiftplanner;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author betaiotazeta
 */
@XStreamAlias("timeGrain")
public class TimeGrain {

    public int getGrainIndex() {
        return grainIndex;
    }

    public void setGrainIndex(int grainIndex) {
        this.grainIndex = grainIndex;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public int getStartingMinuteOfDay() {
        return startingMinuteOfDay;
    }

    public void setStartingMinuteOfDay(int startingMinuteOfDay) {
        this.startingMinuteOfDay = startingMinuteOfDay;
    }

    public String getTimeString() {
        int hourOfDay = startingMinuteOfDay / 60;
        int minuteOfHour = startingMinuteOfDay % 60;
        return (hourOfDay < 10 ? "0" : "") + hourOfDay
                + ":" + (minuteOfHour < 10 ? "0" : "") + minuteOfHour;
    }

    public String getDateTimeString() {
        return day.getDateString() + "-" + getTimeString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStartingGrainOfDay() {
        return startingGrainOfDay;
    }

    public void setStartingGrainOfDay(int startingGrainOfDay) {
        this.startingGrainOfDay = startingGrainOfDay;
    }
    
    @Override
    public String toString() {
        return "tg" + grainIndex + "(" + getDateTimeString() + ")";
    }

    // Variables declaration
    private int grainIndex;
    private Day day;
    private int startingMinuteOfDay;
    private long id;
    private int startingGrainOfDay;
}