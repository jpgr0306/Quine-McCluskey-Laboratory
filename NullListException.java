public class NullListException extends Exception{

	private static final long serialVersionUID = -7401127284920065133L;

	public String getMessage()
	{
		return "List of minterms must not be empty!\n";
	}
}
