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
		makeBags();
		makeRubies();
		makeLockBoxes();
	}
	public void makeBags()
	{
		for (int x = 0; x<8; x++)
		{
			bags.add(new Bag(250));
		}
		int count = 0;
		int num = 250;
		for (int x = 0; x<10; x++)
		{
			if (count%2==0)
			{
				num+=50;
			}
			bags.add(new Bag(num)); 
			count++;
		}
	}
	public ArrayList<Bag> getBags()
	{
		return bags;
	}
	public void makeRubies()
	{
		for (int x = 0; x<6; x++)
		{
			rubies.add(new Ruby());
		}
	}
	public ArrayList<Ruby> getRubies()
	{
		return rubies;
	}
	public void makeLockBoxes()
	{
		lockBoxes.add(new LockBox());
		lockBoxes.add(new LockBox());
	}
	public ArrayList<LockBox> getLockBoxes()
	{
		return lockBoxes;
	}
	
	
}
