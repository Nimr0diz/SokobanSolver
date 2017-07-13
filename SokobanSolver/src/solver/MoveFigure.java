package solver;

import java.util.LinkedList;
import java.util.List;

import model.Position2D;
import plan.Clause;
import plan.PlanAction;
import plan.Predicate;
import plan.PredicateType;

public class MoveFigure extends SokobanAction implements PlanAction<Position2D> {

	
	public MoveFigure(LevelSearcher level, String id, Position2D value) {
		super(level, PredicateType.Move, "Figure", id, value);

	}

	@Override
	public List<Predicate<Position2D>> getPreconditions() {
		List<Predicate<Position2D>> preConditions = new LinkedList<>();
		path = level.searchMovePath(id,value);
		if(!path.thereIsPath())
			preConditions.add(new Predicate<Position2D>(PredicateType.NoSolution,"?","?",null));
		else
			level.updatePosition(entity, id, value);
		return preConditions;
	}

	@Override
	public Clause<Position2D> getEffect() {
		Clause<Position2D> effects = new Clause<>();
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Nothing","?",path.getFirstState().getState()));
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Figure",id,path.getBeforeLastState().getState()));
		return effects;
	}





}
