import java.util.ArrayList;


public class Character extends Player{

	
	private String name;
	private ArrayList<Bag> bags = new ArrayList<Bag>();
	private ArrayList<Ruby> rubies = new ArrayList<Ruby>();
	private ArrayList<LockBox> lockboxes = new ArrayList<LockBox>();
	
	public Character(String n, TrainCar c, int le){
		currentCar = c;
		currentLevel = le;
		name = n;
		
	}
	
	public String getName(){
		return name;
	}
	
	public TrainCar getCurrentCar(){
		return currentCar;
	}
	
	public ArrayList<Bag> getBags(){
		return bags;
	}
	public ArrayList<Ruby> getRubies(){
		return rubies;
	}
	public ArrayList<LockBox> getLockboxes(){
		return lockboxes;
	}
	public int getLevel(){
		return currentLevel;
	}
}
