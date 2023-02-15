import java.util.ArrayList;

public class Minterm
{
	private String minterms;
	private String dontCares;
	private int numterms;
    private ArrayList<String> minSep;
    private ArrayList<MintermWrapper> minGroups; 
    private ArrayList<Integer> listDont;
    private ArrayList<MintermWrapper> primeImplicants;
    private ArrayList<Combination> solutions;

    public Minterm(String str, String valsDontCares) throws Exception
    {
        minterms=str;
        dontCares=valsDontCares;
        minSep=new ArrayList<String>();
        numterms=0;
        minGroups=new ArrayList<MintermWrapper>();
        listDont=new ArrayList<Integer>();
        primeImplicants=new ArrayList<MintermWrapper>();
        solutions=new ArrayList<Combination>();
        solveQuineMcCluskey();
    }
    
    public void solveQuineMcCluskey() throws Exception
    {
    	verifyMinterm();
    	numterms=getNumTerms();
    	splitMinterms();
    	merge(0,minGroups.size(),numterms);
    	selectPrimeImplicants();
    	ArrayList<Integer> listPrimes = new ArrayList<Integer>();
    	if(minGroups.size()!=1)
    		 listPrimes = selectEssentialPrimeImplicants();
    	evaluateCoverage(listPrimes);
    }
    
    private void verifyMinterm() throws Exception
    {
        if(compareMintermAndDontCare()){
        	throw new ParadoxException();
        }
    	ExpressionAnalyzer anal1 = new ExpressionAnalyzer(minterms);
    	ExpressionAnalyzer anal2 = new ExpressionAnalyzer(dontCares);
    	anal1.verifyMinterm(false);
    	anal2.verifyMinterm(true);
    	minSep = anal1.tokenizer(",");
    	ArrayList<String> dontCares = anal2.tokenizer(",");
    	minSep.addAll(dontCares);
    	listDont=convertTokenizer(dontCares);
    }
    
    private boolean compareMintermAndDontCare(){
    	for(int i = 0; i < minterms.length(); i++){
    		if(minterms.charAt(i) == ',') 
    			continue;
    		for(int j = 0; j < dontCares.length(); j++){
    			if(dontCares.charAt(j) == ',')
    				continue;
    			if(minterms.charAt(i) == dontCares.charAt(j))
    				return true;
    		}
    	}
    	return false;
    }
    
    private void splitMinterms()
    {
    	ArrayList<MintermWrapper> minterms = new ArrayList<MintermWrapper>();
    	for(int i=0; i<minSep.size(); i++)
    	{
    		String binMin=genBinary(minSep.get(i),numterms);
    		int count=countOnes(binMin);
            MintermWrapper newMinterm = new MintermWrapper(binMin,count);
            newMinterm.addValue(Integer.parseInt(binMin,2));
            minterms.add(newMinterm);
        }
    	minGroups=minterms;
    }
    
    private void merge(int start, int goTo, int maxInt)
    {
	    ArrayList<MintermWrapper> aux1;
	    ArrayList<MintermWrapper> aux2;
	    for(int i=0; i<maxInt; i++)
	    {
	    	aux1 = new ArrayList<MintermWrapper>();
	    	aux2 = new ArrayList<MintermWrapper>();
		    for(int j=start; j<goTo; j++)
		    {
		    	if(minGroups.get(j).getTicket()==i)
		    	{
		    		aux1.add(minGroups.get(j));
		    	}
		    	if(minGroups.get(j).getTicket()==i+1)
		    	{
		    		aux2.add(minGroups.get(j));
		    	}
		    }
		    for(MintermWrapper min1:aux1)
		    {
		    	for(MintermWrapper min2:aux2)
		    	{
		    		String str="";
		    		int count=0;
		    		boolean toDo=true;
		    		for(int k=0; k<min1.getMin().length(); k++)
		    		{
		    			if(min1.getMin().charAt(k)=='_'&&min2.getMin().charAt(k)!='_'||min1.getMin().charAt(k)!='_'&&min2.getMin().charAt(k)=='_')
		    			toDo=false;
		    			if(min1.getMin().charAt(k)!=min2.getMin().charAt(k))
		    			{
		    				str+="_";
		    				count++;
		    			}
		    			else
		    			{
		    				str+=min1.getMin().charAt(k);
		    			}
		    		}
		    		if(toDo&&count<=1)
		    		{
		    			min1.setUsage(true);
		    			min2.setUsage(true);
		    			MintermWrapper newMin = new MintermWrapper(str,min1.getTicket());
						newMin.addCombination(min1.getValues(),min2.getValues());
						if(!isDuplicate(newMin))
						{
							minGroups.add(newMin);
						}
		    		}
		    	}
		    }
	    }
	    if(start!=goTo)
	    {
	    	merge(goTo,minGroups.size(),maxInt--);
	    }
    }
    
