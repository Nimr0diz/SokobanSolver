package search;

public interface Action<T> {

	State<T> getResultState();
	void preformAction(State<T> s);
	boolean isValidate(State<T> s);
	double getCost();
	State<T> getOriginalState();
	
}
