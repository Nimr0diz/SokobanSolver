package plan;

import model.Position2D;

public class SokobanPredicate extends Predicate<Position2D> {

	public SokobanPredicate(PredicateType type, String entity, String id, Position2D value) {
		super(type, entity, id, value);
	}
	
	@Override
	public boolean contradicts(Predicate<Position2D> pre) {
		SokobanPredicate p = (SokobanPredicate)pre;
		return 	(entity.equals(p.entity) && id.equals(p.id) && !value.equals(p.value) ) || //Same Entity but different position  
				(type.equals(p.type) && !entity.equals(p.entity) && value.equals(p.value)) || //Same Position but different entity type
				(type.equals(p.type) && entity.equals(p.entity) && id.equals(p.id) && !value.equals(p.value)); //Same entity type ,predicate type and position but different entity id
	}
	
	@Override
	public boolean satisfies(Predicate<Position2D> pre) {
		SokobanPredicate p = (SokobanPredicate)pre;
		return 	( type.equals(p.type) && entity.equals(p.entity) && id.equals(p.id) && value.equals(p.value) ) ||  //Same Predicate
				( type.equals(p.type) && entity.equals(p.entity) && p.equals("?") && value.equals(p.value) ); //Generic Predicate
	}
	
	@Override
	public int hashCode() {
		return (type+" "+entity+" "+value.getX()+" "+value.getY()).hashCode();
	}
}