    private void selectPrimeImplicants()
    {
    	for(int i=0; i<minGroups.size(); i++)
    	{
    		if(minGroups.get(i).getUsage())
    		{
    			minGroups.remove(i);
    			i--;
    		}
    	}
    }
    
    private ArrayList<Integer> selectEssentialPrimeImplicants()
    {
    	int count=0;
    	ArrayList<Integer> listPrimes = new ArrayList<Integer>();
    	for(MintermWrapper min:minGroups)
    	{
    		for(Integer aux:min.getValues())
    		{
    			if(!isInList(aux,listPrimes)&&!isInList(aux,listDont))
    			{
    				listPrimes.add(aux);
    				count++;
    			}
    		}
    	}
    	boolean[][] implicantsTable = new boolean[minGroups.size()][count];
    	for(int i=0; i<minGroups.size(); i++)
    	{
    		for(int j=0; j<count; j++)
    		{
    			if(minGroups.get(i).isInList(listPrimes.get(j)))
    			implicantsTable[i][j]=true;
    		}
    	}
    	int countAux;
    	int[] countImps = new int[count];
    	for(int i=0; i<count; i++)
    	{
    		countAux=0;
    		for(int j=0; j<minGroups.size(); j++)
    		{
    			if(implicantsTable[j][i])
    			countAux++;
    		}
    		countImps[i]=countAux;
    	}
    	for(int i=0; i<minGroups.size(); i++)
    	{
	    	for(int j=0; j<count;j++)
	    	{
	    		if(countImps[j]==1&&implicantsTable[i][j])
	    		{
	    			primeImplicants.add(minGroups.get(i));
	    			break;
	    		}
	    	}
    	}
    	return listPrimes;    	
    }
    
    private void evaluateCoverage(ArrayList<Integer> listPrimes)
    {
    	MintermWrapper[] toCombine = new MintermWrapper[minGroups.size()];
    	ArrayList<Combination> results = new ArrayList<Combination>();
    	for(int i=0; i<minGroups.size(); i++)
    	{
    		toCombine[i]=minGroups.get(i);
    	}
    	for(int i=1; i<=minGroups.size(); i++)
    	{
    		MintermWrapper[] aux = new MintermWrapper[i];
    		getAllCombinations(toCombine, aux, results, 0, toCombine.length-1, 0, i);
    	}
    	ArrayList<Combination> solutions = new ArrayList<Combination>();
    	ArrayList<Integer> necessaryPrimes;
    	int minimum=results.size();
    	for(Combination comb:results)
    	{
    		necessaryPrimes = copyInt(listPrimes);
    		for(MintermWrapper min:comb.getElements())
    		{
    			for(Integer val:min.getValues())
    			{
    				if(necessaryPrimes.indexOf(val)!=-1)
    				{
    					necessaryPrimes.remove(Integer.valueOf(val));
    				}
    			}
    		}
    		if(necessaryPrimes.isEmpty())
    		{
    			solutions.add(comb);
    			if(comb.getNumElements()<minimum)
    				minimum=comb.getNumElements();
    		}    		
    	}
    	for(int i=0; i<solutions.size(); i++)
    	{
    		if(solutions.get(i).getNumElements()>minimum)
    		{
    			solutions.remove(i);
    			i--;
    		}	
    	}
    	this.solutions=solutions;
    }
    
    private ArrayList<Integer> copyInt(ArrayList<Integer> source)
    {
    	ArrayList<Integer> dest = new ArrayList<Integer>();
    	if(source==null)
    		return new ArrayList<Integer>();
    	for(Integer num:source)
    	{
    		dest.add((int)num);
    	}
    	return dest;
    }
    
