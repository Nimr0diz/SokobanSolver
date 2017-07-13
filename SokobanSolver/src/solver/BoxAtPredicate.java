package solver;

import fail.CommonPredicate;
import model.Position2D;

public class BoxAtPredicate extends CommonPredicate {

	public BoxAtPredicate(Position2D position) {
		super(1);
		params[0] = position;
	}
	
	@Override
	public boolean isAtomic() {
		return false;
	}


}
