package solver;

import java.util.LinkedList;
import java.util.List;

import commons.Direction2D;
import model.Position2D;
import model.entities.Figure;
import model.entities.SolidEntity;
import model.levels.Level;
import search.State;

public class BoxPathSearchable extends SokobanSearchable {
	SolidEntity se;
	public BoxPathSearchable(Level level, SolidEntity se, Position2D dest) {
		super(level, se.getPosition(), dest);
		this.se = se;
	}

	@Override
	public List<State<Position2D>> getAllPossibleStates(State<Position2D> s) {
		List<State<Position2D>> possibleState = new LinkedList<State<Position2D>>();
		level.getField().removeSolidEntity(se.getPosition());
		for(Direction2D dir : Direction2D.values())
		{
			Position2D nextPos  = new Position2D(s.getState());
			se.getPolicy().getMovement().move(nextPos,dir);
			
			Position2D prevPos = new Position2D(s.getState());
			se.getPolicy().getMovement().move(prevPos, dir.getOppositeDirection());
			
			if((level.getSolidEntity(nextPos)==null && (level.getSolidEntity(prevPos)==null || level.getSolidEntity(prevPos) instanceof Figure)))
			{
				MoveAction action = new MoveAction(s,se, dir);
				action.preformAction(s);

				possibleState.add(action.getResultState());
			}
		}
		level.addEntity(se);
		return possibleState;
	}

}
