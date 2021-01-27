/** 
 * The MagicSquare class is a program that creates a matrix filled with numbers
 *such that the sum of the rows, columns and the two diagonals are the same value. 
 *It achieves this task by implementing several methods that check the values in the matrix
 *and return the appropriate data.
 * @author : Rishi Villa
 * Collaborators:None
 * Teacher Name: Mrs.Ishman
 * Period:3
 * Due Date:08-20-19
 */
 
public class MagicSquare {
 public static final String MAGIC_SUCCESS = "successful";
 public static final String FAILED_NOT_ALL_VALUES = "not all values used";
 public static final String FAILED_ROW_SUM = "failed row sum";
 public static final String FAILED_COL_SUM = "failed column sum";
 public static final String FAILED_DIAG_SUM = "failed diagonal sum";
 private int[][] nums;
 private int keepTrack;
 private int[] frequencyTable;
 
 //Constructor that instantiates all the instance variables
 public MagicSquare(int sideLength)
 {
   nums = new int[sideLength][sideLength];
   keepTrack = 0;
   frequencyTable = new int[sideLength * sideLength];
 }
 
 //fills the 2d array with values that are inputed from the keyboard
 //fills the frequencyTable 2D array and appropriatley increments the counter.
 public void add(int number)
 {
   nums[keepTrack/nums.length][keepTrack%nums.length] = number;
   keepTrack++;
   if(number <= nums.length*nums.length && number > 0)
   {
     frequencyTable[number-1]++;
   }
 
 }
 
 //Method that calls all the helper methods to check if the sums are equal
 //returns the appropriate error statement or success statement
 public String isMagic()
 {
   int sumChecker = calcSum(nums[0]);
     for(int i=0;i<frequencyTable.length;i++)
       if(frequencyTable[i]!=1)
         return FAILED_NOT_ALL_VALUES;
     for (int i = 1; i < nums.length; i++)
       if (calcSum(nums[i]) != sumChecker)
         return FAILED_ROW_SUM;
     for(int i = 0; i < nums[0].length; i++)
       if (calcCol(nums, i) != sumChecker)
         return FAILED_COL_SUM;
     if(calcDiagonal(nums) != sumChecker)
       return FAILED_DIAG_SUM;
     if(calcDiagonal1(nums) != sumChecker)
       return FAILED_DIAG_SUM;
 
     return MAGIC_SUCCESS;
 }
 // This method formats the values that are inputed into the matrix
 //and directs when to move to the next line

 @Override
 public String toString() {
   String f = "";
   for (int i = 0; i < nums.length; i++)
     for (int j = 0; j < nums[i].length; j++)
     {
       if(j == nums.length-1)
         {
           f += String.format("%6d\n", nums[i][j]);
         }
       else
        f += String.format("%6d", nums[i][j]);
      
     }
   return f;
 }
 
 public static int[][] makeMagic(int sideLength) {
   return null;
 }
 //Calculates the sum for the row
 private int calcSum(int[] list)
   {
       int result = 0;
       for(int num:list)
           result+=num;
       return result;
   }
   //Calculates the sum for the column
   private int calcCol(int[][] list1, int col)
   {
       int result = 0;
       for(int[] arr:list1)
           result+=arr[col];
       return result;
   }
   //Calculates the sum for the diagonal moving from top left to bottom right
   private int calcDiagonal(int[][] list1)
   {
       int sum=0;
       for(int i=0;i<list1.length;i++)
           for(int j=0;j<list1[i].length;j++)
               if(i==j)
                   sum+=list1[i][j];
   return sum;
 }
 //Calculates the sum for the diagonal moving from top right to bottom left
   private int calcDiagonal1(int[][] list1)
   {
       int sum=0;
       for(int i=0;i<list1.length;i++)
           for(int j=0;j<list1[i].length;j++)
               if(list1.length-i-1==j)
                   sum+=list1[i][j];
   return sum;
 }   
 
}
 


