package plan;

import java.util.Stack;

public class Strips<E> implements Planner<E> {

	@Override
	public Plan plan(Plannable<E> plannable) {
		Plan plan = new Plan();
		Stack<Predicate> stack = new Stack<Predicate>();
		KnowledgeBase knowledgebase = plannable.getKnowledgeBase();
		stack.push(plannable.getGoal());
		while(!stack.isEmpty())
		{
			Predicate top = stack.pop();
			if(top instanceof AndPredicate)
			{
				for(Object o:top.getParams())
				{
					stack.push((Predicate)o);
				}
			}
			else if(top instanceof PlanAction)
			{
				PlanAction action = (PlanAction)top;
				if(top.isSatisfied())
				{
					plan.add(action);
					knowledgebase.add(action.getEffect());
				}
				else
				{
					stack.push(top);
					for(Predicate p:action.getPreconditions())
					{
						p.setSatisfied(plannable.isSatisfied(p));
						stack.push(p);
					}
				}
			}
			else
			{
				boolean satisfy = plannable.isSatisfied(top);
				if(satisfy)
				{
					PlanAction action = plannable.getActionForPredicate(top);
					action.setSatisfied(satisfy);
					stack.push(action);
				}
			}
		}
		
		return plan;
	}

}
