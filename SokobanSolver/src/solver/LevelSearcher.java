package solver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.Position2D;
import model.entities.Box;
import model.entities.Figure;
import model.entities.SolidEntity;
import model.entities.Wall;
import model.levels.Level;
import search.Action;
import search.BFS;
import search.Path;
import search.Searchable;
import search.Searcher;
import search.State;

public class LevelSearcher {
	Level level;
	HashMap<String,SolidEntity> strToEntity;
	HashMap<SolidEntity,String> entityToStr;
	int numberOfWalls,numberOfFigures,numberOfBoxes;
	
	public LevelSearcher(Level level) {
		this.level = level;
		strToEntity = new HashMap<>();
		entityToStr = new HashMap<>();
		numberOfWalls = 0;
		numberOfBoxes = 0;
		numberOfFigures = 0;
		//initLevelSearcher();
	}

	private void initLevelSearcher() {
		
	}
	
	public Path<Position2D> searchMovePath(String id, Position2D dest)
	{
		SolidEntity se = strToEntity.get("Figure "+id);
		Searchable<Position2D> searchable = new FigurePathSearchable(level, se, dest);
		Searcher<Position2D> BFS = new BFS<Position2D>();
		return BFS.search(searchable);
	}
	
	public Path<Position2D> searchPushPath(String entity,String id, Position2D dest)
	{
		SolidEntity se = strToEntity.get("Box "+id);
		Searcher<Position2D> BFS = new BFS<Position2D>();
		Searchable<Position2D> searchable= new BoxPathSearchable(level, se , dest);
		return BFS.search(searchable);
	}
	
	public void updatePosition(String entity,String id,Position2D dest)
	{
		SolidEntity se = strToEntity.get(entity+" "+id);
		level.removeSolidEntity(se.getPosition());
		se.setPosition(dest);
		level.addEntity(se);
	}
	
	public List<Position2D[]> getNodes(Path<Position2D> path)
	{
		List<Position2D[]> list = new LinkedList<>();
		List<Action<Position2D>> actions = path.getActions(); 
		List<State<Position2D>> states = path.getStates();
		for(int i=1;i<actions.size();i++)
		{
			MoveAction act = ((MoveAction)actions.get(i));
			if(act.getDirection()!=((MoveAction)actions.get(i-1)).getDirection())
			{
				Position2D[] node = new Position2D[2];
				node[0] = states.get(i).getState();
				Position2D figPos= new Position2D(node[0]);
				figPos.move(act.getDirection().getOppositeDirection(), 1);
				node[1] = figPos;
				list.add(node);
			}
		}
		Position2D[] node = new Position2D[2];
		node[0] = path.getLastState().getState();
		node[1] = path.getBeforeLastState().getState();
		list.add(node);
		return list;
		
	}
	
	public String addEntity(SolidEntity se)
	{
		String name="";
		if(se instanceof Wall)
			name = "Wall "+numberOfWalls++;
		else if(se instanceof Box)
			name = "Box "+numberOfBoxes++;
		else if(se instanceof Figure)
			name = "Figure "+numberOfFigures++;
		
		strToEntity.put(name, se);
		entityToStr.put(se, name);
		return name;
		
	}
	
	public SolidEntity getEntity(String entity, String id)
	{
		return strToEntity.get(entity+id);
	}
}
