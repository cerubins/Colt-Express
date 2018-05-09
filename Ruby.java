
public class Ruby extends InventoryTwo implements Basics
{
	private int rubyAmount;
	private String name;
	
	public Ruby()
	{
		super("ruby");
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
