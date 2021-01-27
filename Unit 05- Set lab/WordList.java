/** The WordList class is responsible for reading in the file name 
 *and words from the given input and assign them to appropriate methods for output.
 *
 *@author : Rishi Villa
 *Collaborators : None
 *Teacher Name : Mrs.Ishman
 *Period : 3rd Period
 *Due Date : 10-21-2019
 */
import java.util.*;
import java.io.*;

public class WordList
{
  Scanner scan;
  Set<String> diffWord;


  /**
   * calls the setFile method to reset the variables
   * @param file
   * @throws IOException
   */
  public WordList(String file) throws IOException
  {

     setFile(file);
  }

  /**
   * populates set with alphabetized case-insensitive words.
   * @param file
   */
  public void setFile(String file) throws IOException
  {
     scan = new Scanner(new File(file));
     scan.useDelimiter("[,;:.!?()\\s+]");
     diffWord = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
     while(scan.hasNext())
     {
        String word = scan.next();
        if(word.length() != 0)
           diffWord.add(word);
     }
  }

  /**
   * 
   * @return all unique words
   */
  public Set<String> getWords()
  {
     return diffWord;
  }

  /**
   *
   * @param moreWords
   * @return alphabetized words from both sets
   */
  public Set<String> getAllWords(Set<String> moreWords)
  {
     Set<String> join = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
     join.addAll(moreWords);
     join.addAll(diffWord);
     return join;
  }

  /**
   *
   * @param moreWords
   * @return common words fro both sets
   */
  public Set<String> getCommonWords(Set<String> moreWords)
  {
     Set<String> union = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
     union.addAll(moreWords);
     union.retainAll(diffWord);
     return union;
  }
}



