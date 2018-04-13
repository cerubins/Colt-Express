
public class Ruby extends Inventory implements Basics
{
	private int rubyAmount;
	private String name;
	
	public Ruby()
	{
		name = "ruby";
		rubyAmount = 500;
	}
	
	public int getAmount()
	{
		return rubyAmount; 
	}
	public String getType()
	{
		return name;
	}
	
}
