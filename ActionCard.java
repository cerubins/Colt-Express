

public class ActionCard extends Card {

	boolean up = true;
	
	private Character character = null;
	
	public ActionCard (String x) {
		
		super (x);
		
	}
	
	public void setUp (boolean b) {
		
		up = b;
		
	}
	
	public boolean isUp () {
		
		return up;
		
	}
	
	public void setCharacter (Character c) {
		
		character = c;
		
	}
	
	public Character getCharacter () {
		
		return character;
		
	}
	
}
