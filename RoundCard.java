import java.util.ArrayList;


public class RoundCard extends Card {
	
	private int turns = 0;
	
	private int numTurns = 0;
	
	private boolean isStopCard = false;
	
	public RoundCard (String x, int t, int n, boolean s) {
		
		super (x);
		
		turns = t;
		
		numTurns = n;
		
		isStopCard = s;
		
	}
	
	public int getTurns () {
		
		return turns;
		
	}
	
	public int getNumTurns () {
		
		return numTurns;
		
	}
	
	public boolean getIsStopCard () {
		
		return isStopCard;
		
	}
	
}
