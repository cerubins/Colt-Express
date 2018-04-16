import java.util.ArrayList;


public class Character extends Player{

	
	private String name;
	private ArrayList<Bag> bags = new ArrayList<Bag>();
	private ArrayList<Ruby> rubies = new ArrayList<Ruby>();
	private ArrayList<LockBox> lockboxes = new ArrayList<LockBox>();
	
	public Character(String n, Platform plat){
		currentPlat = plat;
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public Platform getCurrentPlatform(){
		return currentPlat;
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
}
