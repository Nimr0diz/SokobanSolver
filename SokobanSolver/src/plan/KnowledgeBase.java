package plan;

import java.util.HashSet;

public class KnowledgeBase {

	HashSet<Predicate> hs;
	
	public KnowledgeBase() {
		hs=new HashSet<Predicate>();
	}
	
	public boolean isContain(Predicate p)
	{
		return hs.contains(p);
	}
	
	public void add(Predicate p)
	{
		hs.add(p);
	}
}
