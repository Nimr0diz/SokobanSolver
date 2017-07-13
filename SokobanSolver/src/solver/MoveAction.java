package solver;

import commons.Direction2D;
import model.Position2D;
import model.entities.SolidEntity;
import search.Action;
import search.State;

public class MoveAction implements Action<Position2D> {
	State<Position2D> originalState;
	State<Position2D> resultState;
	SolidEntity se;
	Direction2D direction;
	int cost;
	
	public MoveAction(State<Position2D> originalState,SolidEntity se,Direction2D direction) {
		this.originalState = originalState;
		this.se = se;
		this.direction = direction;
		resultState = null;
		cost = 1;
	}
	@Override
	public State<Position2D> getResultState() {
		return resultState;
	}

	@Override
	public void preformAction(State<Position2D> s) {
		Position2D newPos  = new Position2D(originalState.getState());
		se.getPolicy().getMovement().move(newPos,direction);
		resultState = new State<Position2D>(newPos);
		resultState.setCameFrom(originalState);
		resultState.setActionCameFrom(this);
		resultState.setCost(originalState.getCost()+getCost());
		if(s.getActionCameFrom()!=null)
			if(((MoveAction)s.getActionCameFrom()).getDirection()!=direction)
				cost=3;
		
	}

	@Override
	public boolean isValidate(State<Position2D> s) {
		return true;
	}

	@Override
	public double getCost() {
		return cost;
	}
	
	@Override
	public String toString() {
		return "MoveAction("+direction+")";
		
	}
	
	public SolidEntity getEntity() {
		return se;
	}
	public Direction2D getDirection() {
		return direction;
	}
	@Override
	public State<Position2D> getOriginalState() {
		return originalState;
	}


}
