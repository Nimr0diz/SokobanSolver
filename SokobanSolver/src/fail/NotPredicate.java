package fail;


public class NotPredicate extends CommonPredicate implements IPredicate {

	public NotPredicate(IPredicate p) {
		super(1);
		params[0] = p;
		
	}
	
	@Override
	public boolean isAtomic() {
		return false;
	}

	@Override
	public boolean isSatisfied() {
		return !(((IPredicate)params[0]).isSatisfied());
	}

}
