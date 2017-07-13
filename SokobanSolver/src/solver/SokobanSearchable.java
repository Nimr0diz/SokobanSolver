package solver;

import java.util.List;

import model.Position2D;
import model.levels.Level;
import search.Searchable;
import search.State;

public abstract class SokobanSearchable implements Searchable<Position2D> {
	Level level;
	State<Position2D> initialState;
	State<Position2D> goalState;
	
	public SokobanSearchable(Level level,Position2D src, Position2D dest) {
		this.level = level;
		this.initialState = new State<Position2D>(src);
		this.goalState = new State<Position2D>(dest);
	}

	@Override
	public State<Position2D> getInitialState() {
		return initialState;
	}

	@Override
	public State<Position2D> getGoalState() {
		// TODO Auto-generated method stub
		return goalState;
	}

}
