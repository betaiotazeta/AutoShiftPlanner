package org.betaiotazeta.autoshiftplanner;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 *
 * @author betaiotazeta
 */
public class MovableCellSelectionFilter implements SelectionFilter<Solution, Cell> {

    @Override
    public boolean accept(ScoreDirector<Solution> scoreDirector, Cell cell) {
        // Solution solution = scoreDirector.getWorkingSolution();
        return !cell.isForbidden();
    }   
}