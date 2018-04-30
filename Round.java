import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;


public class Round {
	
	private Train tr;
	
	private ArrayList <Character> cList = new ArrayList <Character> ();
	
	private TreeMap <Character, ArrayList <ActionCard>> hands = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private TreeMap <Character, ArrayList <ActionCard>> bulletCards = new TreeMap <Character, ArrayList <ActionCard>> ();
		
	private TreeMap <Character, ArrayList <ActionCard>> discard = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private TreeMap <Character, ArrayList <ActionCard>> draw = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private RoundCard roundCard;
	
	private String [] roundCardWhatItDoes;
	
	public Round (Train t, ArrayList <Character> c) { // NEED TO PASS EVERYTHING IN HERE!!
		
		tr = t;
		
		cList = c;
		
	}
	
	public void First () { // PUTS 6 CARDS IN HAND AND 4 IN DRAW PILE, PUT 6 BULLET CARDS FOR EACH CHARACTER (INCLUDING MARSHALL, WHO IS A CHARACTER BUT NOT PLAYABLE)
		
		RoundCardSelector rs = new RoundCardSelector ();
		
		roundCard = rs.select ();
		
		for (int i = 0; i < cList.size (); i++) {
			
			Shuffle s = new Shuffle ();
			
			draw.put (cList.get(i), s.getAllCards());
			
		}
		
		for (int i = 0; i < cList.size (); i++) {
			
			ArrayList <ActionCard> topSix = getTopSixFirst (draw.get (cList.get (i)));
			
			hands.put (cList.get (i), topSix);
			
		}
		
		for (int i = 0; i < cList.size (); i++) {
			
			ArrayList <ActionCard> temp = new ArrayList <ActionCard> ();
			
			for (int j = 0; j < 6; j++) {
				
				temp.add (new ActionCard ("bullet"));
				
			}
			
			bulletCards.put (cList.get (i), temp);
			
		}
		
	}
	
	private ArrayList <ActionCard> getTopSixFirst (ArrayList <ActionCard> c) {
		
		ArrayList <ActionCard> toReturn = new ArrayList <ActionCard> ();
		
		for (int i = 0; i < 6; i++) {
			
			toReturn.add (c.remove (i));
			
		}
		
		return toReturn;
		
	}
	
	public void playRound () {
		
		
		
	}
	
	public void startOfRound () {
		
		
		
	}
	
