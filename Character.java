import java.util.ArrayList;
import java.util.Random;


public class Character extends Player{

	
	private String name;
	private ArrayList<Bag> bags = new ArrayList<Bag>();
	private ArrayList<Ruby> rubies = new ArrayList<Ruby>();
	private ArrayList<LockBox> lockboxes = new ArrayList<LockBox>();
	private ArrayList <ActionCard> cardInventory = new ArrayList <ActionCard> ();
	
	public Character(String n, int c, int le){
		currentCar = c;
		currentLevel = le;
		name = n;
		
		for (int i = 0; i < 6; i++) {
			
			cardInventory.add(new ActionCard ("bullet"));
			
		}
		
	}
	
	public ActionCard getBulletCard () {
		
		if (cardInventory.size () > 0) {
			
			return cardInventory.remove (0);
			
		}
		
		return null;
		
	}
	
	public String getName(){
		return name;
	}
	
	public int getCurrentCar(){
		return currentCar;
	}
	
	public void updateCurrentCar (int x) {
		
		currentCar = x;
		
	}
	
	public void addBags(Bag b){
		bags.add(b);
	}
		
	public void addBags(Ruby r){
		rubies.add(r);
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
	
	public void updateLevel (int x) {
		
		currentLevel = x;
		
	}
	
	public Bag removeBag(){
		Random random = new Random();
		int index = random.nextInt(bags.size());
		
		return bags.get(index);
	}
	
	
}