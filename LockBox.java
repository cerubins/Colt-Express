
public class LockBox extends InventoryTwo implements Basics
{
	private int lbAmount;
	private String name;
	
	public LockBox()
	{
		super("lockBox");
		name = "lockBox";
		lbAmount = 1000;
	}
	public int getAmount()
	{
		return lbAmount; 
	}
	public String getType()
	{
		return name;
	}
}
