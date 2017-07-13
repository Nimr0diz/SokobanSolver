package plan;

public interface Planner<E> {
	Plan plan(Plannable<E> plannable);

}
