import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

public class CSE535Assignment{
	private static String arg;
	private static Scanner infile;
	private static Scanner infile2;
	static String query;
	static String qu;
	static int flag=0;
	static ArrayList<Long> temp=new ArrayList<Long>();
	static ArrayList<Long> temp2=new ArrayList<Long>();
	static LinkedHashMap<String,term2> dict2=new LinkedHashMap<String,term2>();
	static LinkedHashMap<String,term> dict=new LinkedHashMap<String,term>();
	static HashSet<Long> docu=new HashSet<Long>();
	
	
	public static FileWriter log;
	static int compcount=0;
	static int compc=0;
	static int comp=0;
	
	private static ArrayList<Long> t;
	static int fl=0;
	static HashMap <Integer,String> hm= new HashMap<Integer,String>();
	static HashSet<Long> hs=new HashSet<Long>();
	static ArrayList<Integer> a= new ArrayList<Integer>(); 
	public static void main(String[] args) throws IOException {	
		arg=args[3];
		ArrayList<String> q=new ArrayList<String>();
		int i=0;
		String file=args[0];
		FileReader input= new FileReader(file);
		infile = new Scanner(input);
		indexdoc();
		FileReader input2=new FileReader(file);
		infile2=new Scanner(input2);
	    indexterm();
	    FileReader in=new FileReader(args[3]);
	    Scanner s=new Scanner(in);
	    log=new FileWriter(args[1]);
	    getTopk(Integer.parseInt(args[2]));
	    while(s.hasNext())
	    	
	    {
	    	query=s.next();
	    	q.add(query);
	    	getpostings(query);
	    }
	    s.close();
	    FileReader inp=new FileReader(args[3]);
	    Scanner s2=new Scanner(inp);
	    	long startTime = System.nanoTime();
	    	while(s2.hasNext())
	    	{	
	    	qu=s2.next();	
	    	t=TAATA(qu);
	    	}
	    	long endTime = System.nanoTime();
	    	long duration = (endTime - startTime);
	    	Collections.sort(t);
	
	    
	    
	    log.append("\nFUNCTION:termAtATimeQueryAnd\t");
	    Iterator it=q.iterator();
	    while(it.hasNext())
	    {	
	    log.append(it.next().toString());
	    if(i!=(q.size()-1))
	    	log.append(",");
	    	i++;
	    }
	    log.append("\n"+t.size()+"\tdocuments are found");
	    log.append("\n"+compcount+"\tcomparisons are made");
	    log.append("\n"+duration+"\tseconds are used");
	    log.append("\nResult:\t");
	    Iterator n=t.iterator();
	    int j=0;
	    while(n.hasNext())
	    {	
	    log.append(n.next().toString());
	    if(j!=(t.size()-1))
	    	log.append(",");
	    	j++;
	    }
	    
	    
	    FileReader inp1=new FileReader(args[3]);
	    Scanner s3=new Scanner(inp1);
	    	long start = System.nanoTime();
	    	while(s3.hasNext())
	    	{	
	    	qu=s3.next();	
	    	hs=TAATOr(qu);
	    	}	
	    	long end = System.nanoTime();
	    	long dur = (end - start);
	    	ArrayList<Long> sortedhs= new ArrayList<Long>(hs);
	    log.append("\nFUNCTION:\ttermAtATimeQueryOr\t");
	    while(it.hasNext())
	    {	
	    log.append(it.next().toString());
	    if(i!=(q.size()-1))
	    	log.append(",");
	    	i++;
	    }
	    log.append("\n"+hs.size()+"\tdocuments are found");
	    log.append("\n"+dur+"\tseconds are used");
	    log.append("\nResult:\t");
	    Iterator x=sortedhs.iterator();
	    int k=0;
	    int l=0;
	    while(x.hasNext())
	    {
	    	l=1;
	    log.append(x.next().toString());
	    if(k!=(hs.size()-1))
	    	log.append(",");
	    	k++;
	    }
	    if(l!=1)
	    {
	    	log.append("Terms Not Found");
	    }
	    s3.close();
	    
	    ArrayList<Long> dat=new ArrayList<Long>();
    	long st = System.nanoTime();
    	dat=DAATA();  
    	long en = System.nanoTime();
    	long du = (en - st);
    	Collections.sort(dat);
    log.append("\nFUNCTION:\tdocAtATimeQueryAnd\t");
    while(it.hasNext())
    {	
    log.append(it.next().toString());
    if(i!=(q.size()-1))
    	log.append(",");
    	i++;
    }
    log.append("\n"+dat.size()+"\tdocuments are found");
    log.append("\n"+compc+"\tcomparisons are made");
    log.append("\n"+dur+"\tseconds are used");
    log.append("\nResult:\t");
    Iterator f=dat.iterator();
    int r=0;
    int g=0;
    while(f.hasNext())
    {
    	g=1;
    log.append(f.next().toString());
    if(r!=(dat.size()-1))
    	log.append(",");
    	r++;
    }
    if(g!=1)
    {
    	log.append("Terms Not Found");
    }
    

    	long sta = System.nanoTime();
    	HashSet<Long> datOR=new HashSet<Long>();
    	datOR=DAATO();  
    	long ending = System.nanoTime();
    	long dura = (ending - sta);
    log.append("\nFUNCTION:docAtATimeQueryOr\t\t");
    while(it.hasNext())
    {	
    log.append(it.next().toString());
    if(i!=(q.size()-1))
    	log.append(",");
    	i++;
    }
    log.append("\n"+datOR.size()+"\tdocuments are found");
    log.append("\n"+comp+"\tcomparisons are made");
    log.append("\n"+dura+"\tseconds are used");
    log.append("\nResult:\t");
    Iterator v=datOR.iterator();
    int w=0;
    int u=0;
    while(v.hasNext())
    {
    	u=1;
    log.append(v.next().toString());
    if(w!=(datOR.size()-1))
    	log.append(",");
    	w++;
    }
    if(u!=1)
    {
    	log.append("Terms Not Found");
    }
    s3.close();
    s2.close();
	    
	    log.close();
	}

	
	
