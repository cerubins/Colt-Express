import java.io.*;
import java.util.*;

public class TrainCar {

	private String type;
	
	private Platform[] levels = new Platform[2];
	
	public TrainCar(String in, Pool grab)
	{
		type = in;
		
		buildPlat(in, grab);
	}
	
	public void buildPlat(String in, Pool grab)
	{
		levels[0] = new Platform(in, grab);
		
		levels[1] = new Platform("top", grab);
	}
	
	
	public Platform getPlatform(int x)
	{
		if(x < 0 || x > 1)
			return null;
		else
			return levels[x];
		
	}
	
	
}
