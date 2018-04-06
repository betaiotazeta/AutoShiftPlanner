package org.betaiotazeta.autoshiftplanner;

import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.heuristic.selector.move.generic.SwapMove;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 *
 * @author betaiotazeta
 */
public class SameValueSwapMoveFilter implements SelectionFilter<Solution, SwapMove> {

    @Override
    public boolean accept(ScoreDirector<Solution> scoreDirector, SwapMove move) {
        Cell leftCell = (Cell) move.getLeftEntity();
        Cell rightCell = (Cell) move.getRightEntity();
        return !leftCell.getWorked().equals(rightCell.getWorked());
    }
}