	public static void indexdoc()
		{
			String line;
			String[] str;
			String size;
			String doc;
			String newstr;
			String[] post;
			String[] docs;	
		
		String delims="[\\[|,|\\]]";		
		while (infile.hasNextLine())
		{  
			    
				line=infile.nextLine();
				str=line.split("(\\\\)");
				size=str[1];
				int si=Integer.parseInt(size.substring(1));
				LinkedList<posting> ind=new LinkedList<posting>();
				for(int j=0;j<str.length;j++)
				{   
					if(j==2)
					{
						newstr=str[j].substring(1);
						post=newstr.split(delims);
						for(int k=1;k<post.length;k++)
						{
						docs=post[k].split("/");
						doc=docs[0].trim();
						ind.add(new posting(Long.valueOf(doc).longValue(),Integer.parseInt(docs[1])));
						}
					}
				}
				Collections.sort(ind);
				term t=new term(si,ind);
				hm.put(si,str[0]);
				a.add(si);
		     	dict.put(str[0],t);
		}
		infile.close();;			 
	}

		
		
	public static void indexterm()
	{
		String line2;
		String[] str2;
		String size2;
		String doc2;
		String newstr2;
		String[] post2;
		String[] docs2;	
	
	String delims2="[\\[|,|\\]]";		
	while (infile2.hasNextLine())
	{  
		    
			line2=infile2.nextLine();
			str2=line2.split("(\\\\)");
			size2=str2[1];
			int si2=Integer.parseInt(size2.substring(1));
			LinkedList<posting2> ind2=new LinkedList<posting2>();
			for(int j=0;j<str2.length;j++)
			{   
				if(j==2)
				{
					newstr2=str2[j].substring(1);
					post2=newstr2.split(delims2);
					for(int k=1;k<post2.length;k++)
					{
					docs2=post2[k].split("/");
					doc2=docs2[0].trim();
					ind2.add(new posting2(Long.valueOf(doc2).longValue(),Integer.parseInt(docs2[1])));
					}
				}
			}
			Collections.sort(ind2,Collections.reverseOrder());
			term2 t=new term2(si2,ind2);
	     	dict2.put(str2[0],t);  
	}
	infile2.close();	
	}
	
	public static void getTopk(Integer in) throws IOException 
	{  
		log.write("FUNCTION: getTopK "+in);
		Collections.sort(a,Collections.reverseOrder());
		log.write("\nResult:\t");
		for(int i=0;i<in;i++)
		{
			log.write(hm.get(a.get(i)));
			if(i!=in-1)
			{
				log.write(",");
			}
		}	
		}
	
	
	public static void getpostings(String q) throws IOException
	{
		log.write("\nFUNCTION:getPostings_"+q);
		if(dict.containsKey(q))
		{
			term t=dict.get(q);
			t.displayterm();
		}
		else
		{
			log.append("\nTerm\t"+q+"\tNot Found");			
		}
		if(dict2.containsKey(q))
		{
			term2 t=dict2.get(q);
			t.displayterm();
		}
	}
	
	
	public static ArrayList<Long> TAATA(String q) throws IOException
	{
		ArrayList<Long> doc=TAATAnd(q);
		if(flag==0)
		{
			temp.addAll(doc);
			flag=1;
			return temp;
		}
		else
		{		
			ListIterator<Long> i=doc.listIterator();
			while (i.hasNext()) {
			  Long x = i.next();
			  
			  ListIterator<Long> j=temp.listIterator();
			  while (j.hasNext()) {
			    Long y=j.next();
			    compcount++;
			    if (x.longValue()==y.longValue()) {
			    	temp2.add(x);  
			  }
			}
		}
		flag=1;
		return temp2;
		}		
	
	}

