package solver;

import java.util.LinkedList;
import java.util.List;

import model.Position2D;
import plan.Clause;
import plan.PlanAction;
import plan.Predicate;
import plan.PredicateType;

public class SimplePushBox extends SokobanAction implements PlanAction<Position2D> {

	
	public SimplePushBox(LevelSearcher level, String id, Position2D value) {
		super(level, PredicateType.SimplePush, "Box", id, value);

	}

	@Override
	public List<Predicate<Position2D>> getPreconditions() {
		List<Predicate<Position2D>> preConditions = new LinkedList<>();
		path = level.searchPushPath(entity, id, value);
		if(!path.thereIsPath())
			preConditions.add(new Predicate<Position2D>(PredicateType.NoSolution,"?","?",null));
		//else
		//	level.updatePosition(entity, id, value);
		return preConditions;
	}

	@Override
	public Clause<Position2D> getEffect() {
		Clause<Position2D> effects = new Clause<>();
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Nothing","0",path.getFirstState().getState()));
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Nothing","0",path.getSecondState().getState()));
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Box",id,path.getLastState().getState()));
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Figure","0",path.getBeforeLastState().getState()));
		return effects;
	}
	
	@Override
	public void preformAction() {
		level.updatePosition(entity, id, value);
		level.updatePosition("Figure", "0", path.getBeforeLastState().getState());
	}





}
