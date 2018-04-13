import java.io.*;
import java.util.*;

public class Platform {
	
	private ArrayList<Character> people = new ArrayList<Character>();
	
	private Inventory inv;
	
	
	
	public Platform()
	{
		
	}
	
	public void initialize(String type)
	{
		switch(type)
		{
			case("mailroom"):
			{
				//getRandomMoneybag * 4
				//getRandomRuby
				break;
			}
			case("dinner"):
			{
				//getRandomMoneybag * 3
				break;
			}
			case("lounge"):
			{
				//getRandomMoneybag * 3
				break;
			}
			case("coach"):
			{
				//getRandomMoneybag
				break;
			}
			case("tea"):
			{
				//getRandomMoneybag * 3
				//getRandomRuby
				break;
			}
			case("rubyTea"):
			{
				//getRandomRuby * 3
				break;
			}
			case("bar"):
			{
				//getRandomMoneybag
				//getRandomRuby
			}
			case("engine"):
			{
				//getLockbox
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
