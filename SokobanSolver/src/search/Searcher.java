package search;

import java.util.List;

public interface Searcher<T> {

	Path<T> search(Searchable<T> s);
	int getNumberOfNodesEvaluated();
}
