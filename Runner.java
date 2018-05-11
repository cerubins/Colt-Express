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
		
		Round round = new Round(mainTrain, finalchar, hands, bulletCards, discard, draw);
		
		round.First();
		
		round.playRound();
		
		for(int i = 1; i<4; i++)
		{
			finalchar.add(finalchar.remove(0));
			
			round = new Round(mainTrain, finalchar, hands, bulletCards, discard, draw);
			
			round.playRound();
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
