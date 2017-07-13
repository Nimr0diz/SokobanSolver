package boot;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import commons.exceptions.SokobanException;
import model.Position2D;
import model.levels.Level;
import model.levels.loaders.LevelLoader;
import model.levels.loaders.MyObjectLevelLoader;
import model.levels.loaders.MyTextLevelLoader;
import model.levels.loaders.MyXMLLevelLoader;
import plan.AbstractPlannable;
import plan.Plan;
import plan.Strips;
import solver.SokobanPlannable;

public class Boot {
	public static void main(String[] args) throws FileNotFoundException, IOException, CloneNotSupportedException, ClassNotFoundException, SokobanException
	{
		//String[] Args = {"D:\\level3.txt","D:\\output.txt"};
		Level level;
		LevelLoader ll = getLevelLoader(args[0]);
		if(ll!=null)
			level = ll.loadLevel(new FileInputStream(args[0]));
		else return;
		AbstractPlannable<Position2D> sokSolver = new SokobanPlannable(level);
		Plan<Position2D> plan = new Strips<Position2D>().plan(sokSolver);
		BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
		for(String line : plan.printPlan())
		{
			bw.write(line);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	public static LevelLoader getLevelLoader(String filename)
	{
		String filetype = filename.substring(filename.lastIndexOf(".")+1);
		if(filetype.equalsIgnoreCase("txt")) return new MyTextLevelLoader();
		if(filetype.equalsIgnoreCase("xml")) return new MyXMLLevelLoader();
		if(filetype.equalsIgnoreCase("obj")) return new MyObjectLevelLoader();
		return null;
	}
}
