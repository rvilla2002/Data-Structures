/**
 *The CollegeMapping class is responsible for mapping students to their 
 *choice of university and adds to the list if needed.
 *
 *@author: None
 *Collaborators: None
 *Teacher Name : Mrs.Ishman
 *Period : 3rd Period
 *Due Date : 10-28-2019
 */
import java.util.*;
public class CollegeMapping 
{
	private final static String UNDECIDED = "Undecided";
    private Map<Student, Set<String>> stuChoice;
    private static final String NO_COLLEGE = "No college mappings";
    private static final String NO_STUDENT = "Student not in list";
   /**
    *assigns treeset that maps the names and ensures that 
    *the values are sorted by id in the map.
    */ 
   public CollegeMapping()
   {
    	stuChoice = new TreeMap<>(Student::compareTo);
   }
  /**
   *responsible for taking student input and adding it to map 
   *@param Student object and University name 
   */
  public void updateStudent(Student stu, String univ)
   {
   	 boolean check = univ.equals(UNDECIDED) || univ.isEmpty() || univ == null;
        if(!stuChoice.containsKey(stu) || check)
        {
        	stuChoice.put(stu, new TreeSet<String>());
        }
        if(!check)
        {
        	stuChoice.get(stu).add(univ);
        }
   }
   /**
    *removes student from map if not found.
    *@param student object 
    */
   public void removeStudent(Student student1)
   {
   	stuChoice.remove(student1);
   }
   /**
    *@return all the students and their respective university choices
    */
   public String getAllStudents()
    {
        String mapTo = "";
        for(Map.Entry<Student, Set<String>> map: stuChoice.entrySet())
            mapTo += map.getKey().toString() + " => " + map.getValue().toString() + "\n";
        if(mapTo.length() == 0)
            return NO_COLLEGE;
        return mapTo;
    }
   /**
    *@param student object 
    *returns all the university choices for a single student
    */
   public String getUniversityChoices(Student stu)
    {
        if(stuChoice.containsKey(stu))
        {
        	return stuChoice.get(stu).toString();	
        }
        return NO_STUDENT;
    }
   /**
    *@param String university name 
    *@return all the students attending given university
    */
   public ArrayList<String> getStudentsAt(String universityName)
    {
        ArrayList<String> stuAtUni = new ArrayList<>();
        for(Map.Entry<Student, Set<String>> stu : stuChoice.entrySet())
            if((universityName.equals(UNDECIDED) && stu.getValue().size() == 0) || stu.getValue().contains(universityName))
            {
             	stuAtUni.add(stu.getKey().toString());
            }
        return stuAtUni;
    }
}

