package solver;

import java.util.LinkedList;
import java.util.List;

import model.Position2D;
import model.entities.BoxTarget;
import model.entities.SolidEntity;
import model.levels.Level;
import plan.AbstractAction;
import plan.Clause;
import plan.Plannable;
import plan.Predicate;
import plan.PredicateType;

public class SokobanPlannable implements Plannable<Position2D> {
	LevelSearcher level;
	Level preLevel;
	Clause<Position2D> goal;
	List<Predicate<Position2D>> initialState;
	Clause<Position2D> knowledgeBase;
	
	public SokobanPlannable(Level level) {
		this.level = new LevelSearcher(level);
		preLevel = level;
		initialState = new LinkedList<Predicate<Position2D>>();
		goal = new Clause<>();
		knowledgeBase = new Clause<>();
		
		initSokobanPlannable();
	}
	
	public void initSokobanPlannable() {
		
		for(SolidEntity se : preLevel.getSolidArray())
		{
			String str = level.addEntity(se);
			Predicate<Position2D> p = new Predicate<Position2D>(PredicateType.EntityAt, str.substring(0, str.indexOf(" ")),str.substring(1+str.indexOf(" ")) , se.getPosition());
			initialState.add(p);
			knowledgeBase.add(p);
		}
		
		for(BoxTarget bt: preLevel.getField().getBoxTargetList())
			goal.add(new Predicate<Position2D>(PredicateType.EntityAt, "Box", "?", bt.getPosition()));
		
	}

	@Override
	public Clause<Position2D> getGoal() {
		return goal;
	}

	@Override
	public List<Predicate<Position2D>> getInitialState() {
		return initialState;
	}

	@Override
	public Clause<Position2D> getKnowledgeBase() {
		return knowledgeBase;
	}

	@Override
	public boolean isSatisfied(Predicate<Position2D> p) {
		return knowledgeBase.isStaisfy(p);
	}

	@Override
	public AbstractAction<Position2D> getActionForPredicate(Predicate<Position2D> p) {
		if(p.getType()==PredicateType.EntityAt)
		{
			if(p.getEntity().equals("Box"))
			{
				return new PushBox(level, "0", p.getValue());
			}
			else if(p.getEntity().equals("Figure"))
			{
				return new MoveFigure(level, "0", p.getValue());
			}
			else
				return null;
		}
		else if(p.getType()==PredicateType.ReadyToPush)
			return new SimplePushBox(level, "0", p.getValue());
		else return null;
	}

}
