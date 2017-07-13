package solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Position2D;
import model.entities.BoxTarget;
import model.entities.SolidEntity;
import model.levels.Level;
import plan.AbstractAction;
import plan.AbstractPlannable;
import plan.Clause;
import plan.Plannable;
import plan.Predicate;
import plan.PredicateType;

public class SokobanPlannable extends AbstractPlannable<Position2D> implements Plannable<Position2D> {
	LevelSearcher level;
	Level preLevel;
	
	public SokobanPlannable(Level level) {
		this.level = new LevelSearcher(level);
		preLevel = level;
		initialState = new LinkedList<Predicate<Position2D>>();
		goal = new Clause<>();
		knowledgeBase = new Clause<>();
		
		initSokobanPlannable();
	}
	
	public SokobanPlannable(SokobanPlannable plannable)
	{
		this.level = new LevelSearcher(plannable.level);
		this.preLevel = new Level(plannable.preLevel);
		this.goal = new Clause<Position2D>(plannable.goal);
		this.initialState = new LinkedList<Predicate<Position2D>>(plannable.initialState);
		this.knowledgeBase = new Clause<Position2D>(plannable.knowledgeBase);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		SokobanPlannable plannable = new SokobanPlannable(this);
		return plannable;
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
	public List<AbstractAction<Position2D>> getActionForPredicate(Predicate<Position2D> p) {
		List<AbstractAction<Position2D>> actionList = new ArrayList<>();
		if(p.getType()==PredicateType.EntityAt)
		{
			if(p.getEntity().equals("Box"))
			{
				for(int i=0;i<level.getNumberOfBoxes();i++)
				{
					if(level.getEntity("Box",i+"")!=null)
						actionList.add(new PushBox(level, i+"", p.getValue()));
				}
			}
			else if(p.getEntity().equals("Figure"))
			{
				actionList.add(new MoveFigure(level, "0", p.getValue()));
			}
		}
		else if(p.getType()==PredicateType.ReadyToPush)
			actionList.add(new SimplePushBox(level, p.getId(), p.getValue()));
		
		return actionList;
	}

}
