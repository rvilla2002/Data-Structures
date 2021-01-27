/**
 *The EvilHangMan class contains the required helper methods in order to run the game.
 *It also contains the method playGame() which is required to play the game.
 *@author : Rishi Villa
 * Collaborators:None
 * Teacher Name: Mrs.Ishman
 * Period:3
 * Due Date:08-20-19
*/
 
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
 
public class EvilHangman
{
  private boolean debug;
  private Scanner in;
  private List<String> wordList;
  private int wordLength;
  private int remainingGuesses;
  private String solution;
  private String guessedLetters;
 
 /**This is the constructor which instantiates some of the instance variables
  *and takes user input and adds it to solution.
  */
  public EvilHangman(String fileName, boolean debug)
    throws FileNotFoundException
  {
   this.debug = debug;
   in = new Scanner(System.in);
   inputWords(fileName);
   System.out.print("Number of guesses? ");
   remainingGuesses = in.nextInt();
   solution = "";
   for(int i=0;i < wordLength;i++)
  
   {
     solution+="-";
   }
   guessedLetters = "";
  
  }
  /**This method calls the helper methods and also returns
   *the appropriate error message when playing the game.
   */
  
  public void playGame()
  {
   while ((solution.indexOf("-") > -1) && remainingGuesses > 0)
   {
     System.out.println(this.toString());
     String letter = inputLetter();
     guessedLetters += letter;
     List<Partition> list = getPartitionList(letter);
     removeSmallerPartitions(list);
     wordList = getWordsFromOptimalPartition(list);
     String oldSolution = solution;
     substitute(wordList.get(0), letter);
     if (solution.equals(oldSolution))
       remainingGuesses--;
   }
   if (remainingGuesses != 0)
     System.out.println("You win, congratulations!");
   else
     System.out.println("You lose, sorry!");
   int index = (int)(Math.random() * (0 + wordList.size() - 1 + 1) + 0);
   System.out.println("The word was \"" + wordList.get(index) + "\"");
  }
  
  //This method formats the output and prompts the user for appropriate input.
  public String toString()
   {
       if(debug)
           return " Remaining guesses: " + remainingGuesses + "\nGuessed letters: " +
                   guessedLetters + "\nSolution: " + solution + "\nRemaining words: " + wordList.size() + " ";
       return " Remaining guesses: " + remainingGuesses +
               "\nGuessed letters: " + guessedLetters + "\nSolution: " + solution + " ";
   }
 
 
   ////////// PRIVATE HELPER METHODS //////////
   
   
 //This method populates the arraylist with words after checking the word length.
 private void inputWords(String fileName) throws FileNotFoundException
   {
       wordList = new ArrayList<>();
       while(wordList.size()==0)
       {
           System.out.print("Word length? ");
           if(in.hasNext())
           {
             wordLength = in.nextInt();
           }
           Scanner file = new Scanner(new File(fileName));
           while(file.hasNext())
           {
             String nextName = file.next();
               if (nextName.length() == wordLength)
               {
                   wordList.add(nextName);
               }
           }
           if(wordList.size()==0)
               System.out.println("There are no words with " + wordLength + " letters.");
       }
   }
 //This method specifies the parameter for the letters that can be added.
   private String inputLetter()
   {
       boolean caseCheck = true;
       while(caseCheck)
       {
           System.out.print("Next letter? ");
           String givenLetter = in.next().toUpperCase();
           if (givenLetter.length()==1 && givenLetter.charAt(0)>='A' && givenLetter.charAt(0)<='Z')
           {
               return givenLetter;
           }   
           System.out.println("Invalid input!");
          
       }
      return "";
   }
   
  //This method matches the inputed letter to a word and only adds if condition is met.
  private String getPattern(String word, String letter)
  {
   String guesses = "";
    for(int i = 0;i<word.length();i++)
     {
       if(word.substring(i,i+1).equals(letter))
       {
         guesses+=letter;
       }
       else
       {
         guesses+="-";
       }
     }
   return guesses; 
  
  }
  
  //This method searches the array list and adds the key word if conditions are met.
  private List<Partition> getPartitionList(String letter)
  {
    List<Partition> partitionLists = new ArrayList<Partition>();
   boolean partitionChecker = false;
   for(int i = 0;i<wordList.size();i++)
   {
     String loopedWord = getPattern(wordList.get(i),letter);
     for(int j = 0; j < partitionLists.size(); j++)
     {
       if(partitionLists.get(j).addIfMatches(loopedWord,wordList.get(i)))
       {
         partitionChecker = true;
       }
     }
       if(!partitionChecker)
         partitionLists.add(new Partition(loopedWord, wordList.get(i)));
     partitionChecker = false;
   }
   return partitionLists;
 
  }
  
  //This method removes all partitions except the largest one as in the one with most words.
  private void removeSmallerPartitions(List<Partition> partitions)
  {
   int maximum = 0;
   for(int i = 0;i<partitions.size();i++)
   {
     if(partitions.get(i).getWords().size()>maximum)
     {
       maximum = partitions.get(i).getWords().size();
     }
   }
   for(int i = 0;i<partitions.size();i++)
   {
     if(partitions.get(i).getWords().size()<maximum)
     {
       partitions.remove(i);
       i--;
     }
   }
  
  }
  
  //This word picks the partitions with most dashes in it.
  private List<String> getWordsFromOptimalPartition(List<Partition> partitions)
  {
    Partition part = partitions.get(0);
   for(int i = 0;i < partitions.size();i++)
   {
     if(partitions.get(i).getPatternDashCount()>part.getPatternDashCount())
     {
       part = partitions.get(i);
     }
   }
   return part.getWords();
  }
  
  //This method substitutes the dash for the letter if conditions are met.
  private void substitute(String found, String letter)
  {
   String str = "";
  
   for(int i = 0;i < found.length();i++)
   {
     if(found.substring(i, i+1).equals(letter))
     {
       str += letter;
     }
     else
     {
       str += solution.substring(i,i+1);
     }
   }
  solution = str;
  }
}
