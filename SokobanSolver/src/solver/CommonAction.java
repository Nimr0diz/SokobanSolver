package solver;

import java.util.List;

import model.Position2D;
import plan.PlanAction;
import search.Action;

public abstract class CommonAction implements PlanAction<Position2D> {
	Object[] params;
	List<Action<Position2D>> playerActions;
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

	public List<Action<Position2D>> getPlayerActions() {
		return playerActions;
	}
	
	
}
