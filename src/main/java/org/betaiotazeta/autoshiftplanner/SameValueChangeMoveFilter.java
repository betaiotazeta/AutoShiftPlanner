package org.betaiotazeta.autoshiftplanner;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.heuristic.selector.move.generic.ChangeMove;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 *
 * @author betaiotazeta
 */
public class SameValueChangeMoveFilter implements SelectionFilter<Solution, ChangeMove> {

    @Override
    public boolean accept(ScoreDirector<Solution> scoreDirector, ChangeMove move) {    
        Cell cell = (Cell) move.getEntity();
        return !cell.getWorked().equals(move.getToPlanningValue());
    }    
}