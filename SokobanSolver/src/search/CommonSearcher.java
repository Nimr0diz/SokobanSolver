package search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T>{
	int nodesEvaluated;
	//PriorityQueue<State<T>> open;

	public Path<T> backTrace(State<T> s){
		List<Action<T>> actions= new LinkedList<>();
		List<State<T>> states = new LinkedList<>();
		State<T> state=s;
		
		while(state.getCameFrom()!=null)
		{
			actions.add(state.getActionCameFrom());
			states.add(state);
			state=state.getCameFrom();
			nodesEvaluated++;
		}
		Collections.reverse(actions);
		Collections.reverse(states);
		
		return new Path<T>(actions,states);
	}

	@Override
	public int getNumberOfNodesEvaluated() {
		// TODO Auto-generated method stub
		return nodesEvaluated;
	}
	
}
