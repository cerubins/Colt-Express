import java.util.ArrayList;
import java.util.Collections;


public class Shuffle {
	
	public ArrayList <ActionCard> getAllCards () {
		
		ArrayList <ActionCard> a = new ArrayList <ActionCard> ();
		
		a.add (new ActionCard ("move"));
		
		a.add (new ActionCard ("move"));
		
		a.add (new ActionCard ("changeFloor"));
		
		a.add (new ActionCard ("changeFloor"));
		
		a.add (new ActionCard ("shoot"));
		
		a.add (new ActionCard ("shoot"));
		
		a.add (new ActionCard ("loot"));
		
		a.add (new ActionCard ("loot"));
		
		a.add (new ActionCard ("marshall"));
		
		a.add (new ActionCard ("punch"));
		
		Collections.shuffle (a);
		
		ArrayList <ActionCard> toReturn = new ArrayList <ActionCard> ();
		
		for (int i = 0; i < 10; i++) { // NEED TO CHECK IF CHARACTER HAS BULLET CARDS
			
			toReturn.add (a.get (i));
			
		}
		
		return toReturn;
		
	}

}
