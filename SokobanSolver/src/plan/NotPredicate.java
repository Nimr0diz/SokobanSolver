package plan;


public class NotPredicate extends CommonPredicate implements Predicate {

	public NotPredicate(Predicate p) {
		super();
		params[0] = p;
		
	}
	
	@Override
	public boolean isAtomic() {
		return false;
	}

	@Override
	public boolean isSatisfied() {
		return !(((Predicate)params[0]).isSatisfied());
	}

}
