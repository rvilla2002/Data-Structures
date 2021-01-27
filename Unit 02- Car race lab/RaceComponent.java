/**
 *The RaceComponent class makes the cars and the finish line 
 *in the component.
 *
 *@author : Rishi Villa
 *Collaborators: None
 *Teacher Name: Mrs.Ishman
 *Period : 3
 *Due date: 9-13-2019;
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * declares and instantiates the variable and constants.
 */
public class RaceComponent extends JComponent
{
  Car carRacer1;
  Car carRacer2;
  private static final int LINE_POSITION = 550;
  private static final int LINE_WIDTH = 10;
  private static final int LINE_HEIGHT = 1100;
  private static final int LINE_CHECK = 475;
   
   /**
    *instantiates the instance variables
    * @param car objects
    */
   public RaceComponent(Car carRacer1, Car carRacer2)
   {
       this.carRacer1 = carRacer1;
       this.carRacer2 = carRacer2;
   }

   /**
    *Paints the cars and the finishline
    * @param the graphics2D object
    */
   public void paintComponent(Graphics gr)
   {
       Graphics2D g = (Graphics2D) gr;
       carRacer1.draw(g);
       carRacer2.draw(g);
       Rectangle finishLine = new Rectangle(LINE_POSITION, 0, LINE_WIDTH, LINE_HEIGHT);
       g.setColor(Color.red);
       g.draw(finishLine);
       g.fill(finishLine);

   }
   /**
    *moves the cars forward
    */
   public void advanceCars()
   {
       carRacer1.moveForward((int)(Math.random() * 5) + 1);
       carRacer2.moveForward((int)(Math.random() * 5) + 1);
       repaint();

   }
   
   /**
    *checks when the race is over to restart
    */
   public boolean isRaceOver()
   {
       if(carRacer1.getBox().getX() > LINE_CHECK)
       {
           carRacer1.resetX();
           carRacer2.resetX();

           return true;
       }

       return false;

   }


}
