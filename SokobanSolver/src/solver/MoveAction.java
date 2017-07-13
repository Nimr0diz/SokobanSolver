package solver;

import commons.Direction2D;
import model.Position2D;
import model.entities.SolidEntity;
import search.Action;
import search.State;

public class MoveAction implements Action<Position2D> {
	State<Position2D> resultState;
	SolidEntity se;
	Direction2D direction;
	
	public MoveAction(SolidEntity se,Direction2D direction) {
		this.se = se;
		this.direction = direction;
		resultState = null;
	}
	@Override
	public State<Position2D> getResultState() {
		return resultState;
	}

	@Override
	public void preformAction(State<Position2D> s) {
		Position2D newPos  = s.getState();
		se.getPolicy().getMovement().move(newPos,direction);
		resultState = new State<Position2D>(newPos);
		resultState.setCameFrom(s);
		resultState.setActionCameFrom(this);
		resultState.setCost(s.getCost()+getCost());
		
	}

	@Override
	public boolean isValidate(State<Position2D> s) {
		return true;
	}

	@Override
	public double getCost() {
		return 1;
	}
	
	@Override
	public String toString() {
		return "MoveAction("+direction+")";
		
	}
	
	@Override
	public boolean equals(Object obj) {
		return direction==((MoveAction)obj).direction;
	}
	
	public SolidEntity getSe() {
		return se;
	}
	public Direction2D getDirection() {
		return direction;
	}


}
