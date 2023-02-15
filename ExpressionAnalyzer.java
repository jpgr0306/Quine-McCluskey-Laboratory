import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * This class is used to analyze the expressions and minterms provided by the user, throwing exceptions when is necessary
 * @author Alexandre Zucki Baciuk
 * @author João Pedro Gava Ribeiro
 * @version 1.0
 */

public class ExpressionAnalyzer
{
	private static final int maxVar = 10;
    private String str;
    private String varList;
    
    /**
     * Constructor of ExpressionAnalyzer
     * @param str The expression or minterms provided by the user
     */
    public ExpressionAnalyzer(String str)
    {
        this.str=str;
    }
    
    /**
     * Verify the expression which come from {@link Expression#toTruthTable() toTruthTable} using regular expressions ({@code regex})
     * @return true whether the expression matches one of the irregular patterns
     */
    
    //ADD NEW EXCEPTIONS
    
    public void verifyExpression() throws Exception
    {
    	Pattern pattern1 = Pattern.compile("^$",Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("~\\+", Pattern.CASE_INSENSITIVE);
        Pattern pattern3 = Pattern.compile("\\+\\+",Pattern.CASE_INSENSITIVE);
        Pattern pattern4 = Pattern.compile("~$",Pattern.CASE_INSENSITIVE);
        Pattern pattern5 = Pattern.compile("\\+$",Pattern.CASE_INSENSITIVE);
        Pattern pattern6 = Pattern.compile("^\\+",Pattern.CASE_INSENSITIVE);
        Pattern pattern7 = Pattern.compile("[^A-z+~]",Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(str=str.replaceAll(" ",""));
        Matcher matcher2 = pattern2.matcher(str);
        Matcher matcher3 = pattern3.matcher(str);
        Matcher matcher4 = pattern4.matcher(str);
        Matcher matcher5 = pattern5.matcher(str);
        Matcher matcher6 = pattern6.matcher(str);
        Matcher matcher7 = pattern7.matcher(str);
    	if(matcher1.find())
        	throw new NullListException();
    	if(matcher2.find())
    		throw new InvalidConnectivesPairException(); 
    	if(matcher3.find())
    		str=str.replaceAll("\\+\\+", "+");
        if(matcher4.find())
        	str=str.substring(0,str.length()-1);
        if(matcher5.find())
        	str=str.substring(0,str.length()-1);
        if(matcher6.find())
        	str=str.substring(1,str.length());
        if(matcher7.find())
        	throw new InvalidCharacterException(str,"[A-z+~]");
        generateVarList();
        if(varList.length() > maxVar)
        	throw new VariablesOverflowException();
    }
    
    /**
     * Verify the minterm using regular expressions ({@code regex})
     * @param ignore true if is a minterm and false if is a don't care
     * @throws NullListException The list of minterms is empty
     * @throws InvalidCharacterException The list has one or more invalid characters (not integer numbers)
     */
    
    public void verifyMinterm(boolean ignore) throws Exception
    {
        //Pattern pattern1 = Pattern.compile("^$", Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("^,", Pattern.CASE_INSENSITIVE);
        Pattern pattern3 = Pattern.compile(",$", Pattern.CASE_INSENSITIVE);
        Pattern pattern4 = Pattern.compile("[^0-9,]",Pattern.CASE_INSENSITIVE);
        //Matcher matcher1 = pattern1.matcher(str=str.replaceAll(" ","").replaceAll(",,",","));
        Matcher matcher2 = pattern2.matcher(str);
        Matcher matcher3 = pattern3.matcher(str);
        Matcher matcher4 = pattern4.matcher(str);
        //if(!ignore && matcher1.find())
            //throw new NullListException();
        if(matcher2.find())
            str=str.substring(1,str.length()-1);
        if(matcher3.find())
            str=str.substring(0,str.length()-2);
        if(matcher4.find())
            throw new InvalidCharacterException(str,"[0-9,]");
            /*if(ex!=null)
            {
            	JOptionPane.showMessageDialog(null,ex.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            	throw ex;
            }*/
    }
    
    /**
     * Split the String tokens in an ArrayList of Strings
     * @param del The delimiter used to tokenize
     * @return The splited String tokens
     */
    
    public ArrayList<String> tokenizer(String del)
    {
        ArrayList<String> aux = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str,del);  
        while (st.hasMoreTokens())
        {
        	aux.add(st.nextToken());
        }
        return aux;
    }
    
    /**
     * Add the characters to the list of variables
     */
    
    private void generateVarList()
    {
        int i = 0;
        varList = "";
        while(i<str.length())
        {
            if(!varList.contains(""+str.charAt(i)) && str.charAt(i)!='+' && str.charAt(i)!='~')
            varList+=str.charAt(i);
            i++;
        }
    }

    
    /**
     * Return the list of variables
     * @return List of variables
     */
    public String getVarList(){
    	return varList; 
    }
}