	public static ArrayList<Long> TAATAnd(String q) throws IOException
	{	
		ArrayList<Long> docu = new ArrayList<Long>();
		
		if(dict2.containsKey(q))
		{
			term2 t=dict2.get(q);	
			LinkedList<posting2> l=t.getList();
			ListIterator<posting2> x = l.listIterator(0);
			while(x.hasNext())		
			{
				docu.add(x.next().docID);
				
			}
			return docu;
		}

		else
		{
			log.append("\nTerm\t"+q+"\tNot Found");
			return docu;
	}

}
	
	public static HashSet<Long> TAATOr(String q)
	{
		if(dict2.containsKey(q))
		{  
			term2 t=dict2.get(q);	
			LinkedList<posting2> l=t.getList();
			ListIterator<posting2> x = l.listIterator(0);
			while(x.hasNext())		
			{   
				Long y=x.next().docID;
				docu.add(y);
				}	
				
			}
		return docu;
		}
	
	public static ArrayList<Long> DAATA() throws FileNotFoundException 
	{   
		FileReader in=new FileReader(arg);
	    Scanner s=new Scanner(in);
	    int flag=0;
	    int i;
	    ArrayList<ArrayList<Long>> itlist=new ArrayList<ArrayList<Long>>();
	    ArrayList<Integer> inter=new ArrayList<Integer>();
	    ArrayList<Long> DATdoc=new ArrayList<Long>();
	    int index;
	   	Long max;
	   	int fl;
    	int z;
    	int x;
    	int count=0;
	    while(s.hasNext()) 	
	    {
	    	query=s.next();
	    	ArrayList<Long> it=DAATAnd(query);
	    	itlist.add(it);
	    			
	    }
	    s.close();
	    int b=0;
	    int a;
	    int check;
	    while(count!=itlist.size())
	    {	
	    if(flag!=0)
	    { 	
	    
	    	index=inter.get(0);
	    	for(int ch=0;ch<inter.size();ch++)
	    	{   
	    		a=inter.get(ch)+1;
	    		if(a==itlist.get(ch).size())
	    			b++;
	    	}
	    	if(b==itlist.size())
	    	{
	    		break;
	    	}
	    	check=0;
		   	max = itlist.get(0).get(inter.get(0));
		    	for (x = 1; x < itlist.size(); x++) {
		    		compc++;
		    		if (itlist.get(x).get(inter.get(x)).longValue()> max.longValue()) {
		    			max =itlist.get(x).get(inter.get(x));
		    			index=inter.get(x);
		    			check=x;
		    			inter.set(x,index);
		    		}
		    	}
		    	try{
		    	for(int j=0;j<itlist.size();j++)
		    	{
		    		if(j!=check)
		    		{
		    		i=inter.get(j);
		    	
		    		while(itlist.get(j).get(i)!=null)	
		    		{	
		    			compc++;
		    			if(itlist.get(j).get(i)<max)
		    			{	
		    			i++;
		    			
		    			}
		    			else
		    			break;	
		    		}
		    		if(itlist.get(j).get(i)==null)
		    		{
		    			count++;
		    		}
		    		inter.set(j,i);
		    	}
		       }  
	    }
		    	catch(Exception e)
		    	{
		    		break;
		    	}
		    	 fl=0;
		    	 z=0;
		    	for(int j=0;j<itlist.size();j++)
		    	{   int c=inter.get(z++);
		    		compc++;
		    		if(itlist.get(j).get(c).longValue()==max.longValue())
		    		{
		    			fl++;		
		    		}
		    			
		    	}
		    
		    	if(fl==itlist.size())
		    	{
		    		DATdoc.add(max);
		    		for(int k=0;k<inter.size();k++)
		    		{   
		    			int val=inter.get(k);
		    			val=val+1;
		    			if(val<itlist.get(k).size())
		    			inter.set(k,val);
		    		}
		    	}
		    	
		    	flag=1;
	    }
	    else
	    {	
	     index=0;
	   	 max = itlist.get(0).get(0);
	    	for ( x = 1; x < itlist.size(); x++) {
	    		compc++;
	    		if (itlist.get(x).get(0)> max) {
	    			max =itlist.get(x).get(0);
	    			index=x;
	    		}
	    	}
	    	inter.add(0,index);
	    	for(int j=0;j<itlist.size();j++)
	    	{
	    		if(j!=index)
	    		{
	    		i=0;
	    		while(itlist.get(j).get(i)!=null)	
	    		{	
	    			compc++;
	    			if(itlist.get(j).get(i)<max)
	    			i++;
	    			else
	    			break;	
	    		}
	    		if(itlist.get(j).get(i)==null)
	    		{
	    			count++;
	    		}
	    		inter.add(j,i);
	    	}
	       }  
	    	 fl=0;
	    	 z=0;
	    	for(int j=0;j<itlist.size();j++)
	    	{   int c=inter.get(z++);
	    		compc++;
	    		if(itlist.get(j).get(c)==max)
	    			fl++;
	    	}
	    	if(fl==itlist.size())
	    	{
	    		DATdoc.add(max);
	    		for(int k=0;k<inter.size();k++)
	    		{   
	    			int val=inter.get(k);
	    			val=val+1;
	    			inter.set(k,val);
	    		}
	    	}
	    	flag=1;
	    }
	    }
	    return DATdoc;
	}
	
	
	public static ArrayList<Long> DAATAnd(String q)
	{	
		ArrayList<Long> docu = new ArrayList<Long>();
	
		if(dict.containsKey(q))
		{	
		term t=dict.get(q);	
		LinkedList<posting> l=t.getList();
		ListIterator<posting> x = l.listIterator(0);
		while(x.hasNext())		
		{
			docu.add(x.next().docID);
			
		}
		}
		return docu;
	}
	
	
	public static ListIterator<posting> DAATOr(String q)
	{
		ListIterator<posting> x=null;
		if(dict.containsKey(q))
		{
		term t=dict.get(q);	
		LinkedList<posting> l=t.getList();
		 x = l.listIterator(0);
		}
		return x;	
	}
	
	
	public static HashSet<Long> DAATO() throws FileNotFoundException 
	{   
		FileReader in=new FileReader(arg);
	    Scanner s=new Scanner(in);
	   
	    ArrayList<ListIterator<posting>> itlist=new ArrayList<ListIterator<posting>>();
	    HashSet<Long> DATdoc=new HashSet<Long>();
    	int count=0;
	    while(s.hasNext()) 	
	    {
	    	query=s.next();
	    	ListIterator<posting> it=DAATOr(query);
	    	itlist.add(it);
	    			
	    }
	    s.close();
	   
	    while(count!=itlist.size())
	    {	
	    for(int j=0;j<itlist.size();j++)
	    {   
	    	comp++;
	        if(itlist.get(j).hasNext())
	        {
	    	posting x=itlist.get(j).next(); 
	    	if(x!=null)
	    	{
	    		DATdoc.add(x.docID);
	    	}	
	        }
	        else
	    	{	
	    		count++;
	    
	    }
	    }
	    }
	   return DATdoc; 
	   
	}
}	
		
