import java.util.*;
public class Player {
	private int money;
	private int bullets = 6;
	private Stack<ActionCard> cardDrawPile = new Stack<ActionCard>();
	
	public int currentCar;
	public int currentLevel;
	public  Platform currentPlat;
}
