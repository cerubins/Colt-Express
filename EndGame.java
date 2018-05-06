import java.util.ArrayList;
import java.util.TreeMap;


public class EndGame 
{
	private TreeMap<Integer, ArrayList<Character>> ranking;
	
	public EndGame()
	{
		ranking = new TreeMap<>();
	}
	public EndGame(ArrayList<Character> c)
	{
		ranking = new TreeMap<>();
		for (int x = 0; x<c.size(); x++)
		{
			if (ranking.keySet().contains(c.get(x).getTotal()))
			{
				ranking.get(c.get(x).getTotal()).add(c.get(x));
			}
			else
			{
				ArrayList<Character> list = new ArrayList<>();
				list.add(c.get(x));
				ranking.put(c.get(x).getTotal(), list);
			}
		}
	}	
	public TreeMap<Integer, ArrayList<Character>> getResults()
	{
		return ranking;
	}
}
