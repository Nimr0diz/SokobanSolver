package solver;

import fail.CommonPredicate;
import fail.IPredicate;
import model.Position2D;

public class BoxTargetAtPredicate extends CommonPredicate implements IPredicate {
	
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
