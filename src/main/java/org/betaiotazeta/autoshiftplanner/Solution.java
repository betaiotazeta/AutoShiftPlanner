package org.betaiotazeta.autoshiftplanner;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.util.ArrayList;
import java.util.List;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

/**
 *
 * @author betaiotazeta
 */
@PlanningSolution
public class Solution {
    
    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public ArrayList<Employee> getStaffScore() {
        return staffScore;
    }

    public void setStaffScore(ArrayList<Employee> staffScore) {
        this.staffScore = staffScore;
    }

    public Table getTableScore() {
        return tableScore;
    }

    public void setTableScore(Table tableScore) {
        this.tableScore = tableScore;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Configurator getConfigurator() {
        return configurator;
    }

    public void setConfigurator(Configurator configurator) {
        this.configurator = configurator;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }

    @ValueRangeProvider(id = "timeGrainRange")
    @ProblemFactCollectionProperty
    public List<TimeGrain> getTimeGrainList() {
        return timeGrainList;
    }

    public void setTimeGrainList(List<TimeGrain> timeGrainList) {
        this.timeGrainList = timeGrainList;
    }

    @ValueRangeProvider(id = "shiftDurationRange")
    @ProblemFactCollectionProperty
    public List<ShiftDuration> getShiftDurationList() {
        return shiftDurationList;
    }

    public void setShiftDurationList(List<ShiftDuration> shiftDurationList) {
        this.shiftDurationList = shiftDurationList;
    }

    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    @PlanningEntityCollectionProperty
    public List<ShiftAssignment> getShiftAssignmentList() {
        return shiftAssignmentList;
    }

    public void setShiftAssignmentList(List<ShiftAssignment> shiftAssignmentList) {
        this.shiftAssignmentList = shiftAssignmentList;
    }
    
    public AspApp getAspApp() {
        return aspApp;
    }

    public void setAspApp(AspApp aspApp) {
        this.aspApp = aspApp;
    }
    
    private HardSoftScore score;
    private ArrayList<Employee> staffScore;
    private Table tableScore;
    private Business business;
    private Configurator configurator;
    private int bonus;
    private List<Day> dayList;
    private List<TimeGrain> timeGrainList;
    private List<ShiftDuration> shiftDurationList;
    private List<Shift> shiftList;
    private List<ShiftAssignment> shiftAssignmentList;
    @XStreamOmitField
    private AspApp aspApp;
}
