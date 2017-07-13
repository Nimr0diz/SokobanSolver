package plan;

import java.util.LinkedList;
import java.util.List;

import search.Action;

public class Plan<T> {
	List<PlanAction> plan;
	
	public Plan() {
		plan=new LinkedList<PlanAction>();
	}
	
	public void add(PlanAction action)
	{
		plan.add(action);
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("");
		for(PlanAction<T> pa : plan)
		{
			for(Action<T> a : pa.getPlayerActions())
			{
				sb.append(a);
				sb.append("\n");	
			}
			sb.append("-----------\n");
			
		}
		return sb.toString();
		
	}
}
