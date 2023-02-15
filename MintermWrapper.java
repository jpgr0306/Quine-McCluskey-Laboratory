import java.util.ArrayList;

public class MintermWrapper {
	private int ticket;
	private ArrayList<Integer> values;
	private String binary;
	private boolean isUsed;
	
	public MintermWrapper(String binary,int ticket)
	{
		this.binary=binary;
		this.isUsed=false;
		values = new ArrayList<Integer>();
		this.ticket=ticket;
	}
	
	public ArrayList<Integer> getValues()
	{
		return values;
	}
	
	public void addValue(int newValue)
	{
		values.add(newValue);
	}
	
	public void addCombination(ArrayList<Integer> min1, ArrayList<Integer> min2)
	{
		for(Integer aux:min1)
		{
			if(!isInList(aux))
			{
				values.add(aux);
			}
		}
		for(Integer aux:min2)
		{
			if(!isInList(aux))
			{
				values.add(aux);
			}
		}
	}
	
	public boolean isInList(int min)
	{
		for(Integer aux:values)
		{
			if(aux==min)
			return true;
		}
		return false;
	}
	
	public void setMin(String binary)
	{
		this.binary=binary;
	}
	
	public String getMin()
	{
		return binary;
	}
	
	public boolean getUsage()
	{
		return isUsed;
	}
	
	public int getTicket()
	{
		return ticket;
	}
	
	public void setTicket(int ticket)
	{
		this.ticket=ticket;
	}
	
	public void setUsage(boolean isUsed)
	{
		this.isUsed=isUsed;
	}
}