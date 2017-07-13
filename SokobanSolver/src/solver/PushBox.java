package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import fail.AndPredicate;
import fail.IPredicate;
import model.Position2D;
import model.entities.Box;
import model.levels.Level;
import plan.PlanAction;
import search.Action;
import search.BFS;
import search.Searcher;

public class PushBox extends CommonAction implements PlanAction<Position2D> {
	Position2D lastPosition;
	public PushBox(Level level,Box box, Position2D position2d) {
		super(3);
		params[0] = level;
		params[1] = box;
		params[2] = position2d;
		lastPosition = null;
	}
	
	@Override
	public boolean isAtomic() {
		return false;
	}

	@Override
	public List<IPredicate> getPreconditions() {
		List<IPredicate> preConditions = new LinkedList<IPredicate>();
		BoxPathSearchable searchable = new BoxPathSearchable((Level)params[0], (Box)params[1], (Position2D)params[2]);
		Searcher<Position2D> searcher = new BFS<Position2D>();
		playerActions = searcher.search(searchable);
		
		MoveAction firstAct = (MoveAction)playerActions.get(0);
		
		Position2D node = new Position2D(firstAct.getOriginalState().getState());
		Position2D figNode = new Position2D(firstAct.getOriginalState().getState());
		figNode.move(firstAct.getDirection().getOppositeDirection(), 1);
		preConditions.add(new FigureAtPredicate(figNode));
		preConditions.add(new BoxAtPredicate(node));
		
		for(int i=1;i<playerActions.size();i++)
		{
			MoveAction act = (MoveAction)playerActions.get(i);
			if(act.getDirection()!=((MoveAction)(playerActions.get(i-1))).getDirection())
			{
				node = new Position2D(act.getOriginalState().getState());
				figNode = new Position2D(act.getOriginalState().getState());
				figNode.move(act.getDirection().getOppositeDirection(), 1);
				preConditions.add(new FigureAtPredicate(figNode));
				preConditions.add(new BoxAtPredicate(node));

			}
		}
		
		lastPosition = (Position2D)(playerActions.get(playerActions.size()-2).getOriginalState().getState());
		Collections.reverse(preConditions);
		return preConditions;
	}

	@Override
	public AndPredicate getEffect() {
		return new AndPredicate(new BoxAtPredicate((Position2D)params[2]),new FigureAtPredicate(lastPosition));
	}


}
