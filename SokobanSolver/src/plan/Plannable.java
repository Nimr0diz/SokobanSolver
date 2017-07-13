package plan;

import java.util.List;
import java.util.Map;

public interface Plannable<E> {

	AndPredicate getGoal();
	List<Predicate> getInitialState();
	KnowledgeBase getKnowledgeBase();
	List<E> getAllEntities();
	PlanAction getActionForPredicate(Predicate p);
	boolean isSatisfied(Predicate p);
}
