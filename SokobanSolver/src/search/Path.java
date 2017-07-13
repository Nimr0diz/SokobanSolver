package search;

import java.util.LinkedList;
import java.util.List;

public class Path<T> {
	List<Action<T>> actions;
	List<State<T>> states;
	
	public Path() {
		actions = new LinkedList<>();
		states = new LinkedList<>();
	}
	
	public Path(List<Action<T>> actions, List<State<T>> states)
	{
		this.actions = actions;
		this.states = states;
	}
	
	public List<Action<T>> getActions() {
		return actions;
	}

	public List<State<T>> getStates() {
		return states;
	}

	public void addAction(Action<T> action)
	{
		actions.add(action);
	}
	
	public void addState(State<T> state)
	{
		states.add(state);
	}
	
	public boolean thereIsPath()
	{
		if(actions == null || states == null) return false;
		return true;
	}
	
	public State<T> getLastState()
	{
		return states.get(states.size()-1);
	}
	
	public State<T> getFirstState()
	{
		return states.get(0);
	}
	
	public State<T> getSecondState()
	{
		return states.get(1);
	}
	
	public State<T> getBeforeLastState()
	{
		return states.get(states.size()-2);
	}
	
}
