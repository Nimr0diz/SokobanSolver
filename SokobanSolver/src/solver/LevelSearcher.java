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
	
	public LevelSearcher(LevelSearcher levelSearcher)
	{
		this.level = new Level(levelSearcher.level);
		this.strToEntity = new HashMap<>(levelSearcher.strToEntity);
		this.entityToStr = new HashMap<>(levelSearcher.entityToStr);
		numberOfWalls = levelSearcher.numberOfWalls;
		numberOfBoxes= levelSearcher.numberOfBoxes;
		numberOfFigures= levelSearcher.numberOfFigures;
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
		se.setPosition(new Position2D(dest));
		level.addEntity(se);
		strToEntity.replace(entity+" "+id,se);
	}
	
	public List<Position2D> getNodes(Path<Position2D> path)
	{
		List<Position2D[]> list = new LinkedList<>();
		List<Action<Position2D>> actions = path.getActions(); 
		List<State<Position2D>> states = path.getStates();
		List<Position2D> positions = new LinkedList<Position2D>();
		Position2D firstFigPos =  new Position2D(path.getFirstState().getState());
		firstFigPos.move(((MoveAction)(actions.get(0))).getDirection().getOppositeDirection(), 1);
		positions.add(firstFigPos);
		for(int i=1;i<actions.size();i++)
		{
			MoveAction act = ((MoveAction)actions.get(i));
			if(act.getDirection()!=((MoveAction)actions.get(i-1)).getDirection())
			{
				//Position2D[] node = new Position2D[2];
				positions.add(states.get(i).getState());
				Position2D figPos= new Position2D(states.get(i).getState());
				figPos.move(act.getDirection().getOppositeDirection(), 1);
				//node[1] = figPos;
				positions.add(figPos);
			}
		}
		positions.add(path.getLastState().getState());
		
		return positions;
		
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
		return strToEntity.get(entity+" "+id);
	}
	
	public void removeEntity(String entity, String id)
	{
		SolidEntity se = strToEntity.get(entity+" "+id);
//		level.removeSolidEntity(se.getPosition());
		entityToStr.remove(se);
		strToEntity.remove(entity+" "+id);
	}
	
	public int getNumberOfBoxes()
	{
		return numberOfBoxes;
	}
}
