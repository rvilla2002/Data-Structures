/**
 *The PickAloan class is responsible for prompting the user for input 
 *and reads in lender information, the inputfile and writes to the output file
 *@author: Rishi Villa
 *Collaborators: None
 *Teacher name: Mrs.Ishman
 *Period: 3rd Period
 *Due Date: 9-26-2019
 */

import java.util.List;
import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.util.*;

public class PickALoan
{
	/** The main method is responsible for reading in the input file
	 *  and call the methods to decide loan
	 *  @param args Arraylist 
	 */
	public static void main(String[] args) throws IOException
	{
		boolean keepGoing = true;
		while (keepGoing)
		{
			String inputFileName = getInputFileName();

			Scanner readFile = (new Scanner(new File(inputFileName)));
			List<Lender> lenderList = readLenderData(readFile);


			determineBestLoan(readFile, lenderList, getOutputFileName(inputFileName));
			keepGoing = checkContinue();

			readFile.close();

		}
	}

	/** Retrieves the name of an existing input file from the user
	 *  @return file name of an existing text file to read
	 */
	private static String getInputFileName()
	{
		System.out.print("Enter file with loan information: ");
		Scanner in = new Scanner(System.in);
		String fileName = in.next();
		File inputFile = new File(fileName);
		while (!inputFile.exists())
		{
			System.out.print("File not found. Try again.\n\nEnter file with loan information: ");
			fileName = in.next();
			inputFile = new File(fileName);
		}
		System.out.println("Recommendations written to " + fileName);
		return fileName;
	}
	

   /**Retreives name of output file
    *@param String value of name 
    *@return file name of output file
    */
	private static String getOutputFileName(String inputName)
	{
		String fileName = "";
		int dotIndex = inputName.indexOf(".");
		fileName = inputName.substring(0, dotIndex);
		fileName += ".out";
		return fileName;
	}

   /** Prompts user for input to continue
    *  @return boolean value to tell program to continue or not
    */
	private static boolean checkContinue()
	{
		System.out.print("\nWould you like to continue <yes/no>? ");
		Scanner in = new Scanner(System.in);
		String inputUser = in.next();
		System.out.println("");
		String inputChar= inputUser.substring(0,1);
		if (inputChar.equals("y") || inputChar.equals("Y"))
			return true;
		else
			return false;
	}

   /** Creates array list of lender objects to get lender information
    *  @param user input from keyboard
    *  @return arraylist of lender obejcts wuth information 
    */
	private static List<Lender> readLenderData(Scanner scan)
	{
		ArrayList<Lender> list = new ArrayList<Lender>();
		int numLender = scan.nextInt();
		for (int i = 0; i < numLender; i++)
		{
			scan.nextLine();
			String lenderName = scan.nextLine();
			double interest = scan.nextDouble();
			double payment = scan.nextDouble();
			double fee = scan.nextDouble();
			list.add(new Lender(lenderName, interest, payment, fee));
		}

		return list;
	}

   /** Determines the appropriate loan for student and writes into file
    *  @param uder input, list of lender objects and name of output file
    */
	private static void determineBestLoan(Scanner scan, List<Lender> lenders, String outputFile)
			throws IOException
	{
		PrintWriter out = new PrintWriter(outputFile);
		int numLender = scan.nextInt();
		for (int i = 0; i < numLender; i++)
		{

			scan.nextLine();
			String studentName = scan.nextLine();
			double loanAmount = scan.nextDouble();
			int years = scan.nextInt();

			Lender lender = lenders.get(0);
			for(int j = 1; j < lenders.size(); j++)
				if(lenders.get(j).getMonthlyPayment(loanAmount, years)
						< lender.getMonthlyPayment(loanAmount, years))
					lender = lenders.get(j);

			double cost = lender.getMonthlyPayment(loanAmount, years);
			String lenderName = lender.getName();
			out.printf("%s should choose the loan from %s for $%6.2f per month.\n",
					studentName, lenderName, cost);
		}
		out.close();
	}
}
