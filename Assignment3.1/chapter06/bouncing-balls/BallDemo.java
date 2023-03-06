import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Random randomNumber;
    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        randomNumber = new Random();
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate bouncing balls
     * @param numberOfBalls How many bouncing balls?
     */
    public void bounce(int numberOfBalls)
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        HashSet<BouncingBall> balls = new HashSet<>(numberOfBalls);
        for (int i = 0; i < numberOfBalls; i++) {
            balls.add(new BouncingBall(50 + randomNumber.nextInt(250), 50,
                16 + (i*4), Color.BLUE, ground, myCanvas));
        }
        // draw the balls
        balls.forEach(BouncingBall::draw);
        
        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            for(BouncingBall ball : balls) {
                ball.move();
            // stop once ball has travelled a certain distance on x axis
                if(ball.getXPosition() >= 550) {
                    finished = true;
                }
            }    
        }
    }
}
