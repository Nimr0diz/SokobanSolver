package fail;

import java.util.PriorityQueue;

public interface IPredicate {
	Object[] getParams();
	boolean isAtomic();
	boolean isSatisfied();
	void setSatisfied(boolean satisfy);

}
