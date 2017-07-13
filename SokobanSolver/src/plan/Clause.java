package plan;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Clause<T> {
	HashMap<Predicate<T>,Predicate<T>> set;
	
	public Clause() {
		set = new HashMap<Predicate<T>,Predicate<T>>();
	}
	
	public void add(Predicate<T> p)
	{
		set.put(p, p);
	}
	
	public boolean isContains(Predicate<T> p)
	{
		if(set.get(p)==null) return false;
		return true;
	}
	
	public void remove(Predicate<T> p)
	{
		set.remove(p);
	}
	
	public void removeContradicts(Predicate<T> p)
	{
		for(Map.Entry<Predicate<T>, Predicate<T>> entry : set.entrySet())
		{
			Predicate<T> tempPred = entry.getValue();
			if(p.contradicts(tempPred))
				set.remove(tempPred);
		}
	}
	
	public boolean isStaisfy(Predicate<T> p)
	{
		if(p.id=="?")
		{
			return (set.get(p) != null);
				
		}
		else
		{
			Predicate<T> same = set.get(p);
			if(same!=null)
				return same.getId() == p.getId();
			return false;
		}
	}
	
	public int size()
	{
		return set.size();
	}
	
	public Set<Predicate<T>> getSet()
	{
		return set.keySet();
	}
	
	public void addClause(Clause<T> clause)
	{
		set.putAll(clause.set);
	}



	
}
