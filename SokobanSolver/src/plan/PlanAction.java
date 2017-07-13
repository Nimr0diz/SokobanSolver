package plan;

import java.util.List;
import java.util.PriorityQueue;

import search.Action;

public interface PlanAction<T> extends Predicate{

	List<Predicate> getPreconditions();
	
	AndPredicate getEffect();

	List<Action<T>> getPlayerActions();
	
	

}
