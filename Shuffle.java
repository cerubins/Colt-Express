import java.util.ArrayList;
import java.util.Collections;


public class Shuffle {
	
	public Card [] shuffle () {
		
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
		
		Card [] toReturn = new Card [6];
		
		for (int i = 0; i < 6; i++) {
			
			toReturn [i] = a.get (i);
			
		}
		
		return toReturn;
		
	}

}
