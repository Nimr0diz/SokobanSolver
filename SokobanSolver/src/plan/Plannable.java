package plan;

import java.util.List;

public interface Plannable<T> {

	Clause<T> getGoal();
	List<Predicate<T>> getInitialState();
	Clause<T> getKnowledgeBase();
	//List<T> getAllEntities();
	AbstractAction<T> getActionForPredicate(Predicate<T> p);
	boolean isSatisfied(Predicate<T> p);
}
