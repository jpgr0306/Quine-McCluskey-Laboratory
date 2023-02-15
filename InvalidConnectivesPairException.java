public class InvalidConnectivesPairException extends Exception{

	private static final long serialVersionUID = 3198988852689780459L;

	public String getMessage()
	{
		return "You cannot negate the OR connective!\n";
	}
}