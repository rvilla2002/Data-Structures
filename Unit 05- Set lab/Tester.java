/**
 *The Tester class is responsible for prompting the user 
 *for appropriate input and giving the desired output from the files.
 *
 *@author: Rishi Villa
 *Collaborators : None
 *Teacher Name: Mrs.Ishman
 *Period : 3rd period
 *Due Date : 10-21-2019
 */
import java.io.*;
import java.util.*;
public class Tester
{
	/**
	 *
	 *prompts the user for input and scans the files
	 *@param args
	 */
   public static void main(String[] args) throws IOException
   {
       String readFile = getInputFileName("Enter file to read: ");
       WordList readIn = new WordList(readFile);
       System.out.println("Word list from " + readFile);
       System.out.println(readIn.getWords());
       System.out.println();
       String nameFile= getInputFileName("Enter another file to read to union with first: ");
       WordList readInNext = new WordList(nameFile);
       Set<String> wordSequence = readInNext.getWords();
       System.out.println("Word list from " + nameFile);
       System.out.println(wordSequence);
       System.out.println();
       System.out.println("All words from " + readFile + " and " + nameFile);
       System.out.println(readIn.getAllWords(wordSequence));
       System.out.println();
       nameFile = getInputFileName("Enter another file to read to intersect with first: ");
       readInNext.setFile(nameFile);
       wordSequence = readInNext.getWords();
       System.out.println("Word list from " + nameFile);
       System.out.println(wordSequence);
       System.out.println();
       System.out.println("Common words from " + readFile + " and " + nameFile);
       System.out.println(readIn.getCommonWords(wordSequence));
   }
   /**
    *
    *checks if program should move to next file
    *@return boolean value to move on or not
    */
   private static boolean checkContinue()
   {
       System.out.print("Do you want to try again (y/n)?");
       Scanner input = new Scanner(System.in);
       String answer = input.next();
       System.out.println();
       String key = answer.substring(0,1);
       if (key.equals("Y") || key.equals("y"))
           return true;
       else
           return false;
   }


   /**
    *
    *checks if file exists
    *@param inFile
    *@return name of the file
    */
   private static String getInputFileName(String inFile)
   {
       Scanner scan = new Scanner(System.in);
       System.out.print(inFile);
       String fileTitle = scan.next();
       File inputFile = new File(fileTitle);
       boolean check = true;
       while (check && !inputFile.exists())
       {
           System.out.print(fileTitle + " does not exist. ");
           check = checkContinue();
           if (check)
           {
               System.out.print("\n" + inFile);
               fileTitle = scan.next();
               inputFile = new File(fileTitle);
           }
       }
       return fileTitle;
   }
}



