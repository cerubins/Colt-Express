import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;


public class Round {
	
	Train train = null;
	
	private RoundCard roundCard = null;
	
	private ArrayList <Character> characterList = new ArrayList <Character> ();
	
	private int numTurns = 0;
	
	private String turns = "";
	
	private ArrayList <Integer> whenUp = new ArrayList <Integer> (), whenDown = new ArrayList <Integer> ();
	
	private boolean reverseOrder = false;
	
	private TreeMap <Character, ArrayList <ActionCard>> hands = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private Queue <ActionCard> cards = new LinkedList <ActionCard> ();
	
	Manipulate manipulate = new Manipulate ();
	
	public Round (Train t) { // CONSTRUCTOR TO PASS IN TRAIN
		
		train = t;
		
	}
	
	public Round (Train t, ArrayList <Character> cl) { // *USE THIS ONE!! MUST PASS IN CHARACTER LIST FROM GAME CLASS!!* CONSTRUCTOR TO PASS IN TRAIN AND CHARACTER LIST
		
		train = t;
		
		characterList = cl;
		
	}
			
	public void setRoundCard () { // SELECTS THE ROUND CARD, DETERMINES IN WHICH TURNS CARDS ARE PUT IN UP OR DOWN, AND CHECKS IF QUEUE ORDER IS REVERSED
		
		RoundCardSelector r = new RoundCardSelector ();
		
		roundCard = r.select ();
		
		numTurns = roundCard.getNumTurns();
		
		turns = roundCard.getTurns () + "";
		
		for (int i = 0; i < turns.length (); i++) {
			
			if (turns.charAt(i) == '1') {
				
				whenUp.add (i);
				
			}
			else if (turns.charAt(i) == '0') {
				
				whenDown.add (i);
				
			}
			
		}
		
		if (turns.contains("2")) {
			
			reverseOrder = true;
			
		}
		
	}
	
	public void setCharacterList () { // *SHOULD NOT HAVE TO USE THIS METHOD!! INSTEAD, PASS IN CHARACTER LIST FROM GAME CLASS AND ROTATE IT!!*
		
		for (int i = 0; i < train.getTrain ().size (); i++) {
			
			TrainCar car = train.getTrain ().get (i);
			
			Platform p0 = car.getPlatform (0);
			
			Platform p1 = car.getPlatform (1);
			
			characterList.addAll (p0.getCharacterList ());
			
			characterList.addAll (p1.getCharacterList ());
			
		}
		
	}
	
	public void assignCards () { // ASSIGNS 6 CARDS TO EACH CHARACTER USING THE MAP
		
		for (int i = 0; i < characterList.size (); i++) {
			
			Shuffle s = new Shuffle ();
			
			ArrayList <ActionCard> hand = s.getCards();
			
			hands.put (characterList.get (i), hand);
			
		}
		
	}
	
	public void goAroundTurns () { // DOES TURNS CHECKING IF CARDS SHOULD BE PUT IN UP OR DOWN
		
		for (int i = 0; i < numTurns; i++) {
			
			if (whenUp.contains (i)) {
				
				goAroundCharacters ("up");
				
			}
			
			if (whenDown.contains (i)) {
				
				goAroundCharacters ("down");
				
			}
			
		}
		
	}
	
	public void goAroundCharacters (String ud) { // DOES EACH TURN, GIVES EACH CHARACTER IN CHARACTER LIST CHANCE TO PUT DOWN ONE CARD
		
		for (int i = 0; i < characterList.size (); i++) {
			
			atCharacter (characterList.get (i), ud);
			
		}
		
	}
	
	public void atCharacter (Character c, String ud) { // DOES EACH CHARACTER, ALLOWS THEM TO CHOOSE A CARD FROM 6 AVAILABLE CARDS AND INSERT INTO QUEUE
		
		ArrayList <ActionCard> hand = hands.get (c);
		
		ActionCard toAdd = selectCard (hand);
		
		toAdd.setUD (ud);
		
		toAdd.setCharacter (c);
		
		cards.add (toAdd);
		
	}
	
	public ActionCard selectCard (ArrayList <ActionCard> h) { // GUI METHOD ALLOWS CHARACTER TO SELECT CARD FROM POSSIBLE OPTIONS PASSED IN
		
		// *NEED TO DO ACTIONCARD SELECT WITH GUI HERE!! THIS IS JUST A PLACEHOLDER!!*
		
		return h.get (0);
		
	}
	
	public void play () { // CHECKS IF REVERSE ORDER AND GOES THROUGH QUEUE PLAYING EACH CARD BY CALLING MANIPULATE ACTION METHOD
		
		if (reverseOrder) {
			
			cards = reverseCards (cards);
			
		}
		
		while (!cards.isEmpty()) {
			
			ActionCard c = cards.remove ();
			
			manipulate.action (train, c, c.getCharacter ());
			
			// Manipulate: action (Train, ActionCard, Character);
			
		}
		
	}
	
	public Queue <ActionCard> reverseCards (Queue <ActionCard> c) { // REVERSES QUEUE
		
		Stack <ActionCard> temp = new Stack <ActionCard> ();
		
		while (!c.isEmpty ()) {
			
			temp.push (c.remove ());
			
		}
		
		Queue <ActionCard> toReturn = new LinkedList <ActionCard> ();
		
		while (!temp.isEmpty ()) {
			
			toReturn.add (temp.pop ());
			
		}
		
		return toReturn;
		
	}
	
	
}
