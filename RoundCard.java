import java.util.ArrayList;


public class RoundCard extends Card {
	
	int type = 0;
	
	int numTurns = 0;
	
	boolean isStopCard = false;
	
	public RoundCard (String x, int t, int n, boolean s) {
		
		super (x);
		
		type = t;
		
		numTurns = n;
		
		isStopCard = s;
		
	}
	
	public int getType () {
		
		return type;
		
	}
	
	public int getNumTurns () {
		
		return numTurns;
		
	}
	
	public boolean getIsStopCard () {
		
		return isStopCard;
		
	}
	
}
