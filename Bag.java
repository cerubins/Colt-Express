
public class Bag extends Inventory implements Basics
{
	private int bagAmount;
	private String name; 
	
	public Bag()
	{
		bagAmount = 0;
		name = "";
	}
	public Bag(int x)
	{
		bagAmount = x;
		name = "bag";
	}
	public void setAmount(int x)
	{
		bagAmount = x;
	}
	public int getAmount()
	{
		return bagAmount;
	}
	public String getType()
	{
		return name;
	}
	
}
