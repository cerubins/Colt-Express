import java.util.*;
import java.io.*;
public class Pool {
	
	ArrayList<Ruby> rubies = new ArrayList<Ruby>();
	
	ArrayList<LockBox> lockboxi = new ArrayList<LockBox>();
	
	ArrayList<Bag> monies = new ArrayList<Bag>();
	
	
	public Pool()
	{
		initial();
	}
	
	public void initial()
	{
		for(int i = 0; i < 8; i++)
		{
			monies.add(new Bag(250));
		}
		
		for(int i = 0; i < 2; i++)
		{
			monies.add(new Bag(300));
		}
		
		for(int i = 0; i < 2; i++)
		{
			monies.add(new Bag(350));
		}
		
		for(int i = 0; i < 2; i++)
		{
			monies.add(new Bag(400));
		}
		
		for(int i = 0; i < 2; i++)
		{
			monies.add(new Bag(450));
		}
		
		for(int i = 0; i < 2; i++)
		{
			monies.add(new Bag(500));
		}
		
		for(int i = 0; i < 6; i++)
		{
			rubies.add(new Ruby());
		}
		
		for(int i = 0; i<3; i++)
		{
			lockboxi.add(new LockBox());
		}
		
		Collections.shuffle(monies);
		
	}
	
	
	public Bag getRandomBag()
	{
		if(monies.size() != 0)
			return monies.remove(0);
		else
			return null;
	}
	
	
	public Ruby getRuby()
	{
		if(rubies.size() != 0)
			return rubies.remove(0);
		else
			return null;
	}
	
	public LockBox getLB()
	{
		if(lockboxi.size() != 0)
			return lockboxi.remove(0);
		else
			return null;
	}

}
