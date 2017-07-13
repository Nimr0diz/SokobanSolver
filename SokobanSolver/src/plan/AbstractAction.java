package plan;

public abstract class AbstractAction<T> extends Predicate<T> implements PlanAction<T> {

	public AbstractAction(PredicateType type, String entity, String id, T value) {
		super(type, entity, id, value);
		// TODO Auto-generated constructor stub
	}

}
