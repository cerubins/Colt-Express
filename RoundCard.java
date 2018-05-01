import java.util.ArrayList;


public class RoundCard extends Card {
	
	private String turns = "";
	
	private int numTurns = 0;
	
	private boolean isStopCard = false;
	
	public RoundCard (String x, String t, int n, boolean s) {
		
		super (x);
		
		turns = t;
		
		numTurns = n;
		
		isStopCard = s;
		
	}
	
	public String getTurns () {
		
		return turns;
		
	}
	
	public int getNumTurns () {
		
		return numTurns;
		
	}
	
	public boolean getIsStopCard () {
		
		return isStopCard;
		
	}
	
}
