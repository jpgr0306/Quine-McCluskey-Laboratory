import java.util.ArrayList;
import java.util.Arrays;

public class Expression
{
    private String exp;
    private String varList;
    private int numVar;
    private ArrayList<String> expSep;
    private String dontCares;
    private int[][] truthTable;

    public Expression(String str, String dontCares) throws Exception
    {
        exp=str;
        this.dontCares=dontCares;
        ExpressionAnalyzer anal = new ExpressionAnalyzer(exp);
        anal.verifyExpression();
       	expSep = anal.tokenizer("+");
        varList = sort(anal.getVarList());
        numVar = varList.length();
        toTruthTable();
    }
    
    public String toTruthTable() throws Exception
    {
        genTruthTable();
        return minterms();
    }
    
    private void genTruthTable()
    {
        int[][] truthTable = new int[(int)Math.pow(2,numVar)][numVar+1];
        for(int i = 0; i < (int)Math.pow(2,numVar); i++)
        {
            String min = Integer.toBinaryString(i);
            int count = numVar-1;
            for(int j = min.length()-1; j >= 0; j--)
            {
                truthTable[i][count] = (min.charAt(j)=='1')? 1:0;
                count--;
            }
            for(int j = count; j >= 0; j--)
            {
                truthTable[i][j] = 0;
            }
            truthTable[i][numVar] = resultExpression(i,truthTable);
        }
        this.truthTable=truthTable;
    }
    
    private String minterms()
    {
        String aux="";
        //int count=0;
        for(int i = 0; i < (int)Math.pow(2,numVar); i++)
        {
            if(truthTable[i][numVar] == 1)
            {
            	//count++;
                aux+=i+",";
            }
        }
        /*if(count==Math.pow(2,numVar))
        	return "*1";
        else if(count==0)
        	return "*0";*/
        if(aux.length() <= 0)
        	return "";
        return aux.substring(0,aux.length()-1);
    }
    
    private int resultExpression(int i, int[][] truthTable)
    {
        ArrayList<String> temp = new ArrayList<String>();
        for(String str:expSep)
        {
            String aux = (String)str;
            //NOT redundancies
            aux = aux.replaceAll("~~","");
            for(int j=0; j < numVar; j++)
            {
                aux=aux.replaceAll(""+varList.charAt(j),""+truthTable[i][j]);
            }
            //NOT substitutions
            aux = aux.replaceAll("~0","1");
            aux = aux.replaceAll("~1","0");
            //AND substitutions
            while(aux.length()!=1)
            {
                aux=aux.replaceAll("00","0");
                aux=aux.replaceAll("01","0");
                aux=aux.replaceAll("10","0");
                aux=aux.replaceAll("11","1");
            }
            temp.add(aux);
        }
        int result = 0;
        for(String str:temp)
        {
            //OR substitutions
            if(str.equals("1"))
            {
                result=1;
                break;
            }
        }
        return result;
    }
    
    public String getSolution() throws Exception
    {
    	String result = toTruthTable();
    	if(result.equals("*0"))
    		return "0";
    	if(result.equals("*1"))
    		return "1";
    	Minterm min = new Minterm(result,dontCares);
    	return min.getSolution(varList);
    }
    
    public String getVarList()
    {
    	return varList;
    }
    
    private String sort(String str)
    {
    	char temp[] = str.toCharArray();
    	Arrays.sort(temp);
    	return new String(temp);
    }
    
    public String getTableString()
    {
    	String[] skipLines = dontCares.split(",");
    	String str="+";
    	int header = 4*numVar+exp.length()+2;
    	for(int i=0; i<header; i++)
    	{
    		str+="-";
    	}
    	str+="+\n";
    	for(int i=0; i<numVar; i++)
    	{
    		str+="| "+varList.charAt(i)+" ";
    	}
    	str+="| "+exp+" |\n";
    	int count = 0;
    	for(int i=0; i<Math.pow(2,numVar); i++)
    	{
    		if(count < skipLines.length && (""+i).equals(skipLines[count])){
    			count++;
    			continue;
    		}
    		for(int j=0; j<=numVar; j++)
    		{
    			if(j<numVar)
    			{
    				str+="| "+truthTable[i][j]+" ";
    			}
    			else
    			{
    				str+="| ";
    				for(int k=0; k<exp.length()/2; k++)
    				{
    					str+=" ";
    				}
    				str+=truthTable[i][j];
    				for(int k=0; k<exp.length()/2; k++)
    				{
    					str+=" ";
    				}
    				if(exp.length()%2==1)
    				{
    					str+=" ";
    				}
    			}
    		}
    		str+="|\n";
    	}
    	str+="+";
    	for(int i=0; i<header; i++)
    	{
    		str+="-";
    	}
    	str+="+\n";
    	return str;
    }
}
