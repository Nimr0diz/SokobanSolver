package search;

import java.util.List;

public interface Searcher<T> {

	List<Action<T>> search(Searchable<T> s);
	int getNumberOfNodesEvaluated();
}
