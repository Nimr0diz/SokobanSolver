package plan;

import java.util.Stack;

public class Strips<E,T> implements Planner<E> {

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
					Predicate p = (Predicate)(o);
					if(p instanceof NotPredicate)
					{
						knowledgebase.remove((Predicate)(p.getParams()[0]));
					}
					stack.push((Predicate)o);
				}
			}
			else if(top instanceof PlanAction)
			{
				PlanAction<T> action = (PlanAction<T>)top;
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
			else if(top instanceof NotPredicate)
			{
				Predicate pre = (Predicate)(((NotPredicate)top).params[0]);
				pre.setSatisfied(plannable.isSatisfied(pre));
				stack.push(new NotPredicate(pre));
			}
			else
			{
				boolean satisfy = plannable.isSatisfied(top);
				if(!satisfy)
				{
					PlanAction<T> action = plannable.getActionForPredicate(top);
					action.setSatisfied(satisfy);
					stack.push(action);
				}
			}
		}
		return plan;
	}

}
