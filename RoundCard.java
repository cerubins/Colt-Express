import java.util.ArrayList;


public class RoundCard extends Card {
	
	int guiSymbol = 0;
	
	int numTurns = 0;
	
	boolean isStopCard = false;
	
	public RoundCard (String x, int g, int n, boolean s) {
		
		super (x);
		
		guiSymbol = g;
		
		numTurns = n;
		
		isStopCard = s;
		
	}
	
	public int getGuiSymbol () {
		
		return guiSymbol;
		
	}
	
	public int getNumTurns () {
		
		return numTurns;
		
	}
	
	public boolean getIsStopCard () {
		
		return isStopCard;
		
	}
	
}
