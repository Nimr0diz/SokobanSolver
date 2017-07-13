package plan;

import java.util.HashMap;
import java.util.HashSet;

import fail.IPredicate;

public class KnowledgeBase<T> {

	HashMap<PredicateType,Predicate<T>> hm;
	
	public KnowledgeBase() {
		hm=new HashMap<PredicateType,Predicate<T>>();
	}
	
	public boolean isContain(Predicate<T> p)
	{
		if(hm.get(p)==null) return false;
		return true;
	}
	
	public void add(Predicate<T> p)
	{
		hm.put(p.type,p);
	}
	
	public void remove()
	{
		if(isContain(p))
			hm.remove(p);
	}
}
