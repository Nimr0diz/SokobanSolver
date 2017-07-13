package solver;

import fail.CommonPredicate;
import fail.IPredicate;
import model.Position2D;

public class FigureAtPredicate extends CommonPredicate implements IPredicate {

	public FigureAtPredicate(Position2D position) {
		super(1);
		params[0] = position;
	}
	
	@Override
	public boolean isAtomic() {
		return false;
	}

}
