package solver;

import model.Position2D;
import plan.CommonPredicate;
import plan.Predicate;

public class FigureAtPredicate extends CommonPredicate implements Predicate {

	public FigureAtPredicate(Position2D position) {
		super(1);
		params[0] = position;
	}
	
	@Override
	public boolean isAtomic() {
		return false;
	}

}
