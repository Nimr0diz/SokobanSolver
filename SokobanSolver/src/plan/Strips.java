package plan;

import java.util.Stack;

import fail.AndPredicate;
import fail.IPredicate;
import fail.NotPredicate;

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
			/*if(top instanceof AndPredicate)
			{
				for(Object o:top.getParams())
				{
					IPredicate p = (IPredicate)(o);
					if(p instanceof NotPredicate)
					{
						knowledgebase.remove((IPredicate)(p.getParams()[0]));
					}
					stack.push((IPredicate)o);
				}
			}*/
			if(top instanceof PlanAction)
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
					for(IPredicate p:action.getPreconditions())
					{
						//System.out.println(p.equals();
						p.setSatisfied(plannable.isSatisfied(p));
						stack.push(p);
					}
				}
			}
			else if(top instanceof NotPredicate)
			{
				IPredicate pre = (IPredicate)(((NotPredicate)top).params[0]);
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
