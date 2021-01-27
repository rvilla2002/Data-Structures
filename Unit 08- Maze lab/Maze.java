/**
* @author: Rishi Villa
* Collaborators: None
* Teacher Name: Mrs. Ishman
* Period: 3
* Due Date: 1/16/2019
*/

import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.*;

public class Maze extends AbstractMaze
{
   private static final Color MAZE_BACK_COL = Color.BLUE;
   private static final Color MAZE_WALL_COL = Color.DARK_GRAY;
   private static final Color MAZE_PATH = Color.CYAN;
   private static final Color INITIAL_COL = Color.GREEN;
   private static final Color COL_TOUCHED = Color.ORANGE;
   private static final Color LAST_COL = Color.RED;
   private static final double CHANCES = .8;
   private static final int ROWS = 19;
   private static final int COLS = 19;

   private Location initialPos;
   private Location finalPos;
   
   //Constructor not being used
   public Maze()
   {
   	
   }

   @Override
   public boolean solveMaze()
   {
       Grid<Color> grid = getGrid();
       Deque<Location> stack = new LinkedList<>();
       stack.push(initialPos);
       ArrayList<Location> completed = new ArrayList<>();
       Location immediateLocation = new Location(0, 0);
       while (!stack.isEmpty() && !immediateLocation.equals(finalPos))
       {
           Location point = stack.pop();
           if(!completed.contains(point))
           {
               completed.add(point);
               if(grid.get(point) != INITIAL_COL)
                   grid.put(point, COL_TOUCHED);
               ArrayList<Location> nearSpots = new ArrayList<>();
               for(int r = -1; r < 2; r++)
                   for (int c = -1; c < 2; c++) {
                       try {
                           Location newPos = new Location(point.getRow() + r,
                                   point.getCol() + c);
                           if (grid.get(newPos) == MAZE_BACK_COL && !completed.contains(newPos))
                               nearSpots.add(newPos);
                           if (grid.get(newPos) == LAST_COL)
                               return true;
                       }
                       catch (Exception ignored){}
                   }
               for(Location pos : nearSpots)
               	 {
                   stack.push(pos);
               	 }
               
           }
       }
       return false;
   }


   @Override
   public void populateMaze()
   {
       Grid<Color> grid = getGrid();
       for (int i = 0; i < ROWS; i++)
       {
           for (int j = 0; j < COLS; j++)
               grid.put(new Location(i, j), MAZE_BACK_COL);
       }
     
       for (int i = 0; i < ROWS; i++)
       {
           Location loc = new Location(i, COLS - 1);
           grid.put(loc, MAZE_WALL_COL);
           loc = new Location(i, 0);
           grid.put(loc, MAZE_WALL_COL);
       }
       
       for (int i = 0; i < COLS; i++)
       {
           Location loc = new Location(ROWS - 1, i);
           grid.put(loc, MAZE_WALL_COL);
           loc = new Location(0, i);
           grid.put(loc, MAZE_WALL_COL);
       }

       for (int i = 0; i < ROWS; i++) {
           for (int j = 0; j < COLS; j++) {
               Location loc = new Location(i, j);
               if (Math.random() > CHANCES)
                   grid.put(loc, MAZE_WALL_COL);
           }
       }


       initialPos = new Location((int) (Math.random() * COLS), 0);
       finalPos = new Location((int) (Math.random() * COLS), COLS - 1);

       grid.put(initialPos, INITIAL_COL);
       grid.put(finalPos, LAST_COL);

   }

}







