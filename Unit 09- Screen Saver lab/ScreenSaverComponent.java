/**
 *The ScreenSaverComponent class is responsible for filling a graphic window with circles 
 *in a screensaver fashion by ensuring that a limited number of circles are displayed 
 *on the screen at one time.
 *@author : Rishi Villa
 *Collaborators : None
 *Teacher : Mrs. Ishman
 *Period : 3
 *Due date : 1-31-2020
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.geom.Ellipse2D;

public class ScreenSaverComponent extends JComponent
{
    // Starting color for drawing
    private static final Color STARTING_COLOR = Color.magenta;

    // Min and max values for translations of x and y
    private static final int MIN_CHANGE = 5;
    private static final int MAX_CHANGE = 50;

    private Color keyColor;
    private int diameter;
    private int capQueue;
    private Queue<Circle> q1;
    private Point backReference;
    private int pathX;
    private int pathY;
    private int diffX;
    private int diffY;


    public ScreenSaverComponent(int max, int diam, int chgX, int chgY)
    {
        capQueue = max;
        diameter = diam;
        diffX = chgX;
        diffY = chgY;
        pathX = 1;
        pathY = 1;
        backReference = null;
        q1 = new LinkedList<>();
    }

    /** Add a new circle to be drawn and then draw all circles.
     *  @param g the Graphics object for drawing in this component
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D gr2 = (Graphics2D) g;
        int capW = getWidth();
        int maxHeight = getHeight();
        
        addCircle();
        drawAll(gr2);
    }

    private void drawAll(Graphics2D gr)
    {
        Graphics2D fillComponent = (Graphics2D) gr;
        Queue<Circle> trailCirc = new LinkedList<>(q1);
        while(trailCirc.isEmpty() == false)
        {
            Circle trail2 = trailCirc.poll();
            Point reference1 = trail2.getUpperLeft();
            Ellipse2D.Double shape = new Ellipse2D.Double(reference1.getX(), reference1.getY(), diameter, diameter);
            fillComponent.setColor(trail2.getColor());
            fillComponent.fill(shape);
        }
    }
   
    private void addCircle()
    {
        int capW = getWidth();
        int capH = getHeight();
        
        if (backReference == null)
        {
            backReference = new Point((capW + diameter) / 2, (capH + diameter) / 2);
            keyColor = STARTING_COLOR;
            q1.add(new Circle(backReference, keyColor));
        }
        else
        {
        	   // checking the boundaries in order to deflect the circles
            if (capQueue < q1.size())
            {
                q1.poll();
            }
            if ( diffX + (int)backReference.getX() * pathX > getWidth() || (int)backReference.getX() + diffX * pathX < -(diameter/2))
            {
                pathX = pathX * -1;
                diffX = (int) (Math.random() * (MAX_CHANGE - MIN_CHANGE + 1)) + MIN_CHANGE;
                keyColor = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(), (float)Math.random());
            }
            if ( diffY + (int)backReference.getY() * pathY > getHeight() || (int)backReference.getY() + diffY * pathY < -(diameter/2))
            {
                pathY = pathY * -1;
                diffY = (int) (Math.random() * (MAX_CHANGE - MIN_CHANGE + 1)) + MIN_CHANGE;
                keyColor = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(), (float)Math.random());
            }
            backReference = new Point((int)backReference.getX() + diffX * pathX, (int)backReference.getY() + diffY * pathY);
            q1.add(new Circle(backReference, keyColor));
        }
    }
}


