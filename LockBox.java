
public class LockBox extends Inventory implements Basics
{
	private int lbAmount;
	private String name;
	
	public LockBox()
	{
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
