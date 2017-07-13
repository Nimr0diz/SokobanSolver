package plan;

public class Predicate<T> {
	protected PredicateType type;
	protected String entity;
	protected String id;
	protected T value;
	
	public Predicate(PredicateType type,String entity, String id, T value) {
		this.type = type;
		this.entity = entity;
		this.id = id;
		this.value = value;
	}

	public PredicateType getType() {
		return type;
	}

	public void setType(PredicateType type) {
		this.type = type;
	}
	
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public boolean contradicts(Predicate<T> p)
	{
		return type==p.type && id.equals(p.id) && value.equals(p.value);
	}
	
	public boolean satisfies(Predicate<T> p)
	{
		return type==p.type && entity.equals(p.entity) && value.equals(p.value);
	}
	
	@Override
	public boolean equals(Object obj) {
		Predicate<T> p = (Predicate<T>)obj;
		return type==p.type && entity.equals(p.entity) && value.equals(p.value);
	}
	
	@Override
	public String toString() {
		return type.name()+"="+entity+" "+id+","+value;
	}
}
