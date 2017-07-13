package search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra<T extends Comparable<T>> extends CommonSearcher<T> {

	
	public Dijkstra() {
	}
	
	@Override
	public Path<T> search(Searchable<T> s) {

		State<T> initialState = s.getInitialState();
		State<T> goalState = s.getGoalState();
		
		
		PriorityQueue<State<T>> open = new PriorityQueue<State<T>>(new StateCostComparator<T>());
		HashMap<T,State<T>> closed = new HashMap<T,State<T>>();
		open.add(initialState);
		State<T> n = null;
		while(!open.isEmpty())
		{
			n = open.poll();
			closed.put(n.getState(), n);
			List<State<T>> successors=s.getAllPossibleStates(n);
			for(State<T> succ : successors)
			{
				if(!(open.contains(succ))&&!(closed.containsKey(succ.getState())))
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
		
		return backTrace(closed.get(goalState.getState()));
	}


}
