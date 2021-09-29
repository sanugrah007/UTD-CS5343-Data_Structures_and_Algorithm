import java.util.*;
import java.io.*;

public class Kruskals
{
	PriorityQueue<Compare> queue = new PriorityQueue<>();
	BufferedReader fr = null; 										//// Reads text from a character-input stream
	ArrayList<Compare> Clist = new ArrayList<>();					//// Storing edge of the minimum spanning tree
	ArrayList<String> List = new ArrayList<>();						
	
	public static void main(String args[])											//// Main function
	{
		Kruskals k= new Kruskals();
	    System.out.println("Spanning Tree of the Cities of Dallas\n");	    
	    k.func();  
	}
	
	public void func()                                             //// Function for determining Spanning Tree
	{ 	
		int dis = 0;
		int count = 0 ;
    	try
        {
            String s = null;
            fr = new BufferedReader(new FileReader("assn9_data.csv")); //// Read the file
           
            while ((s = fr.readLine()) != null)
            {
                
                String[] additem = s.split(",");

                List.add(additem[0]);                              //// Add items
                
               
                Clist.add( new Compare(additem[0],additem[1],Integer.parseInt(additem[2])));
                	for (int a=3 ; a<additem.length ; a++)
                	{
                		Clist.add(new Compare(additem[0],additem[a],Integer.parseInt(additem[a+1])));
                		a++ ;
                	}            
            }
        }
        
        catch (Exception e) {                                  //// Catch exception
            e.printStackTrace();
        }	 
        
		DisjointSet set = new DisjointSet(List.size());        //// Using DisJoint Data structure
		
		
		for (Compare x : Clist)
		{
			queue.add(x);	                                  //// Using priority queue for adding
		}
		
		while (count < List.size()-1 )                        
		{
			Compare	e = queue.poll();	
         
         			
			int a=set.find(List.indexOf(new String(e.s1)));				//// Store the index of string
			int b=set.find(List.indexOf(new String(e.s2)));
			if (a!= b)
			{
				count++ ;
				set.union(a,b); 										//// Union two disjoint sets using the height heuristic 
				System.out.println(e.s1 + " ----> " + e.s2 );  
				
				dis = dis + e.dis ;										//// Calculating distance between two stored integers value
				System.out.println("Distance = " + e.dis);              
				System.out.println();
			}		
		}      
	  System.out.println("Sum of all of the distances = " + dis);      //// Calculating total distance
	}
	
	public class Compare implements Comparable<Compare>   //// Class to introduce comparable
	{
		int dis;
		String s1, s2;
		
		Compare(String s1 , String s2,int dis)            ////  Pointing the variables
		{
			this.s1 = s1;
			this.s2 = s2;
			this.dis = dis;
		}
		public int compareTo(Compare C)                    //// To compare the distance
		{
			if (this.dis > C.dis)
				return 1;
			else if (this.dis < C.dis)
				return -1;			
			else 
				return 0;
		}
	}	
}