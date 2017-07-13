package plan;

import java.util.List;
import java.util.PriorityQueue;

public interface PlanAction extends Predicate{

	PriorityQueue<Predicate> getPreconditions();
	
	AndPredicate getEffect();

	
	

}
