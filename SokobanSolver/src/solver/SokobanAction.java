package solver;

import java.util.List;

import model.Position2D;
import plan.PlanAction;
import plan.Predicate;
import plan.PredicateType;
import plan.AbstractAction;
import search.Action;
import search.Path;

public abstract class SokobanAction  extends AbstractAction<Position2D> {
	Path<Position2D> path;
	LevelSearcher level;
	
	public SokobanAction(LevelSearcher level,PredicateType type,String entity, String id, Position2D value) {
		super(type,entity,id,value);
		this.level = level;
	}

	public Path<Position2D> getPlayerActions() {
		return path;
	}
	
	@Override
	public void preformAction() {
		level.updatePosition(entity, id, value);
	}
	
	@Override
	public String toString() {
		return type.name()+"="+entity+" "+id+","+value;
	}
		
	
}
