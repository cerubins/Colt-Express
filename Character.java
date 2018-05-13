import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;


public class Character extends Player{

	
	private String name;
	private Image playerImage;
	private ArrayList<Bag> bags = new ArrayList<Bag>();
	private ArrayList<Ruby> rubies = new ArrayList<Ruby>();
	private ArrayList<LockBox> lockboxes = new ArrayList<LockBox>();
	private int x, y;
	
	public Character(String n, int c, int le, int x1, int y1){
		currentCar = c;
		currentLevel = le;
		name = n;
		x = x1;
		y = y1;
		
	}
	
	public void setImage (Image img) {
		
		playerImage = img;
		
	}
	
	public Image getImage () {
		
		return playerImage;
		
	}
	
	public int getX () {
		
		return x;
		
	}
	
	public int getY () {
		
		return y;
		
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
		
	public void addRubies(Ruby r){
		rubies.add(r);
	}
	public void addLockBoxes(LockBox L)
	{
		lockboxes.add(L);
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
		bags.remove(index);
		return bags.get(index);
	}
	public Ruby removeRuby()
	{
		int index = (int)(Math.random()*bags.size());
		rubies.remove(index);
		return rubies.get(index);
	}
	public LockBox removeLockBox()
	{
		int index = (int)(Math.random()*bags.size());
		lockboxes.remove(index);
		return lockboxes.get(index);
	}
	
	public void update(){
		
	}
	
	public int getTotal()
	{
		int sum = 0;
		for (int y = 0; y<getRubies().size(); y++)
		{
			sum+=getRubies().get(y).getAmount();
		}
		for (int y = 0; y<getBags().size(); y++)
		{
			sum+=getBags().get(y).getAmount();
		}
		for (int y = 0; y<getLockboxes().size(); y++)
		{
			sum+=getLockboxes().get(y).getAmount();
		}
		return sum;
	}
	
}


