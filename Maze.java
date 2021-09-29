import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Maze {
	
	//// Variables
	static int wid, hgt;
	static int[][] grid;     							 //// representing the maze
	static int[][] count;
	static DisjointSet DisjointMaze;  
	static Edge[][] vertexMaze;
		
	public static int gethgt(int index) {				//// Function to get the height
		for (int i = 0; i < count.length; i++) {
			for (int j = 0; j < count[i].length; j++) 
			{
				if (count[i][j] == index)
					return i;
			}
		}
		return -1;
	}
	
	public static int getwid(int index)                   //// Function to get the width
	{
		for (int i = 0; i < count.length; i++) {
			for (int j = 0; j < count[i].length; j++) 
			{
				if (count[i][j] == index)
					return j;
			}
		}
		return -1;
	}
	
	//// Function for maze creation
	public static void createMaze() {
		boolean flag = false;
		while (!flag) 
		{
			int var = (int) (Math.random() * 1000) % (hgt * wid);		/// generating the variable	
			int h1 = gethgt(var);                                      
			int w1 = getwid(var);
			
			while (adjVal(var) < 0)
				{
					var = (int) (Math.random() * 1000) % (hgt * wid);    
				}
			
			int var2 = adjVal(var);   

			int h2 = gethgt(var2);
			int w2 = getwid(var2);
			
			int h3 = gethgt(Math.min(var, var2));
			int w3 = getwid(Math.min(var, var2));
			
			
			if (!(DisjointMaze.find(var) == DisjointMaze.find(var2))) 
				{
					DisjointMaze.union(DisjointMaze.find(var), DisjointMaze.find(var2));
					if (h1 == h2)
					 {
						if (grid[h1][w3] == 3)
							grid[h1][w3] = 2;
						else
							grid[h1][w3] = 0;
					 }
					
					else if (w1 == w2)
					
						{
							if (grid[h3][w1] == 3)
							grid[h3][w1] = 1;
							
						    else
							grid[h3][w1] = 0;
				         }
			 }
			for (int i = 0; i < hgt * wid; i++)
				{
					if (DisjointMaze.set[i] == -1 * (hgt * wid))
					flag = true;	
				}
			}
	}

	public static int adjVal(int var) {
		int w1 = getwid(var);
		int h1 = gethgt(var);
		int getcount = 0;
		int[] temp = new int[4];
		for (int i = 0; i < temp.length; i++)
			temp[i] = -1;
		
		if (h1 != 0) {
			if (grid[h1 - 1][w1] != 0 || grid[h1 - 1][w1] != 1)
				{
					temp[getcount] = count[h1 - 1][w1];
					getcount++;

				}
		}
		
		if (h1 != count.length - 1)
			{
				if (grid[h1][w1] != 0 || grid[h1][w1] != 1)
					{
					temp[getcount] = count[h1 + 1][w1];
					getcount++;
					}
			}
		
		if (w1 != 0)
		{
			if (grid[h1][w1 - 1] != 0 || grid[h1][w1] != 2) {
				temp[getcount] = count[h1][w1 - 1];
				getcount++;
			}
		}
		if (w1 != count[0].length - 1) 
			{
				if (grid[h1][w1] != 0 || grid[h1][w1] != 2) 
					{
						temp[getcount] = count[h1][w1 + 1];
						getcount++;
					}
			}
		
		if (temp[0] < 0 && temp[1] < 0 && temp[2] < 0 && temp[3] < 0)
			return -1;
		
		int temp2 = (int) (Math.random() * 10) % getcount;
		while (temp[temp2] < 0) 
			{
				temp2 = (int) (Math.random() * 10) % getcount;
			}
		return temp[temp2];
	}
	
static class CreateMaze extends JPanel {    ///extending to jPanel to organize components, various layouts 
		private int[][] arr;
		private int maze_size;

		public CreateMaze(int[][] array, int grid_size) {      //// calling constructor
			arr = array;
			this.maze_size = grid_size;
			setPreferredSize(new Dimension(500, 500));
		}

		public void paintComponent(Graphics graphics) {      //// MODIFYING THE PATH LINES
			int start_value = 5;
			graphics.setColor(Color.RED);
			int current = start_value;
			for (int i = 0; i < arr.length; i++)
			{
				int current_value = start_value;
				for (int j = 0; j < arr[i].length; j++)
				{
					current_value += maze_size;
					if ((arr[i][j] == 1 || arr[i][j] == 3) && j != arr[i].length - 1)
						graphics.drawLine(current_value, current, current_value, current + maze_size);
				}
				current += maze_size;
			}
			
			//// To create path lines {void java.awt.Graphics.drawLine(int x1, int y1, int x2, int y2)}
			graphics.drawLine(start_value + maze_size, start_value, maze_size * arr[0].length + 5, start_value);
			graphics.drawLine(start_value, maze_size * arr.length + 5, maze_size * arr[0].length + 5, maze_size * arr.length + 5);
			graphics.drawLine(start_value, start_value, start_value, maze_size * arr.length + 5);
			graphics.drawLine(maze_size * arr[0].length + 5, start_value, maze_size * arr[0].length + 5, maze_size * arr.length + 5 - maze_size);
			current = start_value;
			
			for (int i = 0; i < arr.length; i++) 
			{
				int current_value = start_value;
				current += maze_size;
				for (int j = 0; j < arr[0].length; j++)
				{
					if ((arr[i][j] == 2) || (arr[i][j] == 3))
						graphics.drawLine(current_value, current, current_value + maze_size, current);
					current_value += maze_size;
				}

			}
		}

	}
	
	////  Main function
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);                             //// Dimensions 
		System.out.print("Please enter a height: ");
		hgt = s.nextInt();
		System.out.print("Please enter a width: ");
		wid = s.nextInt();
		
		grid = new int[hgt][wid];
		count = new int[hgt][wid];
		
		int temp = 0;
		s.close();


		for (int i = 0; i < hgt; i++) {
			for (int j = 0; j < wid; j++) {
				count[i][j] = temp;
		
				grid[i][j] = 3;
				temp++;
			}
		}
		DisjointMaze = new DisjointSet(hgt * wid);
		//// Create the maze
		createMaze();
         
		
		vertexMaze = new Edge[hgt][wid];
		for (int i = 0; i < hgt; i++) {
				for (int j = 0; j < wid; j++) {
					vertexMaze[i][j] = new Edge();
					vertexMaze[i][j].i = count[i][j];
				}
			}
			for (int i = 0; i < hgt; i++) {
				for (int j = 0; j < wid; j++) {
					if (i != 0) {
						if (grid[i - 1][j] == 0 || grid[i - 1][j] == 1) {
							vertexMaze[i][j].create(vertexMaze[i - 1][j]);
						}
					}
					if (j != 0) {
						if (grid[i][j - 1] == 0 || grid[i][j - 1] == 2) {
							vertexMaze[i][j].create(vertexMaze[i][j - 1]);
						}
					}
					if (i != (hgt - 1)) {
						if (grid[i][j] == 0 || grid[i][j] == 1) {
							vertexMaze[i][j].create(vertexMaze[i + 1][j]);
						}
					}
					if (j != (wid - 1)) {
						if (grid[i][j] == 0 || grid[i][j] == 2) {
							vertexMaze[i][j].create(vertexMaze[i][j + 1]);
						}
					}
				}
			}
		
		//// Modifying the maze using jpanel and jframe   
			JFrame jf = new JFrame();     
			jf.setTitle("Solve the Maze");                                  //// Title
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              //// Close operation
			jf.getContentPane().add(new CreateMaze(grid, 20));              //// Maze creation
			jf.pack();
			jf.setBackground(Color.YELLOW);                                 //// Background color
			jf.setLocationRelativeTo(null);                                 //// Relative location
			jf.setVisible(true);

	}
}

class Edge {
	public Double distance;
	public boolean visited;
	public int i;
	public LinkedList<Edge> llsit;
	
	public Edge() {
		llsit = new LinkedList();             
		distance = Double.POSITIVE_INFINITY;   ///////  A constant holding the positive infinity of type double
		visited = false;
	}

	public void create(Edge a) {              /////  creating adjacent
		llsit.add(a);                         ///// adding each adjacent path
	}
}