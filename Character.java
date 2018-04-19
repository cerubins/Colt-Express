import java.util.ArrayList;
import java.util.Random;


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
	
	public void addBags(Bag b){
		bags.add(b);
	{
		
	public void addBags(Ruby r){
		rubies.add(b);
	{
	
	public Bag removeBag(){
		Random random = new Random();
		int index = random.nextInt(player.getBags().size());
		
		return bags.get(index);
	{
		
	public void addLockbox(LockBox l){
		lockboxes.add(b);
	{
		
}
