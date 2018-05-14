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
	
	private RoundCard roundCard = new RoundCard ("aloneInCar:StealOneBag", "1 1 0 1", 4, true);
	
	private TreeMap<Integer, String> shootSelections = new TreeMap<Integer, String>();
	
	private TreeMap<Integer, String> moveSelections = new TreeMap<Integer, String>();
	
	private TreeMap<Integer, String> punchSelections = new TreeMap<Integer, String>();
	
	Character marshall = new Character ("marshall", 0, 0, 0, 0);
	
	
	private String [] roundCardWhatItDoes;
	
	public void printTrain () {
		
		System.out.println ("\n");
		
		System.out.println ("ENGINE:\t\t\tCAR 1:\t\t\tCAR 2:\t\t\tCAR 3:\t\t\tCABOOSE:");
				
		int max1 = 0;
		
		int max0 = 0;
		
		for (int i = 0; i < tr.getTrain().size (); i++) {
			
			if (tr.getTrainCar(i).getPlatform(1).getCharacterList ().size () > max1) {
				
				max1 = tr.getTrainCar(i).getPlatform(1).getCharacterList ().size ();
				
			}
			
		}
		
		for (int i = 0; i < tr.getTrain().size (); i++) {
			
			if (tr.getTrainCar(i).getPlatform(0).getCharacterList ().size () > max0) {
				
				max0 = tr.getTrainCar(i).getPlatform(0).getCharacterList ().size ();
				
			}
			
		}
		
		for (int i = 0; i < max1; i++) {
			
			for (int j = 0; j < tr.getTrain ().size (); j++) {
				
				try {
					
					System.out.print (tr.getTrainCar(j).getPlatform(1).getCharacterList().get(i).getName () + "\t\t");
					
					if (tr.getTrainCar(j).getPlatform(1).getCharacterList().get(i).getName ().equals("doc") || tr.getTrainCar(j).getPlatform(1).getCharacterList().get(i).getName ().equals("django") || tr.getTrainCar(j).getPlatform(1).getCharacterList().get(i).getName ().equals("ghost")) {
						
						System.out.print ("\t");
						
					}
					
				}
				catch (NullPointerException | IndexOutOfBoundsException e) {
					
					System.out.print ("\t\t\t");
					
				}
				
			}
			
			System.out.println ();
			
		}
		
		for (int i = 0; i < tr.getTrain ().size (); i++) {
			
			System.out.print ("-----\t\t\t");
			
		}
		
		System.out.println ();
		
		for (int i = 0; i < max0; i++) {
			
			for (int j = 0; j < tr.getTrain ().size (); j++) {
				
				try {
					
					System.out.print (tr.getTrainCar(j).getPlatform(0).getCharacterList().get(i).getName () + "\t\t");
					
					if (tr.getTrainCar(j).getPlatform(0).getCharacterList().get(i).getName ().equals("doc") || tr.getTrainCar(j).getPlatform(0).getCharacterList().get(i).getName ().equals("django") || tr.getTrainCar(j).getPlatform(0).getCharacterList().get(i).getName ().equals("ghost")) {
						
						System.out.print ("\t");
						
					}
					
				}
				catch (NullPointerException | IndexOutOfBoundsException e) {
					
					System.out.print ("\t\t\t");
					
				}
				
			}
			
			System.out.println ();
			
		}
		
		System.out.println ("\n");
		
	}
	
	public Round (Train t, ArrayList <Character> c, TreeMap<Character, ArrayList<ActionCard>> h, TreeMap <Character, ArrayList <ActionCard>> bC, TreeMap <Character, ArrayList <ActionCard>> dis, TreeMap <Character, ArrayList <ActionCard>> dra, RoundCard rc) { // NEED TO PASS EVERYTHING IN HERE!!
		
		marshall.currentCar = 0;
		
		marshall.currentLevel = 0;
		
		tr = t;
		
		cList = c;
		
		hands = h;
		
		bulletCards = bC;
		
		discard = dis;
		
		draw = dra;
		
		sc = new Scanner(in);
		
		roundCard = rc;
		
	}
	
	public RoundCard getRoundCard()
	{
		return roundCard;
	}
	
	public void First () { // PUTS 6 CARDS IN HAND AND 4 IN DRAW PILE, PUT 6 BULLET CARDS FOR EACH CHARACTER (INCLUDING MARSHALL, WHO IS A CHARACTER BUT NOT PLAYABLE)
		
		tr.getTrainCar(0).getPlatform(0).addPlayer(marshall);
		
		System.out.println(roundCard.getTurns ());
		
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
		for (Character c : hands.keySet())
		{
			if (c.getName().equals("doc"))
			{
				hands.get(c).add(draw.get(c).remove(0));
			}
		}
		for (Character c : hands.keySet())
		{

			while (hands.get(c).size()<6 && !c.getName().equals("doc"))
			{
				if (draw.get(c).size()==0)
				{
					for (int x = discard.get(c).size()-1; x>=0; x--)
					{
						draw.get(c).add(discard.get(c).remove(x));
					}
					Collections.shuffle(draw.get(c));
				}
				hands.get(c).add(draw.get(c).remove(0));
			}
			while (hands.get(c).size()<7 && c.getName().equals("doc"))
			{
				if (draw.get(c).size()==0)
				{
					for (int x = discard.get(c).size()-1; x>=0; x--)
					{
						draw.get(c).add(discard.get(c).remove(x));
					}
					Collections.shuffle(draw.get(c));
				}
				hands.get(c).add(draw.get(c).remove(0));
			}
			
		}
	}
	
	private ArrayList <ActionCard> getTopSixFirst (ArrayList <ActionCard> c) {
		
		ArrayList <ActionCard> toReturn = new ArrayList <ActionCard> ();
		
		for (int i = 0; i < 6; i++) {
			
			toReturn.add (c.remove (0));
			
		}
		
		return toReturn;
		
	}
	
	public void playRound () {
		
		//1 is up, 0 is down, 2 reverse
		
	//	int numTurns = roundCard.getNumTurns();
		
		ArrayList<String> read = setIndex(roundCard.getTurns());
		
		Queue<ActionCard> reel = new LinkedList <ActionCard>();
		
		String desc = roundCard.getWhatItDoes();
		
		for(int i = 0; i < roundCard.getNumTurns (); i++)
		{
			
			boolean up = true;
			
			if(read.get(i).equals("0"))
			{
				up = false;
			}
			
			
			if(read.get(i).equals("2"))
			{
				for(int j = cList.size()-1; j >= 0; j--)
				{
					
					if (!cList.get(j).getName ().equals("marshall")) {
					//prompt player (reverse) GIVE PLAYER OPTIONS FOR AVAILABLE CARDS IN HAND HERE
					
						ActionCard toPut = chooseToPlayCard (cList.get (j));
						
						for(int k = hands.get( cList.get(j) ).size()-1; k > 0; k--)
						{
							
							if(hands.get( cList.get(j) ).get(k).getWhatItDoes().equals(toPut.getWhatItDoes()))//FIX!!!
							{
								
								toPut = hands.get( cList.get(j) ).remove(k);
								
								break;
							}
							
						}
						
						reel.add(toPut);
					}	
					
				}
				
			}
			else
			{
				for(int j = 0; j < cList.size(); j++)
				{
					
					if (!cList.get(j).getName ().equals("marshall")) {
					//prompt player GIVE PLAYER OPTIONS FOR AVAILABLE CARDS IN HAND HERE
					ActionCard toPut = chooseToPlayCard (cList.get (j));
					
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
			
		}
		
		Stack temp = new Stack ();
		
		while (!reel.isEmpty())
		{
			ActionCard ac = reel.remove ();
			
			if (discard.containsKey (ac.getCharacter())) {
				
				discard.get (ac.getCharacter()).add (ac);
				
			}
			else {
				
				ArrayList <ActionCard> list = new ArrayList <ActionCard> ();
				
				list.add (ac);
				
				discard.put (ac.getCharacter(), list);
				
			}
			
			printTrain ();
			
			action (ac, ac.getCharacter());
		}
		
		endRoundRoundCardSwitchCases ();
				
	}
	
	public ActionCard chooseToPlayCard (Character player) {
		
		ArrayList <ActionCard> options = new ArrayList <ActionCard> ();
		
		for (int i = 0; i < hands.get (player).size (); i++) {
			
			options.add (hands.get (player).get (i));
			
		}
		System.out.println(player.getName()+"'s turn:");
		
		for (int i = 0; i < options.size (); i++) {
			
			System.out.println ((i + 1) + ". " + options.get (i).getWhatItDoes ());
			
		}
		
		Scanner input = new Scanner (System.in);
		
		int index = input.nextInt() - 1;
		input.nextLine();
		for (int i = 0; i < hands.get(player).size (); i++) {
			
			if (i == index) {
				
				return hands.get(player).remove (i);
				
			}
			
		}
		
		return null;
		
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
			
			case "stopCarAndMarshall:DropBag": {
				
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
						
						if (tr.getTrainCar(i).getPlatform(0).getCharacterList().get (j).getName().equals("marshall")) {
							
							inThisCar = true;
							
							marshall = tr.getTrainCar(i).getPlatform(0).getCharacterList().get (j);
							
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
	
	public void startOfRound () 
	{
		
		out.println(roundCard.getTurns());
	//hands, discard, draw
		for (Character c: hands.keySet())
		{
			
			for (int x = hands.get(c).size()-1; x>=0 ; x--)
			{
				if (hands.get(c).get(x).getWhatItDoes().equals("bullet"))
				{
					
					if (discard.containsKey(c) && discard.get(c).size()!=0)
						discard.get(c).add(hands.get(c).remove(x));
					else
					{
						ArrayList<ActionCard> list = new ArrayList<>();
						list.add(hands.get(c).remove(x));
						discard.put(c, list);
					}
				}
			}
		}
		
		ArrayList <Character> keySet = new ArrayList <Character> (hands.keySet ());
		
		for (int i = 0; i < keySet.size (); i++)
		{
			
			Character c = keySet.get(i);
			while (hands.get(c).size()>0&&hands.get(c).size()<5)
			{
				
				System.out.println ("CHOOSE");
				if (!c.getName().equals("marshall"))
				{
					String str = "Which would "+c.getName()+" like to discard?\n";
					for (int x = 0; x<hands.get(c).size(); x++)
					{
						str = str +(x+1)+".\t"+hands.get(c).get(x).getWhatItDoes()+"\n";
					}
					str = str+"0. Discard none";
					out.println(str);
					int choice = sc.nextInt();
					sc.nextLine();
					if (choice == 0)
					{
						break;
					}
					discard.get(c).add(hands.get(c).remove(choice-1));	
				}
			}
		
			while (hands.get(c).size()<6 && !c.getName().equals("doc"))
			{
				if (draw.get(c).size()==0)
				{
					for (int x = discard.get(c).size()-1; x>=0; x--)
					{
						draw.get(c).add(discard.get(c).remove(x));
					}
					Collections.shuffle(draw.get(c));
				}
				hands.get(c).add(draw.get(c).remove(0));
			}
			while (hands.get(c).size()<7 && c.getName().equals("doc"))
			{
				if (draw.get(c).size()==0)
				{
					for (int x = discard.get(c).size()-1; x>=0; x--)
					{
						draw.get(c).add(discard.get(c).remove(x));
					}
					Collections.shuffle(draw.get(c));
				}
				hands.get(c).add(draw.get(c).remove(0));
			}
		}
		
	}
	public String printPunchOptions(Character player)
	{
		punchSelections = new TreeMap<Integer, String>();
		String str = "Who would "+player.getName()+" like to punch?\n";
		int car = player.getCurrentCar();
		ArrayList<Character> list = (ArrayList<Character>) tr.getTrainCar(car).getPlatform(player.getLevel()).getCharacterList().clone ();
		for (int x = 0; x <list.size(); x++)
		{ 
			if (list.get(x).getName().equals(player.getName()))
			{
				list.remove(x);
				break;
			}
		}
		if (list.size()>0)
		{
			for (int x = 0; x<list.size(); x++)
			{
				str = str+(x+1)+".\t"+list.get(x).getName()+"\n";
				punchSelections.put(x+1, list.get(x).getName());
			}
		}
		else
		{
			str = "Sorry, "+player.getName()+" can't punch here.";
		}
		return str;
	}
	
	
	public String printLootOptions(ArrayList<InventoryTwo> list, Character player)
	{
		String str = "What would "+player.getName()+" like to loot?\n";
		if (list.size()>0)
		{
			for (int x = 0; x<list.size(); x++)
			{
				str = str +(x+1)+".\t"+list.get(x).getType()+"\n";
			}
		}
		else
			str = "Sorry, "+player.getName()+" can't loot here.";
		return str;
	}
	
	
	public String printShootOptions(TreeMap<Integer, ArrayList<Character>> map, Character player)
	{
		shootSelections = new TreeMap<>();
		String str = "Who would "+player.getName()+" like to shoot?\n";
		int count = 1;
		for (int x : map.keySet())
		{
			ArrayList<Character> list = map.get(x);
			for (int i = 0; i<list.size();i++)
			{
				str = str+count+".\t"+list.get(i).getName()+"\n";
				shootSelections.put(count, list.get(i).getName());
				count++;
			}
		}
		if (shootSelections.size ()>0)
			return str;
		else 
			return "Sorry, "+player.getName()+" can't shoot";
	}
	
	
	public String printMoveOptions(Character player)
	{
		moveSelections = new TreeMap<>();
		String str = "Which way would "+player.getName()+" like to move?\n";
		int level = player.currentLevel;
		int currentLoc = player.currentCar;
		int count = 1; 
		
		if (level == 0) {
			
			if (currentLoc - 1 >= 0) 
			{
				str = str+count +".\t1 left\n";
				moveSelections.put(count, "1 left");
				count++;
			}
			if (currentLoc + 1 >= 0 && currentLoc + 1 <= 4) 
			{
				str = str+count+".\t1 right\n";
				moveSelections.put(count, "1 right");
				count++;
			}
			else {
				
				//tr.getTrainCar(currentLoc).getPlatform(level).addPlayer (player);
				
				//player.updateCurrentCar(currentLoc);
				
			}
			
		}
		else {

			for (int x = 1; x<4; x++)
			{
				if (currentLoc -x >=0)
				{
					str = str+count+".\t "+x+" left\n";
					moveSelections.put(count, x+" left");
					count++;
					
				}
				if (currentLoc+x>=0 && currentLoc +x<=4)
				{
					str = str+count+".\t "+x+" right\n";
					moveSelections.put(count, x+" right");
					count++;
				}
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
			String s = printPunchOptions(player);
			out.println(s);
			if (!s.equals("Sorry, "+player.getName()+" can't punch here."))
			{
			int a = sc.nextInt();
			sc.nextLine();
			String victim = punchSelections.get(a);
			for(int x = 0; x < list.size(); x++){
				if(list.get(x).getName().equals(victim)){
					Character vic = tr.getTrainCar(player.getCurrentCar()).getPlatform(player.currentLevel).removePlayer(victim);
					
					if(player.getName().equals("cheyenne")){
						player.addBags(vic.removeBag());
					}
					String d = "";
					out.println("Which direction would "+player.getName()+" like to punch the victim?");
					if (vic.getCurrentCar () >= 1 && vic.getCurrentCar () <= 3) {
						
						out.println ("1. 1 left");
						out.println ("2. 1 right");
						String str1 = sc.nextLine ();
						
						if (str1.equals ("1")) {
							
							d = "left";
							
						}
						else {
							
							d = "right";
							
						}
						
					}
					else if (vic.getCurrentCar () <= 3) {
						
						out.println ("1. 1 right");
						String str1 = sc.nextLine ();
						
						if (str1.equals ("1")) {
							
							d = "right";
							
						}
						else {
							
							d = "right";
							
						}
						
					}
					else if (vic.getCurrentCar () >= 1) {
						
						out.println ("1. 1 left");
						String str1 = sc.nextLine ();
						
						if (str1.equals ("1")) {
							
							d = "left";
							
						}
						else {
							
							d = "left";
							
						}
						
					}
					
					
					String direction = d;
					if(direction.equals("right")){
						tr.getTrainCar(player.getCurrentCar()+1).getPlatform(player.currentLevel).addPlayer(vic);
						
						vic.updateCurrentCar(vic.getCurrentCar()+1);
						
					}
					else if(direction.equals("left")){
						tr.getTrainCar(player.getCurrentCar()-1).getPlatform(player.currentLevel).addPlayer(vic);
						vic.updateCurrentCar(vic.getCurrentCar()-1);
						
					}
					
					if (marshall.currentCar == vic.currentCar && vic.currentLevel == 0) {
						
						vic = tr.getTrainCar(vic.currentCar).getPlatform(vic.currentLevel).removePlayer (vic.getName());
						
						tr.getTrainCar (vic.currentCar).getPlatform(1).addPlayer(vic);
						
						vic.updateLevel(1);
						
					}
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
					
					// CLONE DON'T POINT
					
					possibleShoots.put (player.currentCar, (ArrayList<Character>) tr.getTrainCar(player.currentCar).getPlatform(shootLevel).getCharacterList().clone ());
					
				}
				
				if (player.currentLevel == 0) {
					
					if (player.currentCar - 1 >= 0 && tr.getTrainCar(player.currentCar - 1).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
						
						possibleShoots.put (player.currentCar - 1, (ArrayList<Character>) tr.getTrainCar(player.currentCar - 1).getPlatform(player.currentLevel).getCharacterList().clone ());
					
					}
					
					if (player.currentCar + 1 >= 0 && player.currentCar + 1 <= 4 && tr.getTrainCar(player.currentCar + 1).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
						
						possibleShoots.put (player.currentCar + 1, (ArrayList<Character>) tr.getTrainCar(player.currentCar + 1).getPlatform(player.currentLevel).getCharacterList().clone ());
					
					}
					
				}
				
				if (player.currentLevel == 1) {
					
					for (int i = player.currentCar + 1; i <= 4; i++) {
						
						if (tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
							
							possibleShoots.put (i, (ArrayList<Character>) tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList().clone());
							
							break;
						}
						
					}
					
					for (int i = player.currentCar - 1; i >= 0; i--) {
						
						if (tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList().size () > 0) {
							
							possibleShoots.put (i, (ArrayList<Character>) tr.getTrainCar(i).getPlatform(player.currentLevel).getCharacterList().clone ());
							
							break;
							
						}
						
					}
					
				}
				Character selected = new Character ("", car, car, car, car);//FIX THIS
				for (int i : possibleShoots.keySet())
				{
					for (int x = 0; x<possibleShoots.get(i).size(); x++)
					{
						if (possibleShoots.get(i).get(x).getName().equals("marshall"))
						{
							possibleShoots.get(i).remove(x);
							break;
						}
					}
				}
				String s = printShootOptions(possibleShoots, player);
				out.println(s);
				if (!s.equals("Sorry, "+player.getName()+" can't shoot"))
				{
					int sel = sc.nextInt();
					sc.nextLine();
					String str = shootSelections.get(sel);
					for (int x : possibleShoots.keySet())
					{
						for (int i =0; i<possibleShoots.get(x).size(); i++)
						{
							if(possibleShoots.get(x).get(i).getName().equals(str))
							{
								selected = possibleShoots.get(x).get(i);
								break;
							}
						}
					}
				
			 // GIVE OPTIONS ON WHOM TO SHOOT BASED ON MAP POSSIBLE SHOOTS, SELECT CHARACTER TO SHOOT AND POINT SELECTED AT IT (INITIALIZED ONLY TO PREVENT ERROR FOR NOW, DOESN'T ACTUALLY WORK)
			
				hands.get (selected).add(bulletCards.get(player).remove(0));
				Character victim = selected;
			//	Character victim = new Character ("", car, car, car, car); // GIVE OPTIONS ON WHOM TO SHOOT BASED ON MAP POSSIBLE SHOOTS, SELECT CHARACTER TO SHOOT AND POINT SELECTED AT IT (INITIALIZED ONLY TO PREVENT ERROR FOR NOW, DOESN'T ACTUALLY WORK)
				
			//	hands.get (sel).add (bulletCards.get (player).remove (0));
				
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
					
					if (marshall.currentCar == victim.currentCar && victim.currentLevel == 0) {
						
						victim = tr.getTrainCar(victim.currentCar).getPlatform(victim.currentLevel).removePlayer (victim.getName());
						
						tr.getTrainCar (victim.currentCar).getPlatform(1).addPlayer(victim);
						
						victim.updateLevel(1);
						
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
			int j = sc.nextInt();
			sc.nextLine();
			d = moveSelections.get(j);
			
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
				
				if (marshall.currentCar == player.currentCar && player.currentLevel == 0) {
					
					tr.getTrainCar(player.currentCar).getPlatform(player.currentLevel).removePlayer (player.getName());
					
					tr.getTrainCar (player.currentCar).getPlatform(1).addPlayer(player);
					
					player.updateLevel(1);
					
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
				else if (d.equals ("3 left")  && currentLoc - 3 >= 0) {
					
					tr.getTrainCar(currentLoc - 3).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc - 3);
					
				}
				else if (d.equals("3 right") && currentLoc + 3 >= 0 && currentLoc + 3 <= 4) {
					
					tr.getTrainCar(currentLoc + 3).getPlatform(level).addPlayer (player);
					
					player.updateCurrentCar(currentLoc + 3);
					
				}
				
				
			}
			
			if (tr.getTrainCar(player.currentCar).getPlatform(0).getCharacterList().contains(marshall) && player.currentLevel == 0) {
				
				for (int i = 0; i < tr.getTrainCar(marshall.currentCar).getPlatform(0).getCharacterList().size(); i++) {
					
					if (!tr.getTrainCar(marshall.currentCar).getPlatform(0).getCharacterList().get (i).equals (marshall)) {
						
						Character tempC = tr.getTrainCar(marshall.currentCar).getPlatform(0).getCharacterList().remove (i);
						
						tr.getTrainCar(marshall.currentCar).getPlatform(1).addPlayer (tempC);
						
					}
					
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
			
			if (changeTo == 1 || (changeTo == 0 && marshall.currentCar != player.currentCar)) {
				
				tr.getTrainCar(player.getCurrentCar ()).getPlatform (level).removePlayer(player.getName());
				
				//	System.out.println(player.getName());
					
				tr.getTrainCar(player.getCurrentCar ()).getPlatform (changeTo).addPlayer (player);
				
				player.updateLevel(changeTo);
				
			}
			
			
			/* if (marshall.currentCar == player.currentCar && player.currentLevel == 0) {
				
				player = tr.getTrainCar(player.currentCar).getPlatform(player.currentLevel).removePlayer (player.getName());
				
				tr.getTrainCar (player.currentCar).getPlatform(1).addPlayer(player);
				
				player.updateLevel(1);
				
			} */
			
			
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
			String s = printLootOptions(list, player);
			out.println(s);
			
			//SHOW OPTIONS
			if (!s.equals("Sorry, "+player.getName ()+" can't loot here."))
			{
				int sel = sc.nextInt();
				sc.nextLine();
				sel--;
				InventoryTwo selected = new InventoryTwo(list.get(sel).getType());
				tr.getTrainCar(car).getPlatform(player.getLevel()).getInventory().removeInventory(selected, player);
			}
			
		}break; 
		
		
		case "marshall": {
			
			System.out.println ("Where would "+player.getName ()+" like to move the marshall?");
			
			String dir = "";
			
			if (marshall.currentCar != 0 && marshall.currentCar != 4) {
				
				System.out.println ("1.\t 1 right\n2.\t 1 left");
				
				String temp = sc.nextLine ();
				
				if (temp.equals ("1")) {
					
					dir = "right";
					
				}
				else {
					
					dir = "left";
					
				}
				
			}
			else if (marshall.currentCar == 0) {
				
				System.out.println ("1.\t 1 right");
				
				String temp = sc.nextLine ();
				
				dir = "right";
				
			}
			else if (marshall.currentCar == 4) {
				
				System.out.println ("1.\t 1 left");
				
				String temp = sc.nextLine ();
				
				dir = "left";
				
			}
			
			if (dir.equals ("left")) {
				
				tr.getTrainCar(marshall.currentCar).getPlatform (marshall.currentLevel).removePlayer(marshall.getName ());
				
				tr.getTrainCar(marshall.currentCar - 1).getPlatform (marshall.currentLevel).addPlayer(marshall);
				
				marshall.updateCurrentCar (marshall.currentCar - 1);
				
				System.out.println ("LEFT");
				
				System.out.println (marshall.currentCar);
				
			}
			else if (dir.equals ("right")) {
				
				tr.getTrainCar(marshall.currentCar).getPlatform (marshall.currentLevel).removePlayer(marshall.getName ());
				
				tr.getTrainCar(marshall.currentCar + 1).getPlatform (marshall.currentLevel).addPlayer(marshall);
				
				marshall.updateCurrentCar (marshall.currentCar + 1);
				
				System.out.println ("RIGHT");
				
				System.out.println (marshall.currentCar);
				
			}
			
			for (int i = 0; i < tr.getTrainCar(marshall.currentCar).getPlatform(0).getCharacterList().size(); i++) {
				
				if (!tr.getTrainCar(marshall.currentCar).getPlatform(0).getCharacterList().get (i).equals (marshall)) {
					
					Character tempC = tr.getTrainCar(marshall.currentCar).getPlatform(0).getCharacterList().remove (i);
					
					tr.getTrainCar(marshall.currentCar).getPlatform(1).addPlayer (tempC);
					
				}
				
			}
			
		} break;
		
		
		}
	}
	
	
}
