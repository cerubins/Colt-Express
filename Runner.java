import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;


public class Runner {
	
	private static ArrayList<String> characterNames = new ArrayList<String>();
	
	private static ArrayList<Character> finalchar = new ArrayList<Character>();
	
	private static TreeMap <Character, ArrayList <ActionCard>> hands = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private static TreeMap <Character, ArrayList <ActionCard>> bulletCards = new TreeMap <Character, ArrayList <ActionCard>> ();
		
	private static TreeMap <Character, ArrayList <ActionCard>> discard = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private static TreeMap <Character, ArrayList <ActionCard>> draw = new TreeMap <Character, ArrayList <ActionCard>> ();
	

	public static void main(String[] args)
	{
		
		RoundCardSelector rs = new RoundCardSelector ();
		
		ArrayList <RoundCard> roundCardList = rs.select ();
		
		Train mainTrain = new Train();
		
		fillOutNames();
		
		for(int i = 0; i < 4; i++)
		{
			System.out.println("What character? Choose: ");
			
			for(int j = 0; j<characterNames.size(); j++)
			{
				
				System.out.println((j+1) + ". " + characterNames.get(j));
				
			}
			
			Scanner key = new Scanner(System.in);
			
			int use = key.nextInt();
			
			System.out.println("Would you like to be in the Caboose(4) or the car in front of the Caboose(3)?");
			
			int pos = key.nextInt();
			
			Character created = new Character(characterNames.remove(use-1), pos, 0, 0, 0);
			
			finalchar.add(created);
			
			mainTrain.getTrainCar(pos).getPlatform(0).addPlayer(created);
			
		}
		
		mainTrain.getTrainCar(0).getPlatform(0).addPlayer(new Character ("marshall", 0, 0, 0, 0));
		
		finalchar.add(new Character("marshall", 0, 0, 0, 0));
		
		Round round = new Round(mainTrain, finalchar, hands, bulletCards, discard, draw, roundCardList.get (0));
		
		roundCardList.add (roundCardList.remove (0));
		
		round.First();
		
		round.playRound();
		
		boolean toStop = false;
		
		while (!toStop) 
		{
			finalchar.add(finalchar.remove(0));
			
			roundCardList.add (roundCardList.remove (0));
			
			RoundCard r = roundCardList.get (0);
			
			if (!r.getIsStopCard ()) {
				
				round = new Round(mainTrain, finalchar, hands, bulletCards, discard, draw, r);
				
				round.startOfRound();
			
				round.playRound();
			
			}
			else {
				
				break;
				
			}
	
		}
		
	
		EndGame eg = new EndGame(finalchar);
		TreeMap<Integer, ArrayList<Character>> rankings = eg.getResults();
		ArrayList<Character> list = new ArrayList<>();
		int count = 1;
		for (int i : rankings.keySet())
		{
			for (int x = 0; x<rankings.get(i).size(); x++)
			{
				list.add(rankings.get(i).get(x));
			}
		}
		count = 1; 
		for (int x =list.size()-1; x>=0; x--)
		{
			if (!list.get(x).getName().equals("marshall"))
			{
				System.out.println(count+".\t"+list.get(x).getName()+"\t"+list.get(x).getTotal());
				count++;
			}
		}
	}
	
	public static void fillOutNames()
	{
		characterNames.add("django");
		
		characterNames.add("cheyenne");
		
		characterNames.add("doc");
		
		characterNames.add("ghost");
		
		characterNames.add("tuco");
		
		characterNames.add("belle");
	}
	
}
