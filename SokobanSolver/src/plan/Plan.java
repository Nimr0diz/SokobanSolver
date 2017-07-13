package plan;

import java.util.LinkedList;
import java.util.List;

public class Plan {
	List<PlanAction> plan;
	
	public Plan() {
		plan=new LinkedList<PlanAction>();
	}
	
	public void add(PlanAction action)
	{
		plan.add(action);
	}
	
}
