package fail;

public class AndPredicate extends CommonPredicate implements IPredicate{
	
	public AndPredicate(IPredicate... predicates) {
		params = predicates;
	}

	@Override
	public boolean isAtomic() {
		return false;
	}
	
	@Override
	public boolean isSatisfied() {
		boolean tempSatisfied = true;
		for(Object o:params)
			tempSatisfied = tempSatisfied && ((IPredicate)o).isSatisfied();
		
		return tempSatisfied;
	}

}
