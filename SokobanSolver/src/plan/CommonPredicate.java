package plan;

import java.util.PriorityQueue;

public abstract class CommonPredicate implements Predicate{
	protected Object[] params;
	boolean satisfied;
	
	public CommonPredicate() {
	
	}
	
	public CommonPredicate(int size){
		params = new Object[size];
	}
	
	@Override
	public Object[] getParams() {
		return params;
	}
	
	@Override
	public boolean isSatisfied() {
		return satisfied;
	}
	
	@Override
	public void setSatisfied(boolean satisfy) {
		satisfied = satisfy;
		
	}
	
}
