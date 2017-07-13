package search;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T>{
	int nodesEvaluated;
	//PriorityQueue<State<T>> open;

	public List<Action<T>> backTrace(State<T> s){
		List<Action<T>> path= new LinkedList<Action<T>>();
		State<T> state=s;
		
		while(state.getCameFrom()!=null)
		{
			path.add(state.getActionCameFrom());
			state=state.getCameFrom();
			nodesEvaluated++;
		}
		return path;
	}
	
	@Override
	public int getNumberOfNodesEvaluated() {
		// TODO Auto-generated method stub
		return nodesEvaluated;
	}
	
}
