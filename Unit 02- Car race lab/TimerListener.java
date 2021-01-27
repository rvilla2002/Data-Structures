/**
 * The TimerListener class is responsible for checking the time intervas
 *between each car movement.
 *
 *@author : Rishi Villa
 *Collaborators: None
 *Teacher Name: Mrs.Ishman
 *Period : 3
 *Due date: 9-13-2019;
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * declares rhe RaceComponent object
 */
public class TimerListener implements ActionListener
{
   private RaceComponent carRunner;

   public TimerListener(RaceComponent carRunner)
   {
       this.carRunner = carRunner;
   }

   public void actionPerformed(ActionEvent event)
   {
       carRunner.advanceCars();
       carRunner.isRaceOver();

   }
}






