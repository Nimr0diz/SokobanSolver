package plan;

import java.util.PriorityQueue;

public interface Predicate {
	Object[] getParams();
	boolean isAtomic();
	boolean isSatisfied();
	void setSatisfied(boolean satisfy);

}
