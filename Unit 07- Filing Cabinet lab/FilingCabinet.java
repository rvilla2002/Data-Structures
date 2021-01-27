/*
 *The FilingCabinetJava class is responsible for taking the student objects and matching it to the 
 *given drawer in the filing cabinet and perform other operations like removing the appropriate file.
 *@author: Rishi Villa
 *Collaborators: None
 *Teacher name : Mrs.Ishman
 *Period : 3rd Period 
 *Due date : 12-10-2019
 */
 public class FilingCabinet
{
	/** Number of filing cabinets (one for each letter A-Z) */
	private static final int NUM_CABINETS = 26;

	/** Array of DoubleNode objects for filing cabinet of Students */
	private DoubleNode<Student>[] cabinet;

	@SuppressWarnings("unchecked")
	public FilingCabinet()
	{
		cabinet = new DoubleNode[26];
	}

	/**
	 * Responsible for adding student object to the start 
	 * @param stu student object that is added to the cabinet
	 */
	public void add(Student stu)
	{

		DoubleNode<Student> node = cabinet[getDrawer(stu)];

		DoubleNode<Student> newVals = new DoubleNode<>(stu, null,null);;

		if(node == null)
		{
			cabinet[getDrawer(stu)] = newVals;
		}
		else if(node.getValue().compareTo(stu) >= 0)
		{
			newVals.setNext(node);
			newVals.getNext().setPrevious(newVals);
			cabinet[getDrawer(stu)] = newVals;
		} 
		else
	   {
			while (node.getNext() != null && node.getNext().getValue().compareTo(stu) < 0)
			{
				node = node.getNext();
			}
			newVals.setNext(node.getNext());
			if(node.getNext() != null)
			{
				newVals.getNext().setPrevious(newVals);
			}
			
			node.setNext(newVals);
			newVals.setPrevious(node);
		}

	}

   /*
    *Responsible for checking whether student object exists in drawer
    *@param student object that is to be checked if in drawer.
    */
	public boolean contains(Student stu)
	{
		DoubleNode<Student> firstVals = cabinet[getDrawer(stu)];
		while (firstVals != null)
		{
			if(firstVals.getValue().equals(stu))
				return true;
			firstVals = firstVals.getNext();
		}
		return false;
	}

	public void remove(Student stu)
	{
	}

	/** @return filing cabinet as a string with each student on a separate line
	 */
	@Override
	public String toString()
	{
		StringBuilder buf = new StringBuilder("Filing Cabinet:\n");
		DoubleNode<Student> drawer;
		for (int k = 0; k < cabinet.length; k++)
		{
			drawer = cabinet[k];
			if (drawer != null)
				buf.append("Drawer " + (char) ('A' + k) + ":  ");
			while (drawer != null)
			{
				buf.append(drawer.getValue().toString() + "\n");
				drawer = drawer.getNext();
				if (drawer != null) buf.append("           ");
			}
		}
		buf.append("**end**");
		return buf.toString();
	}

	/** @return filing cabinet as a string with each student in reverse alphabetical order
	 */
	public String reverseToString()
	{
		StringBuilder buf = new StringBuilder("Reverse Filing Cabinet:\n");
		DoubleNode<Student> drawer;
		for (int k = cabinet.length-1; k >= 0; k--)
		{
			drawer = cabinet[k];
			if (drawer != null)
			{
				buf.append("Drawer " + (char) ('A' + k) + ":  ");

				// go to end
				while (drawer.getNext() != null)
					drawer = drawer.getNext();

				while (drawer != null)
				{
					buf.append(drawer.getValue().toString() + "\n");
					drawer = drawer.getPrevious();
					if (drawer != null) buf.append("           ");
				}
			}
		}
		buf.append("**end**");
		return buf.toString();
	}

	//--------------------------------------------------
	//	Private Methods
	//--------------------------------------------------

	/** Determines which drawer could contain stu
	 *   @return index of the drawer to which this student belongs;
	 *          (uses the 'Z' drawer if last name does not begin with a letter)
	 */
	private int getDrawer(Student stu)
	{
		String lastName = stu.getLastName().toUpperCase();
		char letter = lastName.charAt(0);
		if (letter < 'A' || letter > 'Z') letter = 'Z';
		return letter - 'A';
	}
}