class term extends CSE535Assignment
{
	int a;
	int i=0;
	LinkedList<posting> ll=new LinkedList<posting>(); 
	public term(int n,LinkedList<posting>l)
	{
		a=n;
		ll.addAll(l);
	}
	
	public void displayterm() throws IOException
	{
		log.write("\nOrdered by Doc IDs:");
		for(posting p : ll) {
			p.display();
			if(i!=(ll.size()-1))
			{	
			log.append(",");
			i++;
			}
			}
	}
	
	public LinkedList<posting> getList()
	{
	  return ll;	
	}
   public int getSize()
   {
	   return a;
   }
	
}



class term2 extends CSE535Assignment 
{
	int a;
	LinkedList<posting2> ll=new LinkedList<posting2>(); 
	int i=0;
	public term2(int n,LinkedList<posting2>l)
	{
		a=n;
		ll.addAll(l);
	}
	public void displayterm() throws IOException 
	{
		log.write("\nOrdered by TF:");
		for(posting2 p : ll) {
			p.display();
			if(i!=(ll.size()-1))
			{	
			log.append(",");
			i++;
			}}
			}
	
	public LinkedList<posting2> getList()
	{
	  return ll;	
	}
}


class posting extends CSE535Assignment implements Comparable<posting> 
{  
	int size;	
   long docID;
   int TF;
   
   public posting(long a, int b)
   {
     docID= a; 
     TF = b; 
   }
   @Override
	public int compareTo(posting p) {
		int comparedSize = p.size;
		if (this.size > comparedSize) {
			return 1;
		} else if (this.size == comparedSize) {
			return 0;
		} else {
			return -1;
		}
	}

	
  public void display() throws IOException
  {
	  log.append(String.valueOf(docID)); 
  }
}

class posting2 extends CSE535Assignment implements Comparable<posting2>
{
	int size;	
	   long docID;
	   int TF;
	   
	   public posting2(long a, int b)
	   {
	     docID= a; 
	     TF = b; 
	   }
	   @Override
		public int compareTo(posting2 p) {
			int comparedSize = p.TF;
			if (this.TF > comparedSize) {
				return 1;
			} else if (this.TF == comparedSize) {
				return 0;
			} else {
				return -1;
			}
		}
	  public void display() throws IOException
	  {
		  log.append(String.valueOf(docID));  
	  }
}




