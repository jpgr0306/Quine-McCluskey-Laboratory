import java.util.ArrayList;

public class Combination {
	private ArrayList<MintermWrapper> elements;
	private int numElements;
	
	public Combination()
	{
		numElements=0;
		elements=new ArrayList<MintermWrapper>();
	}
	
	public void addCombination(MintermWrapper min)
	{
		elements.add(min);
		incrementNumElements();
	}
	
	public ArrayList<MintermWrapper> getElements()
	{
		return elements;
	}
	
	public int getNumElements()
	{
		return numElements;
	}
	
	private void incrementNumElements()
	{
		numElements++;
	}
}
