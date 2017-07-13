package solver;

import java.util.LinkedList;
import java.util.List;

import commons.Direction2D;
import model.Position2D;
import model.entities.Figure;
import model.entities.SolidEntity;
import model.levels.Level;
import search.State;

public class FigurePathSearchable extends SokobanSearchable {
	SolidEntity se;
	
	public FigurePathSearchable(Level level, SolidEntity se, Position2D dest) {
		super(level,se.getPosition(), dest);
		this.se = se;
	}

	@Override
	public List<State<Position2D>> getAllPossibleStates(State<Position2D> s) {
		List<State<Position2D>> possibleState = new LinkedList<State<Position2D>>();
		for(Direction2D dir : Direction2D.values())
		{
			Position2D newPos  = new Position2D(s.getState());
			se.getPolicy().getMovement().move(newPos,dir);
			if(level.getSolidEntity(newPos)==null)
			{
				MoveAction action = new MoveAction(s,se, dir);
				action.preformAction(s);
				possibleState.add(action.getResultState());
			}
		}
		return possibleState;
	}

}
