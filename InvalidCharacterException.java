public class InvalidCharacterException extends Exception{

	private static final long serialVersionUID = 3983576138720001623L;
	private String str;
	private String validChars;
	
	public InvalidCharacterException(String str, String validChars)
	{
		this.str=str;
		this.validChars=validChars;
	}
	
	public String getMessage()
	{
		String aux="";
		for(int i=0; i<str.length(); i++)
		{
			if((str.charAt(i)+"").matches(validChars))
				aux+=" ";
			else
				aux+="^";
		}
		return "List of minterms and Don't Cares must not have invalid characters!\n"+str+"\n"+aux;
	}
}