package boot;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import model.Position2D;
import model.entities.SolidEntity;
import model.levels.Level;
import model.levels.loaders.MyTextLevelLoader;
import plan.Plan;
import plan.Plannable;
import plan.Strips;
import solver.SokobanPlannable;

public class Boot {
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String[] Args = {"D:\\level2.txt","D:\\output.txt"};
		Level level = new MyTextLevelLoader().loadLevel(new FileInputStream(Args[0]));
		Plannable<SolidEntity> sokSolver = new SokobanPlannable(level);
		Plan plan = new Strips<SolidEntity,Position2D>().plan(sokSolver);
		BufferedWriter bw = new BufferedWriter(new FileWriter(Args[1]));
		bw.write(plan.toString());
		bw.flush();
		bw.close();
	}
}