    //Unused
	/*private ArrayList<String> copyStr(ArrayList<String> source)
    {
    	ArrayList<String> dest = new ArrayList<String>();
    	if(source==null)
    		return new ArrayList<String>();
    	for(String num:source)
    	{
    		dest.add(num);
    	}
    	return dest;
    }*/
    
    private void getAllCombinations(MintermWrapper[] toCombine, MintermWrapper[] aux, ArrayList<Combination> results, int start, int end, int index, int r)
    {
    	if (index == r)
        {
    		Combination comb = new Combination();
            for (int j=0; j<r; j++)
            	comb.addCombination(aux[j]);
            results.add(comb);
            return;
        }
	    for (int i=start; i<=end && end-i+1 >= r-index; i++)
	    {
	        aux[index] = toCombine[i];
	        getAllCombinations(toCombine, aux, results, i+1, end, index+1, r);
	    }
     }
    
    private boolean isInList(int val, ArrayList<Integer> values)
    {
    	for(Integer num:values)
    	{
    		if(val==num)
    		return true;
    	}
    	return false;
    }
    
    private boolean isDuplicate(MintermWrapper toVerify)
    {
    	boolean flag=true;
    	for(int i=0; i<minGroups.size(); i++)
    	{
    		flag=true;
    		MintermWrapper aux=minGroups.get(i);
    		for(Integer num:toVerify.getValues())
    		{
    			if(!aux.isInList(num))
    			flag=false;
    		}
    		if(flag)
    		return true;
    	}
    	return false;
    }
    
    private int countOnes(String min)
    {
    	int count=0;
    	for(int i=0; i<min.length(); i++)
    	{
    		if(min.charAt(i)=='1')
    		count++;
    	}
    	return count;
    }
    
    private String genBinary(String str, int numVar)
    {
    	str=Integer.toBinaryString(Integer.parseInt(str));
    	int count = numVar-str.length();
    	String aux="";
        for(int j = 0; j < count; j++)
        {
            aux+="0";
        }
        return (aux+str);
    }
    
    public String getSolution(String varList)
    {
    	String expression="";
    	for(int i=0; i<solutions.size(); i++)
    	{
    		for(MintermWrapper min:solutions.get(i).getElements())
    		{
    			String minterm="";
    			for(int j=0; j<min.getMin().length(); j++)
    			{
    				if(min.getMin().charAt(j)=='1')
    					minterm+=varList.charAt(j);
    				else if(min.getMin().charAt(j)=='0')
    					minterm+="~"+varList.charAt(j);
    			}
    			expression+=minterm+"+";
    		}
    		expression=expression.substring(0,expression.length()-1)+" or\n";
    	}
    	boolean flag = false;
    	if(minSep.size() == (int)Math.pow(2, numterms)){
    		flag = true;
	    	for(String min: minSep){
	    		if(Integer.parseInt(min) < 0 || Integer.parseInt(min) > (int)Math.pow(2, numterms)-1){
	    			flag = false;
	    			break;
	    		}
	    	}
	    }
    	if(flag)
    		return "1";
    	if(minSep.size() <= 0)
			return "0";
    	return expression.substring(0,expression.length()-4);
    }
    
    private int getNumTerms()
    {
    	int max=0, count=0;
    	for(String minVal:minSep)
    	{
    		if(max<Integer.parseInt(minVal))
    			max=Integer.parseInt(minVal);
    	}
    	while(max!=0)
    	{
    		max/=2;
    		count++;
    	}
    	return count;
    }

    private ArrayList<Integer> convertTokenizer(ArrayList<String> dontCares)
    { 
    	ArrayList<Integer> aux = new ArrayList<Integer>();
    	for(String dont:dontCares)
    	{
    		aux.add(Integer.parseInt(dont));
    	}
    	return aux;
    }
    
    public static void main (String args[]) throws Exception
    {
    	final String standardVarList = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	//Expression exp = new Expression("a~b~c+~a+bc","");
    	//System.out.println(exp.getSolution());
    	Minterm min2 = new Minterm("1,2","");
    	System.out.print(min2.getSolution(standardVarList));
    }
}