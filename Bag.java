
public class Bag extends InventoryTwo implements Basics
{
	private int bagAmount;
	private String name; 
	
	public Bag()
	{
		super();
		bagAmount = 0;
		name = "";
	}
	public Bag(int x)
	{
		super("bag");
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
