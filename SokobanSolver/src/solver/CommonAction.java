package solver;

import java.util.PriorityQueue;

import plan.AndPredicate;
import plan.PlanAction;
import plan.Predicate;

public abstract class CommonAction implements PlanAction {
	Object[] params;
	boolean satisfied;
	
	public CommonAction(int size){
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
