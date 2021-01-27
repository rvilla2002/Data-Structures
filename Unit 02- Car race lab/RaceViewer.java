/**
 *The RaceViewer class is responsible for creating a frame and adding the component 
 *It is also resposnible for running the TimerListener class.
 *
 *@author : Rishi Villa
 *Collaborators: None
 *Teacher Name: Mrs.Ishman
 *Period : 3
 *Due date: 9-13-2019;
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JComponent;

//Declares and instantiates all the constants.
public class RaceViewer extends JComponent
{
  private static final int WIDTH = 900;
  private static final int HEIGHT = 1000;
  private static final int CAR_SPEED = 20;
  private static final int CAR_POS_X = 10;
  private static final int CAR1_POS_Y = 300;
  private static final int CAR2_POS_Y = 600;

  public static void main(String[] args)
  {
     // creates the frame
     JFrame frame = new JFrame("Race Viewer");
     frame.setSize(WIDTH, HEIGHT);
     frame.add(new RaceViewer());
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     

     //specifies the dimensions for the car.
     Car carRacer1 = new Car( CAR_POS_X, CAR1_POS_Y, Color.magenta);
     Car carRacer2 = new Car(CAR_POS_X, CAR2_POS_Y, Color.blue);
     RaceComponent game = new RaceComponent(carRacer1, carRacer2);
     frame.add(game);
     frame.setVisible(true);
     frame.setResizable(false);
     
     //uses the TimerListener class
     ActionListener listener = new TimerListener(game);
     Timer time = new Timer(CAR_SPEED, listener);
     time.start();

  }
}
