package org.betaiotazeta.autoshiftplanner;

/**
 *
 * @author betaiotazeta
 */

public class Business {
    
    public Business(double startTime, double endTime, int numberOfEmployees) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfEmployees = numberOfEmployees;
    }
   
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }
    
    public double getStartTime() {
        return startTime;
    }
    
    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }
    
    public double getEndTime() {
        return endTime;
    }
    
    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
    
    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }        
    
    private double startTime;
    private double endTime;   
    private int numberOfEmployees;
}
