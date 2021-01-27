 /**
  * The RadixSort Lab is responsible for sorting non negative numbers into an array 
  *and making the required number of passes SIZE_OF_QUEUEd on the max number of digits.
  *@author: Rishi Villa
  *Collaborators : None
  *Teacher Name  : Mrs. Ishman
  *Period : 3rd period
  *Due Date : 1-28-2020
  */
import java.util.*;

public class RadixSort 
{
	private static final int SIZE_OF_QUEUE = 10;

	/** Sorts the given array using the Radix Sort
	 *  @param nums the array of integers to be sorted
	 *      Precondition: nums.length > 0; all values in nums are non-negative
	 */
	public static void sort(int[] nums) 
	{
		int numPasses = getMaxDigits(nums);
		
		for (int i = 0; i < numPasses; i++) 
		{
			List<Queue<Integer>> queues = itemsToQueues(nums, i);
			queuesToArray(queues, nums);
		}
	}
	
	private static int getMaxDigits(int[] numbers)
	{
		int maxDigits = 0;
		for(int num: numbers)
		{
			int cap = Integer.toString(num).length();
			if(cap > maxDigits)
				maxDigits = cap;
		}
		return maxDigits;
	}
	
	private static int getDigit(int number, int k) 
	{
		return (number % (int) Math.pow(SIZE_OF_QUEUE, k + 1)) / (int) Math.pow(SIZE_OF_QUEUE, k);
	}
	

	private static List<Queue<Integer>> itemsToQueues(int[] nums, int k)
	{
   		List<Queue<Integer>> sortElements = new ArrayList<>();
  	    for(int j = 0; j < SIZE_OF_QUEUE; j++)
  	    {
  	    	sortElements.add(new LinkedList<>());
  	    }

        for (int j = 0; j < SIZE_OF_QUEUE; j++)
        {
        	sortElements.set(j, new LinkedList<Integer>());
        }
        for(int check: nums)
  		 {
       	 	int listkey = getDigit(check, k);
       	 	sortElements.get(listkey).offer(check);
  		 }
  		 return sortElements;
	}

	private static void queuesToArray(List<Queue<Integer>> queues, int[] nums)
	{
   	int track = 0;
   	for(int i = 0; i < queues.size(); i++)
   	{
   		while (queues.get(i).size() > 0)
        {
           nums[track] = queues.get(i).remove();
           track++;
        }
   	}
	
	}	

}


