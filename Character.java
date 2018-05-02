import java.util.ArrayList;
import java.util.Random;


public class Character extends Player{

	
	private String name;
	private String playerImage;
	private ArrayList<Bag> bags = new ArrayList<Bag>();
	private ArrayList<Ruby> rubies = new ArrayList<Ruby>();
	private ArrayList<LockBox> lockboxes = new ArrayList<LockBox>();
	private ArrayList <ActionCard> cardInventory = new ArrayList <ActionCard> ();
	private int x, y;
	
	public Character(String n, int c, int le, int x, int y){
		currentCar = c;
		currentLevel = le;
		name = n;
		
		switch(n){
			case "django":
				playerImage = "images/Django_Idle";
				break;
			case "ghost":
				playerImage = "images/Ghost_Idle";
				break;
			case "cheyenne":
				playerImage = "images/Cheyenne_Idle";
				break;
			case "tuco":
				playerImage = "images/Tuco_Idle";
				break;
			case "doc":
				playerImage = "images/Doc_Idle";
				break;
			case "belle":
				playerImage = "images/Belle_Idle";
				break;
		}
		
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
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getPlayerImage(), x, y, null);
	}
	
	public Image getPlayerImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(playerImage));
		return i.getImage();
	}
	
}
