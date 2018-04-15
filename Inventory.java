import java.util.ArrayList;

public class Inventory 
{
	private ArrayList<Bag> bags; 
	private ArrayList<Ruby> rubies;
	private ArrayList<LockBox> lockBoxes;
	public Inventory()
	{
		
		bags = new ArrayList<>();
		
		rubies = new ArrayList<>();
		
		lockBoxes = new ArrayList<>();
		
	}

	public ArrayList<Bag> getBags()
	{
		return bags;
	}
	
	public void addBags(Bag in)
	{
		bags.add(in);
	}
	
	public ArrayList<Ruby> getRubies()
	{
		return rubies;
	}
	
	public void addRuby(Ruby in)
	{
		rubies.add(in);
	}
	
	public ArrayList<LockBox> getLockBoxes()
	{
		return lockBoxes;
	}
	
	public void addLockBox(LockBox in)
	{
		lockBoxes.add(in);
	}
	
	
}
