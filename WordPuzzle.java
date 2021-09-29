import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class WordPuzzle
{
	
	
	
	   //// My initial method for traveling in all direction in a single go (for normal algorithm)
	/*   for (int i = 1; d < 9; d++) { 

        int j, r1 = row + x[d], c2= col + y[d]; 

        // First character is already checked, 
        // match remaining characters 
        for (j = 1; j< len; j++) { 
           
            if (r1 >= R || r1 < 0 || c2 >= C || c2 < 0)  ///for not in range 
                break; 

            
            if (grid[r1][c1] != word.charAt(j))    ////word not found
                break; 


            r1 += x[d]; 
            c2 += y[d]; 
        } 


        if (j == len) 
            s.append(grid2[r][c]); 
    } 
    */
	public static void main(String args[])
	{
		  ArrayList<String> count = new ArrayList<>();
		  
		  
		Scanner sc = new Scanner(System.in);
		MyHashTable<String> HT = new MyHashTable<>( );
		WordPuzzle w = new WordPuzzle();
		
		System.out.print("Enter number of rows: ");
		int row = sc.nextInt();
		System.out.print("Enter number of columns: ");
		int col = sc.nextInt();
		System.out.print("Enter 0 for normal, 1 for enhanced algorithm: ");
		int e = sc.nextInt();
		
		if(row<1 )                             ////Rows columns should be more than 0
		{
			System.out.println("Enter row greater than 0!!!");
			sc.close();
		}
		else if(col<1 )
		{
			System.out.println("Enter column greater than 0!!!");
			sc.close();
		}
		else if (e>2||e<0)			
		{
			System.out.println("Only value = 0 or 1 are allowed....");
			sc.close();
				
		}
		else {
			char[][] grid = new char[row][col];           ////initializing grid
			

			
			Random rand = new Random();                        ////random grid
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					grid[i][j] = (char) ('a' + rand.nextInt(26));
				}
			}
				  
		   System.out.println("\n");
		   System.out.println("The grid is: ");
		   for (int i=0; i<grid.length; i++)
		   {
		      for (int j=0; j<grid[i].length; j++)
		         System.out.print(grid[i][j] + " ");
		      System.out.println();
		   }
		
		   w.loadDict(HT,e);  ////load Dictionary
	       
		   long startTime = System.currentTimeMillis( );      /////calculate time duration
		   

		   w.VerticalTB(HT, count, grid,e);
		   w.VerticalBT(HT, count, grid,e);
		   w.HorizontalLR(HT, count, grid,e);
		   w.HorizontalRL(HT, count, grid,e);
		   w.DiagonalNE(HT, count, grid,e);
		   w.DiagonalSW(HT,count, grid,e);
		   w.DiagonalNW(HT, count, grid,e);
		   w.DiagonalSE(HT, count, grid,e);
		   
		   
		   long endTime = System.currentTimeMillis( );
		   
		   long o=endTime-startTime;

		   System.out.println("Words found: ");
		   for(String s: count)
		   {
			   System.out.println(s);
		   }
		   System.out.println("\n");

		   System.out.println("Total words count = "+count.size());
		   
		   System.out.println("Total time elasped = "+o+" ms");
	   
		}
	}
	
	public void VerticalTB(MyHashTable<String> H,ArrayList<String> results, char grid[][],int e )
	{
		StringBuilder s = new StringBuilder();	     /////to append more data
		
		for (int i=0; i<grid[0].length; i++)           ////running in the grid
		{
			   
	      for (int j=0; j<grid.length; j++)
	      {
				s.setLength(0);                       /////set length = 0
		      
		        
		        for( int k=0; k + j < grid.length; k++ ) 
		        {
		        	s.append(grid[k+j][i]);
		        	if(s.length() <2)
	        		{
		        		continue;
		        	}
		        	
		        	int a = H.contains(s.toString());
		        	
		        	if (a== 1)         ////for normal algorithm
		        	{
		        		results.add(new String(s));
		        	}
		        	else if (a==0  && e==1)    /////for enhanced algorithm
		            {
		        	
		        		s.setLength(0);
		        		break;
		            }
		        	else
		        	{
		        		
		        	}}}}}
	
	public void VerticalBT(MyHashTable<String> H,ArrayList<String> results, char grid[][],int e)
	{
		StringBuilder s = new StringBuilder();
		
		for (int i=0; i<grid[0].length; i++)
		{
			
	      for (int j=0; j<grid.length; j++)
	      {
				s.setLength(0);
		      
		        
		        for( int k=grid.length-1; (k - j) >= 0; k-- )    /////same but reversed
		        {
		        	s.append(grid[k-j][i]);
		        	if(s.length() <2)
	        		{
		        		continue;
		        	}
		        	
		        	int a = H.contains(s.toString());
		        	
		        	if (a == 1)
		        	{
		        		results.add(new String(s));
		        	}
		        	else if (a==0  && e==1)
		            {
		        		s.setLength(0);
		        		break;
		            }
		        	else
		        	{
		        		
		        	}}}}}
	    	
	public void HorizontalLR(MyHashTable<String> H,ArrayList<String> results, char grid[][], int e )
	{
		StringBuilder s = new StringBuilder();	
  	  	
		for (int i=0; i<grid.length; i++)
		{
			   
	      for (int j=0; j<grid[i].length; j++)    /////to check horizontally
	      {
			s.setLength(0);
	      
	        for( int k=0; k + j < grid[i].length; k++ )      ////to append
	        {
        	s.append(grid[i][k+j]);
        	int a = H.contains(s.toString());
        	
        	if (a == 1)                
        	{
        		results.add(new String(s));
        		
        	}
        	else if (a==0  && e==1)
            {
        		s.setLength(0);
        		break;
        
            }
        	else
        	{
        		
        	}}}}}

	
	public void HorizontalRL(MyHashTable<String> H, ArrayList<String> results, char grid[][],int e)
	{
		StringBuilder s = new StringBuilder();	
		int a=grid.length;
  	  	
		for (int i=0; i<a; i++)
		{
			   
	      for (int j=grid[i].length-1; j>=0; j--)        /////reversed
	      {
			s.setLength(0);
	      
	        for( int k=j; k >=0; k-- ) 
	        {
	        	s.append(grid[i][k]);
        	
        		if(s.length() <2)
        		{
        			continue;
        		}
        	
        		int a1 = H.contains(s.toString());
            	
            	if (a1 == 1)
            	{
            		results.add(new String(s));
            	
            	}
            	else if (a1==0  && e==1)
                {
            		s.setLength(0);
            		break;
            		
                }
            	else
            	{
            		
            	}}}}}

	public void DiagonalNE(MyHashTable<String> H,ArrayList<String> results, char grid[][],int e)
	{
		StringBuilder s = new StringBuilder();
		for(int i=0; i<grid.length; i++)
		   {
			   for(int j=0; j<grid[0].length; j++)
			   {
				   int q = i;
				   s.setLength(0);
				   for(int k=j; k<grid[0].length; k++)              ////to move diagonally
				   {
					   s.append(grid[q][k]);
					   if(s.length() <2)
		        		{	
						   q--;
						   if (q<0)
						   	{
							   break;             ///not there
							}
						   continue;
						   }
					   
					   int a = H.contains(s.toString());
			        	
			        	if (a == 1)
			        	{
			           		results.add(new String(s));
			        		
			        	}
			        	else if (a==0  && e==1)
			            {
			        		s.setLength(0);
			        		break;
			            }
			        	else
			        	{
			        		
			        	}
					   q--;
					   if(q<0)
					   {
						   break;
					}}}}}
	public void DiagonalSW(MyHashTable<String> H,ArrayList<String> results, char grid[][],int e)
	{
		StringBuilder s = new StringBuilder();
		for(int i=0; i<grid.length; i++)
		   {
			   for(int j=0; j<grid[0].length; j++)
			   {
				   int q= i;
				   s.setLength(0);
				   for(int k=j; k>=0; k--)
				   {
					   s.append(grid[q][k]);
					   if(s.length() <2)
		        		{q++;
						   if(q>grid.length-1)
						   {
							   break;
						   }
						   continue;
						   }
					   int a = H.contains(s.toString());
			        	
			        	if (a == 1)
			        	{
			        		results.add(new String(s));
			        		
			        	}
			        	else if (a==0  && e==1)
			            {
			        		s.setLength(0);
			        		break;
			            }
			        	else
			        	{
			        		
			        	}
					   q++;
					   if(q>grid.length-1)
					   {
						   break;
					   }
				   }}}}		   
	
	   public void DiagonalSE(MyHashTable<String> H,ArrayList<String> results, char grid[][],int e)
		{
			StringBuilder s = new StringBuilder();
	   
			for(int i=0; i<grid.length; i++)
			   {
				   for(int j=0; j<grid[0].length; j++)
				   {
					   int q = i;
					   s.setLength(0);
					   for(int k=j; k<grid[0].length; k++)
					   {
						   s.append(grid[q][k]);
						   if(s.length() <2)
			        		{
							   q++;
							   if(q>grid.length-1)
							   {
								   break;
							    }
							   continue;
							   }
						   
						   int a = H.contains(s.toString());
				        	
				        	if (a == 1)
				        	{
				        		results.add(new String(s));
				        						        		
				        	}
				        	else if (a==0  && e==1)
				            {
				        		s.setLength(0);
				        		break;				        		
				            }
				        	else
				        	{
				        		
				        	}
						   q++;
						   if(q>grid.length-1)
						   {
							   break;
							}}}}}
		

	   public void DiagonalNW(MyHashTable<String> H,ArrayList<String> results, char grid[][],int e)
		{
			StringBuilder s = new StringBuilder();
	   
			for(int i=0; i<grid.length; i++)
			   {
				   for(int j=0; j<grid[0].length; j++)
				   {
					   int q = i;
					   s.setLength(0);
					   for(int k=j; k>=0; k--)
					   {
						   s.append(grid[q][k]);
						   if(s.length() <2)
			        		{q--;
							   if(q<0)
							   {
								   break;
								}
							   continue;
							   }
						   
						   int a= H.contains(s.toString());
				        	
				        	if (a == 1)
				        	{
				        		results.add(new String(s));
				        		
				        	}
				        	else if (a==0  && e==1)
				            {
				        		s.setLength(0);
				        		break;
				            }
				        	else
				        	{
				        		
				        	}
						   q--;
						   if(q<0)
						   {
							   break;
						}}}}}				 					   
				   
	   public void loadDict(MyHashTable<String> H,int e)              ////opening dictionary
		{
		    Scanner sc=null;
			try {
					sc = new Scanner(new File("dictionary.txt"));     ///NEED TO BE LOAD PERFECTLY IN THE GIVEN PATH
				} 
			catch (FileNotFoundException o) {
				
				System.out.println("File not found!!!");             ///Incase File not found
			}
		     
		     
		     while(sc.hasNext()){
		    	 String w = sc.nextLine();
		    	 if(e == 0)                                        // normal algorithm
		    	 { 
		    		 H.insert(w,true);

		    		 
		    	 }
		    	 else                                       ////enhanced algorithm
		    	 {
			    		StringBuilder sb = new StringBuilder();
						for(int i = 0; i < w.length(); i++) 
						{
							sb.append(w.charAt(i));            /////append method not in String (thats why STRINGBUILDER)
							
							if(i == w.length() - 1) 
							{
								H.insert(sb.toString(), true);
								
							}
							else 
							{								
								H.insert(sb.toString(), false);
							}}}}
		     sc.close();

				}}
	
