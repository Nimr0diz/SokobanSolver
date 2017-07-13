package plan;

public class AndPredicate extends CommonPredicate implements Predicate{
	
	public AndPredicate(Predicate... predicates) {
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
			tempSatisfied = tempSatisfied && ((Predicate)o).isSatisfied();
		
		return tempSatisfied;
	}

}
