package solver;

import java.util.List;
import java.util.PriorityQueue;

import model.Position2D;
import model.entities.Box;
import model.levels.Level;
import plan.AndPredicate;
import plan.PlanAction;
import plan.Predicate;
import search.Action;
import search.BFS;

public class PushBox extends CommonAction implements PlanAction {

	public PushBox(Level level,Box box, Position2D position2d) {
		super(3);
		params[0] = level;
		params[1] = box;
		params[2] = position2d;
	}

	@Override
	public boolean isAtomic() {
		return false;
	}

	@Override
	public PriorityQueue<Predicate> getPreconditions() {
		PriorityQueue<Predicate> preConditions = new PriorityQueue<Predicate>();
		BoxPathSearchable searchable = new BoxPathSearchable((Level)params[0], (Box)params[1], (Position2D)params[2]);
		List<Action<Position2D>> path = new BFS().search(searchable);
		
		for(int i=1;i<path.size();i++)
		{
			MoveAction act = (MoveAction)path.get(i);
			if(act.equals(path.get(i-1)))
			{
				Position2D node = act.getResultState().getState();
				preConditions.add(new BoxAtPredicate(node));
				node.move(act.getDirection().getOppositeDirection(), 1);
				preConditions.add(new FigureAtPredicate(node));
			}
		}
		return preConditions;
	}

	@Override
	public AndPredicate getEffect() {
		// TODO Auto-generated method stub
		return null;
	}


}