	public void action(ActionCard card, Character player){ // LEFT IS FORWARD, RIGHT IS BACKWARD
		int car = player.getCurrentCar();
		switch(card.getWhatItDoes()){
		case "punch": {
			
			ArrayList<Character> list = tr.getTrainCar(car).getPlatform(player.getLevel()).getCharacterList();
			//ASK PLAYER FOR PLAYER TO PUNCH, PUT IN STRING 'VICTIM', ask player for direction to punch, put in string 'direction'
			String victim = "";
			String direction = "";
			for(int x = 0; x < list.size(); x++){
				if(list.get(x).getName().equals(victim)){
					Character vic = player.currentPlat.removePlayer(victim);
					
					if(player.getName().equals("cheyenne")){
						player.addBags(vic.removeBag());
					}
					
					if(direction.equals("right")){
						tr.getTrainCar(player.getCurrentCar()+1).getPlatform(player.currentLevel).addPlayer(vic);
						
						player.updateCurrentCar(player.getCurrentCar()+1);
						
					}
					else if(direction.equals("left")){
						tr.getTrainCar(player.getCurrentCar()-1).getPlatform(player.currentLevel).addPlayer(vic);
						player.updateCurrentCar(player.getCurrentCar()-1);
						
					}
				}
			}
		} break;	
		case "shoot": {
			
			TreeMap <Integer, ArrayList <Character>> possibleShoots = new TreeMap <Integer, ArrayList <Character>> ();
			
			if (player.getName ().equals ("django")) {
				
				
				
			}
			
			if (player.getName ().equals ("tuco")) {
				
				
				
			}
			
			if (player.currentLevel == 0) {
				
				if (player.currentCar - 1 >= 0 && tr.getTrainCar(player.currentCar - 1).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
					
					possibleShoots.put (player.currentCar - 1, tr.getTrainCar(player.currentCar - 1).getPlatform(player.currentLevel).getCharacterList());
				
				}
				
				if (player.currentCar + 1 >= 0 && player.currentCar + 1 <= 4 && tr.getTrainCar(player.currentCar + 1).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
					
					possibleShoots.put (player.currentCar + 1, tr.getTrainCar(player.currentCar + 1).getPlatform(player.currentLevel).getCharacterList());
				
				}
				
			}
			
			if (player.currentLevel == 1) {
				
				for (int i = player.currentCar; i <= 4; i++) {
					
					if (tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
						
						possibleShoots.put (i, tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList());
						
						break;
					}
					
				}
				
				for (int i = 0; i < player.currentCar; i++) {
					
					if (tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
						
						possibleShoots.put (i, tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList());
						
						break;
						
					}
					
				}
				
			}
			
			Character selected = new Character ("", car, car); // GIVE OPTIONS ON WHOM TO SHOOT BASED ON MAP POSSIBLE SHOOTS, SELECT CHARACTER TO SHOOT AND POINT SELECTED AT IT (INITIALIZED ONLY TO PREVENT ERROR FOR NOW, DOESN'T ACTUALLY WORK)
			
			hands.get (selected).add ();
			
			
		} break;
		case "move":{ // NEED TO FIX BY GIVING OPTION FOR 3 MOVE
			String d = "";
			
			int level = player.currentLevel;
			
			int currentLoc = player.currentCar;
			
			tr.getTrainCar (currentLoc).getPlatform(level).removePlayer(player.getName());
			
			if (level == 0) {
				
				// ASK PLAYER FOR DIRECTION, PUT "1 LEFT" OR "1 RIGHT" (FORWARD OR BACKWARD RESPECTIVELY) (LOWERCASE) IN STRING d
				
				if (d.equals ("1 left")  && currentLoc - 1 >= 0) {
					
					tr.getTrainCar(currentLoc - 1).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc - 1);
					
				}
				else if (d.equals("1 right") && currentLoc + 1 >= 0 && currentLoc + 1 <= 4) {
					
					tr.getTrainCar(currentLoc + 1).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc + 1);
					
				}
				else {
					
					tr.getTrainCar(currentLoc).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc);
					
				}
				
			}
			else {
				
				// ASK PLAYER FOR DIRECTION, PUT "1 LEFT" OR "2 LEFT" OR "1 RIGHT" OR "2 RIGHT" (FORWARD OR BACKWARD RESPECTIVELY) (LOWERCASE) IN STRING d
				
				if (d.equals ("1 left")  && currentLoc - 1 >= 0) {
					
					tr.getTrainCar(currentLoc - 1).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc - 1);
					
				}
				else if (d.equals ("2 left")  && currentLoc - 2 >= 0) {
					
					tr.getTrainCar(currentLoc - 2).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc - 2);
					
				}
				else if (d.equals("1 right") && currentLoc + 1 >= 0 && currentLoc + 1 <= 4) {
					
					tr.getTrainCar(currentLoc + 1).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc + 1);
					
				}
				else if (d.equals("2 right") && currentLoc + 2 >= 0 && currentLoc + 2 <= 4) {
					
					tr.getTrainCar(currentLoc + 2).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc + 2);
					
				}
				else {
					
					tr.getTrainCar(currentLoc).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc);
					
				}
				
			}
			
		} break;
		case "changeFloor": {
			
			int level = player.currentLevel;
			
			int changeTo = 0;
			
			if (level == 0) {
				
				changeTo = 1;
				
			}
			else {
				
				changeTo = 0;
				
			}
			
			tr.getTrainCar(player.getCurrentCar ()).getPlatform (level).removePlayer(player.getName());
			
			tr.getTrainCar(player.getCurrentCar ()).getPlatform (changeTo).addPlayer (player);
			
			player.updateLevel(changeTo);
			
		} break;
		case "loot":
		case "marshall": { // NEED TO FIX BY CHECKING IF OTHER CHARACTERS IN SAME ROOM AS MARSHALL AND MOVING THEM TO THE ROOF
			
			int currentLoc = 0;
			
			int level = 0;
			
			for (int i = 0; i < tr.getTrain().size(); i++) {
									
				ArrayList<Character> c0 = tr.getTrainCar(i).getPlatform(0).getCharacterList();
									
				ArrayList<Character> c1 = tr.getTrainCar(i).getPlatform(1).getCharacterList();
				
				for (int j = 0; j < c0.size (); j++) {
					
					if (c0.get (j).equals ("marshall")) {
						
						currentLoc = i;
						
						level = 0;
						
					}
					
				}
				
				for (int j = 0; j < c1.size (); j++) {
					
					if (c1.get (j).equals ("marshall")) {
						
						currentLoc = i;
						
						level = 1;
						
					}
					
				}
				
				
			}
			
			Character marshall = tr.getTrainCar (currentLoc).getPlatform (level).removePlayer ("marshall");
			
			// ASK PLAYER FOR DIRECTION TO MOVE MARSHALL, PUT "LEFT" OR "RIGHT" (FORWARD OR BACKWARD RESPECTIVELY) (LOWERCASE) IN STRING d
			
			String d = "";
			
			if (d.equals("left") && currentLoc - 1 >= 0) {
				
				tr.getTrainCar (currentLoc - 1).getPlatform(level).addPlayer(marshall);
				
				marshall.updateCurrentCar (currentLoc - 1);
				
			}
			else if (d.equals("right") && currentLoc + 1 >= 0 && currentLoc + 1 <= 4) {
				
				tr.getTrainCar (currentLoc + 1).getPlatform(level).addPlayer(marshall);
				
				marshall.updateCurrentCar (currentLoc + 1);
				
			}
			else {
				
				tr.getTrainCar (currentLoc).getPlatform (level).addPlayer(marshall);
				
				marshall.updateCurrentCar (currentLoc);
				
			}
			
		} break;
		
		
		}
	}
	
	
}
