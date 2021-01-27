/**
 *The MoneyWorld Class is responsible for checking the adjacent money values in a grid
 *and changing them to a '*' when the box is clicked.
 *@author: Rishi Villa
 *Collaborators : None
 *Teacher : Mrs. Ishman
 *Period : 3rd Period 
 *Due date : 02-12-2020
 */

import info.gridworld.grid.*;
import info.gridworld.world.World;

import java.util.*;

public class MoneyWorld extends World<String>
{
	/** Message for help area */
	private static final String HELP_MSG
		= "Click on grid cell to pick-up money; STEP to reset world";
	private static final int ROW_CAP = 10;
	private static final int COL_CAP = 10;
	private static final double[] CHANCES = {.25, .5, .75, .85, .95};
	private static final List<String> SWITCH_KEYS = Arrays.asList("+", "-");
	private static final String MARK_VISIT = "*";
	private static final List<String> MONEY = Arrays.asList("5", "10", "20", "50");
	private static final int[][] PARAMETERS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	private static int updatedAmount = 0;

	/** grid to cleanup */
	private Grid<String> grid;

	/**
	 * Create a world of letters of size rows x cols
	 * @param rows number of rows in world
	 * @param cols number of cols in world
	 */
	public MoneyWorld(int rows, int cols)
	{
		super(new BoundedGrid<String>(rows, cols));
		grid = getGrid();
		fillWorld();
		setMessage(HELP_MSG);
	}

	/**
	 *  Refill world when the step button is pressed
	 */
	@Override
	public void step()
	{
		fillWorld();
	}


	/**
	 * Suppresses menu from appearing when any Location is clicked
	 * @param loc the grid location that the user selected
	 * @return true to indicate click was pro
	 */
	@Override
	public boolean locationClicked(Location loc)
	{
		updatedAmount += pickupMoney(loc);
		this.setMessage("Total amount picked up: $" + updatedAmount +
				"\nClick on grid cell to pick-up money, STEP to reset world");
		return true;
	}
   
    //fills grid after checking position
	private void fillWorld()
	{
		grid = getGrid();
		for (int i = 0; i < ROW_CAP; i++)
			for (int j = 0; j < COL_CAP; j++)
			{
				Location intialPos = new Location(i, j);
				double key = Math.random();
				if (key <= CHANCES[0])
					grid.put(intialPos, SWITCH_KEYS.get(0));
				else if (key <= CHANCES[1])
					grid.put(intialPos, SWITCH_KEYS.get(1));
				else if (key <= CHANCES[2])
					grid.put(intialPos, MONEY.get(0));
				else if (key > CHANCES[2] && key <= CHANCES[3])
					grid.put(intialPos, MONEY.get(1));
				else if (key > CHANCES[3] && key <= CHANCES[4])
					grid.put(intialPos, MONEY.get(2));
				else if (key > CHANCES[4])
					grid.put(intialPos, MONEY.get(3));
			}
	}
    
    //to update total money
	private int pickupMoney(Location loc)
	{
		int sum = 0;
		if(grid.get(loc).matches("\\d+"))
		{
			sum += Integer.parseInt(grid.get(loc));
			grid.put(loc, MARK_VISIT);
			for(Location adjacent : getNeighbors(loc))
				sum += pickupMoney(adjacent);
		}
		return sum;
	}
    
    //for reference position
	private ArrayList<Location> getNeighbors(Location loc)
	{
		ArrayList<Location> moneyGrid = new ArrayList<>();
		for(int[] read: PARAMETERS)
	    {
			Location changePos = new Location(loc.getRow() + read[0],
					loc.getCol() + read[1]);
			if (!valid(changePos))
				continue;
			if (grid.get(changePos).matches("\\d+"))
				moneyGrid.add(changePos);
		}
		return moneyGrid;
	}

	private boolean valid(Location nextPos)
	{
		return nextPos.getCol() >= 0 && nextPos.getCol() < COL_CAP
	   &&  nextPos.getRow() >= 0 && nextPos.getRow() < ROW_CAP;
	}
	

	/**
	 * Run the money world
	 */
	public static void main(String[] args)
	{
		MoneyWorld myWorld = new MoneyWorld(ROW_CAP, COL_CAP);
		myWorld.show();
	}
}


