package plan;

public class Predicate<T> {
	String type;
	String entity;
	String id;
	T value;
	
	public Predicate(String type,String entity, String id, T value) {
		this.type = type;
		this.entity = entity;
		this.id = id;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
		return false;
	}
	
}
