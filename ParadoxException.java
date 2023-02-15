public class ParadoxException extends Exception{

	private static final long serialVersionUID = -972670053568181193L;

	public String getMessage(){
		return "Minterms and Don't Cares cannot share similar terms!\n";
	}
}