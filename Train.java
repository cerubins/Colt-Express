import java.util.*;

public class Train {

	ArrayList<TrainCar> cars = new ArrayList<TrainCar>();
	
	public Train()
	{
		randomizeCars();
	}
	
	public void randomizeCars()
	{
		//start with all 7 cars and remove 2 at random until stuff
		
		Pool grab = new Pool();
		
		cars.add(new TrainCar("mailroom", grab));
		
		cars.add(new TrainCar("dinner", grab));
		
		cars.add(new TrainCar("lounge", grab));
		
		cars.add(new TrainCar("coach", grab));
		
		cars.add(new TrainCar("tea", grab));
		
		cars.add(new TrainCar("rubyTea", grab));
		
		cars.add(new TrainCar("bar", grab));
		
		Collections.shuffle(cars);
		
		cars.add(0, new Engine(grab));
		
		cars.remove(cars.size()-1);
		
		cars.remove(cars.size()-1);
		
		cars.remove(cars.size()-1);
		
	}
	
	public ArrayList <TrainCar> getTrain () {
		
		return cars;
		
	}
	
	public TrainCar getTrainCar(int place)
	{
		if(place < 0 || place >= cars.size())
			return null;
		else
			return cars.get(place);
	}
	
}
