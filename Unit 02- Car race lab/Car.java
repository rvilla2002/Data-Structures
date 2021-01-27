/**
 * The Car.java class is responsible for creating the component 
 * to draw the car in and it also creates the two cars required for racing
 *
 *@author : Rishi Villa
 *Collaborators: None
 *Teacher Name: Mrs.Ishman
 *Period : 3
 *Due date: 9-13-2019;
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class Car implements Racer
{
   int x;
   int y;
   Color carColor;
   private static final int INITIAL_X = 10;
   private static final int X_WHEEL1 = 2;
   private static final int Y_WHEEL1 = 20;
   private static final int X_WHEEL2 = 51;
   private static final int X_HEADLIGHT = 70;
   private static final int Y_HEADLIGHT = 10;
   private static final int SIZE_ELLIPSE = 12;
   private static final int X_BACKLIGHT = 5;
   private static final int W_BACKLIGHT = 8;
   private static final int X_CAR = 75;
   private static final int Y_CAR = 25;
    
   /**
    * Constructor instatiates the instance variables
    * @param dimensions and color of car
    */
   public Car(int x, int y, Color carColor)
   {
       this.x = x;
       this.y = y;
       this.carColor = carColor;
   }

   /** Responsible for drawing the car, wheels and lights
    *  @param gr is the Graphics2D component to draw in
    */
   public void draw(Graphics2D gr)
   {
       gr.setColor(carColor);
       Rectangle base = new Rectangle(x, y, X_CAR, Y_CAR);
       Ellipse2D.Double wheel1 = new Ellipse2D.Double(x + X_WHEEL1, y + Y_WHEEL1, Y_WHEEL1, Y_WHEEL1);
       Ellipse2D.Double wheel2 = new Ellipse2D.Double(x + X_WHEEL2 , y + Y_WHEEL1, Y_WHEEL1, Y_WHEEL1);
       Ellipse2D.Double headLight = new Ellipse2D.Double(x + X_HEADLIGHT, y + Y_HEADLIGHT, SIZE_ELLIPSE, SIZE_ELLIPSE);
       Rectangle backLight = new Rectangle (x - X_BACKLIGHT, y + Y_HEADLIGHT, W_BACKLIGHT, SIZE_ELLIPSE);
       gr.draw(base);
       gr.fill(base);
       gr.setColor(Color.black);
       gr.draw(wheel1);
       gr.fill(wheel1);
       gr.draw(wheel2);
       gr.fill(wheel2);
       gr.setColor(Color.yellow);
       gr.fill(headLight);
       gr.setColor(Color.red);
       gr.fill(backLight);
       gr.setBackground(Color.green);



   }

   /**
    * moves the car forwarda by an amount
    * @param ammount to move forward by
    */
   public void moveForward(int amount)
   {
       x += amount;
      

   }

   /**
    * gives the outline for the car
    * @return rectangle outline for car
    */
   public Rectangle getBox()
   {
       return new Rectangle(x, y, X_CAR, Y_CAR);
   }
   /**
    * resets x
    */
   public void resetX()
   {
       x = INITIAL_X;
   }

}



