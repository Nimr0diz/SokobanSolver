package plan;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Strips<T> implements Planner<T> {

	@Override
	public Plan<T> plan(Plannable<T> plannable) {
		Plan<T> plan = new Plan<T>();
		Stack<Predicate<T>> stack = new Stack<Predicate<T>>();
		Clause<T> knowledgebase = plannable.getKnowledgeBase();
		for(Predicate<T> goal : plannable.getGoal().getSet())
			stack.push(goal);
		while(!stack.isEmpty())
		{
			Predicate<T> top = stack.pop();
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
			if(top instanceof AbstractAction)
			{
				AbstractAction<T> action = (AbstractAction<T>)top;
				plan.add(action);
				action.preformAction();
				Set<Predicate<T>> kbs = new HashSet<Predicate<T>>(knowledgebase.getSet());
				for(Predicate<T> effect : action.getEffect().getSet())
				{
					for(Predicate<T> kb : kbs)
					{
						if(kb.contradicts(effect))
							knowledgebase.remove(kb);
					}
					knowledgebase.add(effect);
				}
			}
			else
			{
				boolean satisfy = plannable.isSatisfied(top);
				if(!satisfy)
				{
					AbstractAction<T> action = plannable.getActionForPredicate(top);
					if(action==null)
					{
						plan = new Plan<T>();
						stack.removeAllElements();
					}
					stack.push(action);
					List<Predicate<T>> preConditions = new LinkedList<Predicate<T>>(action.getPreconditions());
					Collections.reverse(preConditions);
					for(int i=preConditions.size()-1;i>=0;i--)
					{
						stack.push(preConditions.get(i));
					}
				}
			}
		}
		return plan;
	}

}
