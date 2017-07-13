package search;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class BFS<T> extends CommonSearcher<T> {
	
	public BFS() {
	}
	
	@Override
	public Path<T> search(Searchable<T> s) {

		State<T> initialState = s.getInitialState();
		State<T> goalState = s.getGoalState();
		nodesEvaluated = 0;
		
		PriorityQueue<State<T>> open = new PriorityQueue<State<T>>(new StateCostComparator<T>());
		HashSet<State<T>> closed = new HashSet<State<T>>();
		open.add(initialState);
		
		while(!open.isEmpty())
		{
			State<T> n = open.poll();
			closed.add(n);
			if(n.equals(goalState))
			{
				return backTrace(n); 
			}
			List<State<T>> successors=s.getAllPossibleStates(n);
			for(State<T> succ : successors)
			{
				if(!(open.contains(succ))&&!(closed.contains(succ)))
				{
					
					succ.setCameFrom(n);
					open.add(succ);
				}
				else if(succ.getCost()>n.getCost()+succ.getActionCameFrom().getCost())
				{
					if(!(open.contains(succ)))
						open.add(succ);
					else
					{
						open.remove(succ);
						succ.setCost(n.getCost()+succ.getActionCameFrom().getCost());
						open.add(succ);
					}
						
				}
			}
		}
		
		return null;
	}

}
