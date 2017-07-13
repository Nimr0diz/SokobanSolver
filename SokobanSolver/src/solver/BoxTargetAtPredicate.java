package solver;

import model.Position2D;
import plan.CommonPredicate;
import plan.Predicate;

public class BoxTargetAtPredicate extends CommonPredicate implements Predicate {
	
	public BoxTargetAtPredicate(Position2D position) {
		super(1);
		params[0] = position;
	}
	
	@Override
	public boolean isAtomic() {
		// TODO Auto-generated method stub
		return false;
	}

}
