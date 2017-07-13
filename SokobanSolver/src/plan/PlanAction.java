package plan;

import java.util.List;

import search.Path;

public interface PlanAction<T> {

	 List<Predicate<T>> getPreconditions();
	
	 Clause<T> getEffect();

	 Path<T> getPlayerActions();
	
	 void preformAction();
	
	

}
