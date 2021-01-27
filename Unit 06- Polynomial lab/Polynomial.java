/**
 * The Polynomial class is responsible for to print and sort a list of polynomial terms
 * based on the coefficient and exponent.
 * @author : Rishi Villa
 * Collaborators : None
 * Teacher name : Mrs.Ishman
 * Period : 3
 * Due date : 11-14-2019
 */
import java.util.ListIterator;

public class Polynomial
{
  private ListNode<Term> list;

  /**
   *
   */
  public Polynomial()
  {
     list = null;
  }

  /**
   * The addTerm method uses nodes to insert terms in decreasing order of exponents.
   * @param coeff
   * @param power
   */
  public void addTerm(int coeff, int power)
  {
     if(coeff == 0)
        return;
     ListNode<Term> termsNode = new ListNode<Term>(new Term(coeff, power), null);
     if (list == null)
        list = termsNode;
     else if(power > list.getValue().getPower())
     {
           termsNode.setNext(list);
           list = termsNode;
     }
     else
     {
        ListNode<Term> termsOld = list;
        ListNode<Term> termsCurrent = list.getNext();
        while (!(termsCurrent == null) && (power < termsCurrent.getValue().getPower()))
        {
           termsOld = termsCurrent;
           termsCurrent = termsCurrent.getNext();
        }
        termsNode.setNext(termsCurrent );
        termsOld.setNext(termsNode);
     }
  }


  public Polynomial sum(Polynomial other)
  {
          return null;
  }

  
  /**
   * The toString returns polynomials with a "+" between it.
   * @return out 
   */
  @Override
  public String toString()
  {
     ListNode<Term> temp;
     String out = "";
     if(list == null)
        return "0";
     for(temp = list; !(temp.getNext() == null); temp = temp.getNext())
        out += temp.getValue() + " + ";
     out += temp.getValue();
     return out;
  }


  /**
   *  Private Inner Class
   */
  private class Term
  {
     /** Coefficient and exponent of a polynomial term
      */
     private int coeff;
     private int power;

     /**  Constructs a term with the given coefficient and exponent
      *   @param co the coefficient for this term
      *   @param pwr the exponent for this term
      */
     public Term(int co, int pwr)
     {
        coeff = co;
        power = pwr;
     }

     /** Returns the coefficient for this term
      *  @return coefficient of this term
      */
     public int getCoefficient()
     {
        return coeff;
     }

     /** Returns the exponent for this term
      *  @return exponent of this term
      */
     public int getPower()
     {
        return power;
     }
     /**
      * The toString method returns the linked list as a string polynomial.
      * @return power
      * @return coeff 
      * @return information about current state of Polynomial class
      */
     @Override
     public String toString()
     {
     		if(coeff == 0)
     		{
     			return "";
     		}
         if(coeff == 1 && power == 1)
         {	
         	return "x";
         }
         if(coeff == -1 && power == 1)
          {
          	return "-x";
          }
         if(coeff == 1)
         {
         	return "x^" + power;
         }
             
         if(coeff == -1)
         {
         	return "-x^" + power;
         }
              
         if(power == 0)
         {
         	return "" + coeff;
         }
         if(power == 1)
         {
         	return coeff + "x";
         }
         return coeff + "x^" + power;
     }
  }
}




