import java.uil.*;
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
		
		Collections.sort(monies);
		
		for(int i = 0; i < 6; i++)
		{
			rubies.add(new Ruby(500));
		}
		
		for(int i = 0; i<3; i++)
		{
			lockboxi.add(new LockBox(1000));
		}
		
	}
	
	

}
