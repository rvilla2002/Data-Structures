/**
 *The GridConnector class is responsible for reading an input file 
 *provided by the user and searching for a block of characters using 
 *recursive methods.
 *@author : Rishi Villa
 *Collaborators : None
 *Teacher : Mrs. Ishman
 *Period : 3
 *Due date : 2-18-2020
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.awt.*;

public class GridConnector
{
	private static final int[][] COORD  = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

	/** Dimensions of grid */
	private int numRows;
	private int numCols;

	/** Grid of values */
	private String[][] grid;
	private boolean[][] marked;

	/** Set of all values in grid */
	private Set<String> allValues;


	public GridConnector(String inputFile)
	{
		loadFile(inputFile);
	}

	public void changeGrid(String newFile)
	{
		loadFile(newFile);
	}

	public int getLargestBlock(String value)
	{
		int upCeil = 0;
		for (int k = 0; k < numRows; k++) {
			for (int j = 0; j < numCols; j++) {
				if (!marked[k][j] && grid[k][j].equals(value)) {
					int calcRoot = searchDepth(value, k, j);
					if (calcRoot > upCeil)
						upCeil = calcRoot;
				}
			}
		}
		marked = new boolean[numRows][numCols];
		return upCeil;
	}

	/** Retrieve set of all values in grid
	 *  @return set of values in grid
	 */
	public String getAllValues()
	{
		if (allValues == null)
			return "";

		return allValues.toString();
	}

	/** Retrieve grid as a string
	 *  @return grid as a string
	 */
	@Override
	public String toString()
	{
		if (grid == null)
			return "";

		StringBuilder str = new StringBuilder();
		for (String[] row : grid)
		{
			for (String ch : row)
				str.append(ch).append(" ");
			str.append("\n");
		}
		return str.toString();
	}
    //instantiate into tree set to keep 
	private void loadFile(String inputFile)
	{
		try
		{
			Scanner in = new Scanner(new File(inputFile));
			numRows = in.nextInt();
			numCols = in.nextInt();
			grid = new String[numRows][numCols];
			marked = new boolean[numRows][numCols];
			allValues = new TreeSet<String>();
			for(int i = 0; i < numRows; i++)
				for(int j = 0; j < numCols; j++) {
					String val = in.next();
					grid[i][j] = val;
					allValues.add(val);
				}
		}
		catch(IOException err)
		{
			System.out.println("File not found");
		}
    }
    
   private int searchDepth(String key, int x, int y)
	{
		if((x >= numRows || x < 0 || y >= numCols || y < 0) || !grid[x][y].equals(key)
		 || marked[x][y])
		 {
		 	return 0;
		 }
		marked[x][y] = true;
		int commonBl = 1;
		for (int[] check : COORD)
			commonBl += searchDepth(key, x + check[0], y + check[1]);
		return commonBl;
	}
}


