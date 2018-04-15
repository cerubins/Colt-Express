import java.io.*;
import java.util.*;

public class Platform {
	
	private ArrayList<Character> people = new ArrayList<Character>();
	
	private Inventory inv;
	
	
	public Platform()
	{
		
	}
	
	public Platform(String in, Pool grab)
	{
		initialize(in, grab);
	}
	
	public void initialize(String type, Pool grab)
	{
		switch(type)
		{
			case("mailroom"):
			{
				for(int i = 0; i<4; i++)
				{
					inv.addBags(grab.getRandomBag());
				}
				
				inv.addRuby(grab.getRuby());
				//getRandomMoneybag * 4
				//getRandomRuby
				break;
			}
			case("dinner"):
			{
				for(int i = 0; i<3; i++)
				{
					inv.addBags(grab.getRandomBag());
				}
				
				//getRandomMoneybag * 3
				break;
			}
			case("lounge"):
			{
				for(int i = 0; i<3; i++)
				{
					inv.addBags(grab.getRandomBag());
				}
				
				//getRandomMoneybag * 3
				
				break;
			}
			case("coach"):
			{
				inv.addBags(grab.getRandomBag());
				
				//getRandomMoneybag
				break;
			}
			case("tea"):
			{
				for(int i = 0; i<3; i++)
				{
					inv.addBags(grab.getRandomBag());
				}
				
				inv.addRuby(grab.getRuby());
				
				//getRandomMoneybag * 3
				//getRandomRuby
				break;
			}
			case("rubyTea"):
			{
				for(int i = 0; i<3; i++)
				{
					inv.addRuby(grab.getRuby());
				}
				
				//getRandomRuby * 3
				break;
			}
			case("bar"):
			{
				inv.addBags(grab.getRandomBag());
				
				inv.addRuby(grab.getRuby());
				
				//getRandomMoneybag
				//getRandomRuby
			}
			case("engine"):
			{
				inv.addLockBox(grab.getLB());
				
				//getLockbox
				
				break;
			}
			case("top"):
			{
				break;
			}
			default:
			{
				//whoops
				break;
			}
			
		}
	
	}
}
