import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Scanner;
import static java.lang.System.*;

public class Round {
	
	private Train tr;
	
	private Scanner sc;
	
	private ArrayList <Character> cList = new ArrayList <Character> ();
	
	private TreeMap <Character, ArrayList <ActionCard>> hands = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private TreeMap <Character, ArrayList <ActionCard>> bulletCards = new TreeMap <Character, ArrayList <ActionCard>> ();
		
	private TreeMap <Character, ArrayList <ActionCard>> discard = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private TreeMap <Character, ArrayList <ActionCard>> draw = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private RoundCard roundCard;
	
	private String [] roundCardWhatItDoes;
	
	public Round Train t, ArrayList <Character> c, TreeMap<Character, ArrayList<ActionCard>> h, TreeMap <Character, ArrayList <ActionCard>> bC, TreeMap <Character, ArrayList <ActionCard>> dis, TreeMap <Character, ArrayList <ActionCard>> dra) { // NEED TO PASS EVERYTHING IN HERE!!
		
		sc = new Scanner(in);
		tr = t;
		
		cList = c;
		
		hands = h;
		
		bulletCards = bC;
		
		discard = dis;
		
		draw = dra;
		
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
				
				temp.add (new ActionCard ("bullet", cList.get (i)));
				
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
		
		//1 is up, 0 is down, 2 reverse
		
		RoundCardSelector rs = new RoundCardSelector ();
		
		roundCard = rs.select ();
		
		int numTurns = roundCard.getNumTurns();
		
		ArrayList<String> read = setIndex(roundCard.getTurns());
		
		Queue<ActionCard> reel = new LinkedList <ActionCard>();
		
		String desc = roundCard.getWhatItDoes();
		
		for(int i = 0; i < numTurns; i++)
		{
			
			boolean up = true;
			
			if(read.get(i).equals("0"))
			{
				up = false;
			}
			
			
			if(read.get(i).equals("2"))
			{
				for(int j = cList.size(); j > 0; j--)
				{
					//prompt player (reverse) GIVE PLAYER OPTIONS FOR AVAILABLE CARDS IN HAND HERE
					
						ActionCard toPut = new ActionCard ("");
						
						for(int k = hands.get( cList.get(j) ).size(); k > 0; k--)
						{
							
							if(hands.get( cList.get(j) ).get(k).getWhatItDoes().equals("prompted response"))//FIX!!!
							{
								
								toPut = hands.get( cList.get(j) ).remove(k);
								
								break;
							}
							
						}
						
						reel.add(toPut);
						
					
				}
				
			}
			else
			{
				for(int j = 0; j < cList.size(); j++)
				{
					//prompt player GIVE PLAYER OPTIONS FOR AVAILABLE CARDS IN HAND HERE
					ActionCard toPut = new ActionCard ("");
					
					for(int k = 0; k < hands.get( cList.get(j) ).size(); k++)
					{
						
						if(hands.get( cList.get(j) ).get(k).getWhatItDoes().equals("prompted response"))//FIX!!!
						{
							
							toPut = hands.get( cList.get(j) ).remove(k);
							
							break;
						}
						
					}
					
					if(up)
					{
						toPut.setUp(true);
					}
					else
					{
						toPut.setUp(false);
					}
					
					toPut.setCharacter( cList.get(j) );
					
					reel.add(toPut);
				}
				
			}
			
		}
		
		
		
		for(int i = 0; i < reel.size(); i++)
		{
			ActionCard ac = reel.remove ();
			
			action (ac, ac.getCharacter());
		}
		
		endRoundRoundCardSwitchCases ();
				
	}
	
	public void endRoundRoundCardSwitchCases () {
		
		switch (roundCard.getWhatItDoes()) {
		
			case "aloneInCar:StealOneBag": {
				
				for (int i = 0; i <= 4; i++) {
					
					if (tr.getTrainCar(i).getPlatform(0).getCharacterList().size() == 1 && 
							tr.getTrainCar(i).getPlatform(0).getInventory().getBags().size() >= 1) {
						
						Character player = tr.getTrainCar(i).getPlatform(0).getCharacterList().get (0);
						
						tr.getTrainCar(i).getPlatform(player.getLevel()).getInventory().removeInventory(tr.getTrainCar(i).getPlatform(0).getInventory().getBags().get(0), player);
						
					}
					
				}
				
			} break;
			
			case "atopCarAndMarshall:DropBag": {
				
				for (int i = 0; i <= 4; i++) {
					
					boolean inThisCar = false;
					
					for (int j = 0; j < tr.getTrainCar(i).getPlatform(0).getCharacterList().size(); j++) {
						
						if (tr.getTrainCar(i).getPlatform(0).getCharacterList().get (i).getName().equals("marshall")) {
							
							inThisCar = true;
							
							break;
							
						}
						
					}
					
					if (inThisCar) {
						
						if (tr.getTrainCar(i).getPlatform(1).getCharacterList().size () >= 1) { // does it drop everyone atop's bags?
							
							for (int j = 0; j < tr.getTrainCar(i).getPlatform(1).getCharacterList().size (); j++) {
								
								Character player = tr.getTrainCar(i).getPlatform(1).getCharacterList().get (j);
								
								try {
								
									tr.getTrainCar(i).getPlatform(1).getInventory().addBags(player.removeBag());
									
								}
								catch (NullPointerException e) {
									
									;
									
								}
								
							}
							
						}
						
						break;
						
					}
					
				}
				
			} break;
			
			case "onOrInEngine:get250Cash": {
				
				if (tr.getTrainCar(0).getPlatform(0).getCharacterList().size () >= 1) {
					
					for (int i = 0; i < tr.getTrainCar(0).getPlatform(0).getCharacterList().size (); i++) {
						
						Character player = tr.getTrainCar(0).getPlatform(0).getCharacterList().get (i);
						
						player.addBags (new Bag (250));
						
					}
					
				}
				
				if (tr.getTrainCar(0).getPlatform(1).getCharacterList().size () >= 1) {
					
					for (int i = 0; i < tr.getTrainCar(0).getPlatform(1).getCharacterList().size (); i++) {
						
						Character player = tr.getTrainCar(0).getPlatform(1).getCharacterList().get (i);
						
						player.addBags (new Bag (250));
						
					}
					
				}
				
			} break;
			
			case "sheriffDropsNewLockBox": {
				
				for (int i = 0; i <= 4; i++) {
					
					boolean inThisCar = false;
					
					Character marshall = new Character ("", 0, 0, 0, 0);
					
					for (int j = 0; j < tr.getTrainCar(i).getPlatform(0).getCharacterList().size(); j++) {
						
						if (tr.getTrainCar(i).getPlatform(0).getCharacterList().get (i).getName().equals("marshall")) {
							
							inThisCar = true;
							
							marshall = tr.getTrainCar(i).getPlatform(0).getCharacterList().get (i);
							
							break;
							
						}
						
					}
					
					if (inThisCar) {
						
						if (marshall.getLockboxes ().size () > 0) {
							
							// *NEED TO START GAME WITH 3 LOCKBOXES IN MARSHALL!!*
							
							tr.getTrainCar (i).getPlatform(0).getInventory().addLockBox(marshall.removeLockBox());
							
						}
						
						break;
						
					}
					
				}
				
			} break;
			
			case "allPeopleAtopTrainMoveToCaboose": {
				
				for (int i = 0; i <= 3; i++) {
					
					if (tr.getTrainCar (i).getPlatform(1).getCharacterList().size() > 1) {
						
						for (int j = 0; j < tr.getTrainCar (i).getPlatform(1).getCharacterList().size (); j++) {
							
							Character player = tr.getTrainCar(i).getPlatform(1).getCharacterList().remove(j);
							
							tr.getTrainCar(4).getPlatform(1).addPlayer (player);
							
							player.updateCurrentCar(4);
							
						}
						
					}
					
				}
				
			} break;
			
			case "everyoneAtopMovesTowardsEngine1Space": {
				
				for (int i = 1; i <= 4; i++) {
					
					if (tr.getTrainCar (i).getPlatform (1).getCharacterList().size () > 1) {
						
						for (int j = 0; j < tr.getTrainCar (i).getPlatform(1).getCharacterList().size (); j++) {
							
							Character player = tr.getTrainCar(i).getPlatform(1).getCharacterList().remove(j);
							
							tr.getTrainCar(player.currentCar - 1).getPlatform (1).addPlayer (player);
							
							player.updateCurrentCar(player.currentCar - 1);
							
						}
						
					}
					
				}
				
			} break;
			
			case "everyoneTakes1Damage": {
				
				for (int i = 0; i <= 4; i++) {
					
					for (int j = 0; j < tr.getTrainCar(i).getPlatform(0).getCharacterList().size (); j++) {
						
						hands.get (tr.getTrainCar(i).getPlatform(0).getCharacterList().get (j)).add(new ActionCard ("bullet", null)); // *ACTIONCARD (BULLET CARD) WITH NULL AS CHARACTER IS NEUTRAL CARD!!*
						
					}
					
					for (int j = 0; j < tr.getTrainCar(i).getPlatform(1).getCharacterList().size (); j++) {
						
						hands.get (tr.getTrainCar(i).getPlatform(1).getCharacterList().get (j)).add(new ActionCard ("bullet", null)); // *ACTIONCARD (BULLET CARD) WITH NULL AS CHARACTER IS NEUTRAL CARD!!*
						
					}
					
				}
				
			} break;
			
			case "sheriffShootsAnyoneAboveHisCar": {
				
				for (int i = 0; i <= 4; i++) {
					
					boolean inThisCar = false;
					
					Character marshall = new Character ("", 0, 0, 0, 0);
					
					for (int j = 0; j < tr.getTrainCar(i).getPlatform(0).getCharacterList().size(); j++) {
						
						if (tr.getTrainCar(i).getPlatform(0).getCharacterList().get (i).getName().equals("marshall")) {
							
							inThisCar = true;
							
							marshall = tr.getTrainCar(i).getPlatform(0).getCharacterList().get (i);
							
							break;
							
						}
						
					}
					
					if (inThisCar) {
						
						if (tr.getTrainCar (i).getPlatform(1).getCharacterList().size () > 1) {
							
							for (int j = 0; j < tr.getTrainCar (i).getPlatform(1).getCharacterList().size (); j++) {
								
								Character player = tr.getTrainCar (i).getPlatform(1).getCharacterList().get (j);
								
								hands.get(player).add(new ActionCard ("bullet", null)); // *ACTIONCARD (BULLET CARD) WITH NULL AS CHARACTER IS NEUTRAL CARD!!*
								
							}
							
						}
						
						break;
						
					}
				
				}
					
			} break;
		
		}
		
	}
	
	public ArrayList<String> setIndex(String key)
	{
		String[] one = key.split(" ");
		
		ArrayList<String> ret = new ArrayList<String> ();
		
		for(int i = 0; i < one.length; i++)
		{
			ret.add(one[i]);
		}
		
		return ret;
		
	}
	
	public void startOfRound () {
		
		
		
	}
	public String printPunchOptions(Character player)
	{
		String str = "Who would you like to hit? You cannot hit yourself.\n";
		int car = player.getCurrentCar();
		ArrayList<Character> list = tr.getTrainCar(car).getPlatform(player.getLevel()).getCharacterList();
		if (list.size()>0)
		{
			for (int x = 0; x<list.size(); x++)
			{
				str = str+(x+1)+".\t"+list.get(x).getName()+"\n";
			}
		}
		else
		{
			str = "Sorry, you can't punch here.";
		}
		return str;
	}
	
	
	public String printLootOptions(ArrayList<InventoryTwo> list)
	{
		String str = "What would you like to loot?\n";
		if (list.size()>0)
		{
			for (int x = 0; x<list.size(); x++)
			{
				str = str +(x+1)+".\t"+list.get(x).getType()+"\n";
			}
		}
		else
			str = "Sorry, you can't loot here.";
		return str;
	}
	
	
	public String printShootOptions(TreeMap<Integer, ArrayList<Character>> map)
	{
		String str = "Who would you like to punch?\n";
		int count = 1;
		for (int x : map.keySet())
		{
			ArrayList<Character> list = map.get(x);
			for (int i = 0; i<list.size();i++)
			{
				str = str+count+".\t"+list.get(i).getName()+"\n";
				count++;
			}
		}
		return str;
	}
	
	
	public String printMoveOptions(Character player)
	{
		String str = "Which way would you like to move? (type in exactly as printed)\n";
		int level = player.currentLevel;
		int currentLoc = player.currentCar;
		if (level == 0) {
			
			if (currentLoc - 1 >= 0) 
			{
				str = str+"1 left\n";
			}
			else if (currentLoc + 1 >= 0 && currentLoc + 1 <= 4) 
			{
				str = str+"1 right\n";
			}
			else {
				
				tr.getTrainCar(currentLoc).getPlatform(level).addPlayer (player);
				
				player.updateCurrentCar(currentLoc);
				
			}
			
		}
		else {
			
			if ( currentLoc - 1 >= 0) {
				str = str+"1 left\n";
			}
			if ( currentLoc - 2 >= 0) {
				
				str = str+"2 left\n";
			}
			if ( currentLoc - 3 >= 0) {
				str = str+"3 left\n";
			}
			if (currentLoc + 1 >= 0 && currentLoc + 1 <= 4) {
				
				str = str+"1 right \n";
			}
			if (currentLoc + 2 >= 0 && currentLoc + 2 <= 4) {
				
				str = str+"3 right \n";
				
			}
			if (currentLoc + 3 >= 0 && currentLoc + 3 <= 4) {
				
				str = str+"3 right \n";
			}
		}
			return str;
	}
	
	public void action(ActionCard card, Character player){ // LEFT IS FORWARD, RIGHT IS BACKWARD
		int car = player.getCurrentCar();
		switch(card.getWhatItDoes()){
		case "punch": {
			
			ArrayList<Character> list = tr.getTrainCar(car).getPlatform(player.getLevel()).getCharacterList();
			//ASK PLAYER FOR PLAYER TO PUNCH, PUT IN STRING 'VICTIM', ask player for direction to punch, put in string 'direction'
			out.println(printPunchOptions(player));
			String victim = sc.nextLine();
			out.println("Which direction would you like to punch the victim?");
			String direction = sc.nextLine();
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
			
			if (bulletCards.get (player).size () > 0) {
				
				TreeMap <Integer, ArrayList <Character>> possibleShoots = new TreeMap <Integer, ArrayList <Character>> ();
				
				if (player.getName ().equals ("tuco")) {
					
					int shootLevel = 0;
					
					if (player.currentLevel == 0) {
						
						shootLevel = 1;
						
					}
					
					possibleShoots.put (player.currentCar, tr.getTrainCar(player.currentCar).getPlatform(shootLevel).getCharacterList());
					
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
					
					for (int i = player.currentCar + 1; i <= 4; i++) {
						
						if (tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
							
							possibleShoots.put (i, tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList());
							
							break;
						}
						
					}
					
					for (int i = player.currentCar - 1; i >= 0; i--) {
						
						if (tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
							
							possibleShoots.put (i, tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList());
							
							break;
							
						}
						
					}
					
				}
				out.println(printShootOptions(possibleShoots));//NEED HELP HERE
				Character victim = new Character ("", car, car, car, car); // GIVE OPTIONS ON WHOM TO SHOOT BASED ON MAP POSSIBLE SHOOTS, SELECT CHARACTER TO SHOOT AND POINT SELECTED AT IT (INITIALIZED ONLY TO PREVENT ERROR FOR NOW, DOESN'T ACTUALLY WORK)
				
				hands.get (victim).add (bulletCards.get (player).remove (0));
				
				if (player.getName ().equals ("django")) {
					
					String d = "";
					
					if (victim.currentCar < player.currentCar) {
						
						d = "1 left";
						
					}
					else {
						
						d = "1 right";
						
					}
					
					switch (d) {
					
						case "1 left": {
							
							if (victim.currentCar - 1 >= 0) {
								
								tr.getTrainCar(victim.currentCar).getPlatform(victim.currentLevel).removePlayer(victim.getName ());
								
								tr.getTrainCar(victim.currentCar - 1).getPlatform (victim.currentLevel).addPlayer(victim);
								
								victim.updateCurrentCar (victim.currentCar - 1);
								
							}
							
							
						} break;
						
						case "1 right": {
							
							if (victim.currentCar + 1 <= 4) {
								
								tr.getTrainCar(victim.currentCar).getPlatform(victim.currentLevel).removePlayer(victim.getName ());
								
								tr.getTrainCar(victim.currentCar + 1).getPlatform (victim.currentLevel).addPlayer(victim);
								
								victim.updateCurrentCar (victim.currentCar + 1);
								
							}
							
						}
					
					}
					
				}
				
			}
				
		} break;
		case "move":{ // NEED TO FIX BY GIVING OPTION FOR 3 MOVE
			String d = "";
			
			int level = player.currentLevel;
			
			int currentLoc = player.currentCar;
			
			tr.getTrainCar (currentLoc).getPlatform(level).removePlayer(player.getName());
			out.println(printMoveOptions(player));
			d = sc.nextLine();
			
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
		{
			ArrayList <InventoryTwo> list = new ArrayList <InventoryTwo> ();
			for (int x = 0; x<tr.getTrainCar(car).getPlatform(player.getLevel()).getInventory().getBags().size(); x++)
			{
				list.add (tr.getTrainCar(car).getPlatform(player.getLevel()).getInventory().getBags().get(x));
			}
			for (int x = 0; x<tr.getTrainCar(car).getPlatform(player.getLevel()).getInventory().getRubies().size(); x++)
			{
				list.add(tr.getTrainCar(car).getPlatform(player.getLevel()).getInventory().getRubies().get(x));
			}
			for (int x = 0; x<tr.getTrainCar(car).getPlatform(player.getLevel()).getInventory().getLockBoxes().size(); x++)
			{
				list.add(tr.getTrainCar(car).getPlatform(player.getLevel()).getInventory().getLockBoxes().get(x));
			}
			//ADDS ALL INVENTORY TO ARRAYLIST OF INVENTORY
			out.println(printLootOptions(list));
			int sel = sc.nextInt();
			sel--;
			//SHOW OPTIONS
			InventoryTwo selected = new InventoryTwo(list.get(sel).getType());
			tr.getTrainCar(car).getPlatform(player.getLevel()).getInventory().removeInventory(selected, player);
			
		}break; 
		case "marshall": { // NEED TO FIX BY CHECKING IF OTHER CHARACTERS IN SAME ROOM AS MARSHALL AND MOVING THEM TO THE ROOF
			
			int currentLoc = 0;
			
			int level = 0;
			
			for (int i = 0; i < tr.getTrain().size(); i++) {
									
				ArrayList<Character> c0 = tr.getTrainCar(i).getPlatform(0).getCharacterList();
									
				for (int j = 0; j < c0.size (); j++) {
					
					if (c0.get (j).getName().equals ("marshall")) {
						
						currentLoc = i;
	
						level = 0;
						
						break;
					}
					
				}
				
			
					
				
				
			}
			
			Character marshall = tr.getTrainCar (currentLoc).getPlatform (level).removePlayer ("marshall");
			
			// ASK PLAYER FOR DIRECTION TO MOVE MARSHALL, PUT "LEFT" OR "RIGHT" (FORWARD OR BACKWARD RESPECTIVELY) (LOWERCASE) IN STRING d
			
			out.println("Which way would you like to move the Marshall? (left or right)");
			String d = sc.nextLine();
			
			if (d.equals("left") && currentLoc - 1 >= 0) {
				
				tr.getTrainCar (currentLoc - 1).getPlatform(level).addPlayer(marshall);
				
				marshall.updateCurrentCar (currentLoc - 1);
				currentLoc= currentLoc-1;
				
			}
			else if (d.equals("right") && currentLoc + 1 >= 0 && currentLoc + 1 <= 4) {
				
				tr.getTrainCar (currentLoc + 1).getPlatform(level).addPlayer(marshall);
				
				marshall.updateCurrentCar (currentLoc + 1);
				currentLoc= currentLoc+1;
				
			}
			if(tr.getTrainCar(currentLoc).getPlatform(level).getCharacterList().size()>1)
			{
				for (int x = 0; x<tr.getTrainCar(currentLoc).getPlatform(level).getCharacterList().size(); x++)
				{
					if (!tr.getTrainCar(currentLoc).getPlatform(level).getCharacterList().get(x).getName().equals("marshall"))
					{
						tr.getTrainCar(currentLoc).getPlatform(level).getCharacterList().get(x).updateLevel(level+1);
						hands.get(tr.getTrainCar(currentLoc).getPlatform(level).getCharacterList().get(x)).add(new ActionCard("bullet",null)); // *ACTIONCARD (BULLET CARD) WITH NULL AS CHARACTER IS NEUTRAL CARD!!*
					}
				}
			}
			
		} break;
		
		
		}
	}
	
	
}
