
public class ActionCard extends Card {

	private String upOrDown = "";
	
	private Character character = null;
	
	public ActionCard (String x) {
		
		super (x);
		
	}
	
	public void setUD (String ud) {
		
		upOrDown = ud;
		
	}
	
	public String getUD () {
		
		return upOrDown;
		
	}
	
	public void setCharacter (Character c) {
		
		character = c;
		
	}
	
	public Character getCharacter () {
		
		return character;
		
	}
	
}
