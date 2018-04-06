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
    
    @PlanningEntityCollectionProperty
    public List<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }

    @ValueRangeProvider(id = "booleanRange")
    @ProblemFactCollectionProperty
    public List<Boolean> getBooleanList() {
        return booleanList;
    }

    public void setBooleanList(List<Boolean> booleanList) {
        this.booleanList = booleanList;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public ArrayList<Employee> getStaff() {
        return staffScore;
    }

    public void setStaff(ArrayList<Employee> staffScore) {
        this.staffScore = staffScore;
    }

    public Table getTable() {
        return tableScore;
    }

    public void setTable(Table tableScore) {
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
    
    public AspApp getAspApp() {
        return aspApp;
    }

    public void setAspApp(AspApp aspApp) {
        this.aspApp = aspApp;
    }
    
    private List<Cell> cellList;
    private List<Boolean> booleanList;
    private HardSoftScore score;
    private ArrayList<Employee> staffScore;
    private Table tableScore;
    private Business business;
    private Configurator configurator;
    private int bonus;
    @XStreamOmitField
    private AspApp aspApp;
}
