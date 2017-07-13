package solver;

import java.util.LinkedList;
import java.util.List;

import model.entities.Box;
import model.entities.BoxTarget;
import model.entities.Figure;
import model.entities.SolidEntity;
import model.levels.Level;
import plan.AndPredicate;
import plan.KnowledgeBase;
import plan.PlanAction;
import plan.Plannable;
import plan.Predicate;
import model.Position2D;

public class SokobanPlannable implements Plannable<SolidEntity> {
	Level level;
	AndPredicate goal;
	List<Predicate> initialState;
	KnowledgeBase knowledgeBase;
	
	public SokobanPlannable(Level level) {
		this.level = level;
	}
	
	public void initSokobanPlannable() {
		Predicate[] predicateGoal = new Predicate[level.getBoxesLeft()];
		initialState = new LinkedList<Predicate>();
		knowledgeBase = new KnowledgeBase();
		int i = 0;
		for(BoxTarget bt: level.getField().getBoxTargetList())
		{
			predicateGoal[i++] = new BoxAtPredicate(bt.getPosition());
			initialState.add(new BoxTargetAtPredicate(bt.getPosition()));
			knowledgeBase.add(new BoxTargetAtPredicate(bt.getPosition()));
		}
		
		for(Box b : level.getField().getBoxList())
		{
			initialState.add(new BoxAtPredicate(b.getPosition()));
			knowledgeBase.add(new BoxAtPredicate(b.getPosition()));
		}
		
		Figure f = level.getFirstFigure();
		initialState.add(new FigureAtPredicate(f.getPosition()));
		knowledgeBase.add(new FigureAtPredicate(f.getPosition()));
		
		this.goal = new AndPredicate(predicateGoal);
		
	}

	@Override
	public AndPredicate getGoal() {
		return goal;
	}

	@Override
	public List<Predicate> getInitialState() {
		return initialState;
	}

	@Override
	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	@Override
	public List<SolidEntity> getAllEntities() {
		return null;
	}

	@Override
	public boolean isSatisfied(Predicate p) {
		return knowledgeBase.isContain(p);
	}

	@Override
	public PlanAction getActionForPredicate(Predicate p) {
		if(p instanceof BoxAtPredicate)
		{
			return new PushBox(level,level.getField().getBoxList().get(0),(Position2D)(p.getParams())[0]);
		}
		else if(p instanceof FigureAtPredicate)
		{
			return new MoveFigure(level,level.getFirstFigure(),(Position2D)(p.getParams())[0]);
		}
		else return null;
	}

}
