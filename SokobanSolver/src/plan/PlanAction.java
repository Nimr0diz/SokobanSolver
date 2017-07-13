package plan;

import java.util.List;
import java.util.PriorityQueue;

import fail.AndPredicate;
import fail.IPredicate;
import search.Action;

public interface PlanAction<T> {

	List<IPredicate> getPreconditions();
	
	AndPredicate getEffect();

	List<Action<T>> getPlayerActions();
	
	

}
