package solver;

import java.util.LinkedList;
import java.util.List;

import commons.Direction2D;
import model.Position2D;
import model.entities.Box;
import model.levels.Level;
import search.State;

public class BoxPathSearchable extends SokobanSearchable {
	Box b;
	public BoxPathSearchable(Level level, Box b, Position2D dest) {
		super(level, b.getPosition(), dest);
		this.b = b;
	}

	@Override
	public List<State<Position2D>> getAllPossibleStates(State<Position2D> s) {
		List<State<Position2D>> possibleState = new LinkedList<State<Position2D>>();
		for(Direction2D dir : Direction2D.values())
		{
			Position2D nextPos  = s.getState();
			b.getPolicy().getMovement().move(nextPos,dir);
			
			Position2D prevPos = s.getState();
			b.getPolicy().getMovement().move(prevPos, dir.getOppositeDirection());
			
			if(level.getSolidEntity(nextPos)==null && level.getSolidEntity(prevPos)==null)
			{
				MoveAction action = new MoveAction(b, dir);
				action.preformAction(s);
				possibleState.add(action.getResultState());
			}
		}
		return possibleState;
	}

}
