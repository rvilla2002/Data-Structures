/** The lender class is responsible for calculating monthy payment based 
 *on loan ammount and returning other lender information and payment info
 *
 *@author: Rishi Villa
 *Collaborators: None
 *Teacher Name: Mrs.Ishman
 *Period: 3rd Period
 *Due Date: 9-26-2019
 */
public class Lender 
{
	private static final int MONTHS_PER_YEAR = 12;
	
	private String lender;			     // name of lending institution
	private double monthlyRate;	    // monthly interest rate
	private double minPayment;      // minimum payment amount
	private double monthlyFee;      // additional monthly fee

	
	/** Constructs a Lender for the given lending institution
	 *  given the loan information
	 *  @param lender name of lending institution
	 *  @param rate annual interest rate, where 10.5 means 10.5%
	 *  @param minPayment minimum monthly payment amount
	 *  @param fee an additional monthly fee
	 */
	public Lender(String lender, double rate, double minPayment, double fee) 
	{
		this.lender = lender;
		this.monthlyRate = rate / MONTHS_PER_YEAR / 100;
		this.minPayment = minPayment;
		this.monthlyFee = fee;
	}
	

	/** Uses formula to calculate total monthly payment
	 *  @param takes amount of loan and number of years 
	 *  @return total monthly payment amount
	 */
	public double getMonthlyPayment(double loanAmount, int years) 
	{
		double monthlyPayment = loanAmount * Math.pow((1 + monthlyRate), years * MONTHS_PER_YEAR);
		double monthlyPayment2 = monthlyPayment / (Math.pow((1 + monthlyRate), years * MONTHS_PER_YEAR) - 1);
		double finalMonthlyPayment = (monthlyRate* monthlyPayment2) + monthlyFee;
		if (finalMonthlyPayment < minPayment)
			return minPayment;
		else
			return finalMonthlyPayment;
	}

	/** Retrieves the lender's name
	 *  @return name of lender
	 */
	public String getName() 
	{
		return lender;
	}
		 	
	/** Retrieves the annual interest rate
	 *  @return annual interest rate
	 */
	public double getAnnualRate() 
	{
		return monthlyRate * MONTHS_PER_YEAR * 100;
	}
		 	
	/** Retrieves the minimum monthly payment to be paid
	 *  @return minimum monthly payment amount
	 */
	public double getMinimumPayment() 
	{
		return minPayment;
	}
	
	/** Retrieves the mandatory monthly fee
	 *  @return additional monthly fee
	 */
	public double getMonthlyFee() 
	{
		return monthlyFee;
	}

}



