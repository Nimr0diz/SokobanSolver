package plan;

import java.util.List;
import java.util.Map;

import fail.AndPredicate;
import fail.IPredicate;

public interface Plannable<E> {

	AndPredicate getGoal();
	List<IPredicate> getInitialState();
	KnowledgeBase getKnowledgeBase();
	List<E> getAllEntities();
	PlanAction getActionForPredicate(IPredicate p);
	boolean isSatisfied(IPredicate p);
}
