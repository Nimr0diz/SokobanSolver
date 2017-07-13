package boot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import commons.exceptions.SokobanException;
import model.levels.Level;
import model.levels.loaders.LevelLoader;
import model.levels.loaders.MyObjectLevelLoader;
import model.levels.loaders.MyTextLevelLoader;
import model.levels.loaders.MyXMLLevelLoader;

public class LoadLevelCommand  {
	//The HashMap link each text file extension to the specific LevelLoader
	HashMap<String,LevelLoader> levelLoaderList;
	
	public LoadLevelCommand() {
		//Initializing the HashMap.
		levelLoaderList = new HashMap<String,LevelLoader>();
		
		levelLoaderList.put("txt", new MyTextLevelLoader());
		levelLoaderList.put("obj", new MyObjectLevelLoader());
		levelLoaderList.put("xml", new MyXMLLevelLoader());
		
	}
	public Level execute(Level level, String filename) throws ClassNotFoundException, FileNotFoundException, IOException, SokobanException {
		String filetype = filename.substring(filename.lastIndexOf(".")+1);//Get the letters after the '.' to get the file type
		LevelLoader ll= levelLoaderList.get(filetype);
		level = ll.loadLevel(new FileInputStream(filename));
		return level;

	}

}
