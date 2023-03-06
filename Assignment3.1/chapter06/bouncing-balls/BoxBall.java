import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.util.HashSet;
/**
 * Creates a box with a ball bouncing inside.
 *
 * @author Eric Wallace
 * @version 1.0
 */
public class BoxBall
{
    // instance variables - replace the example below with your own
    private static final int GRAVITY = 3;
    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    // y position of ground.
    private final int groundPosition;
    // x position of left wall.
    private final int leftWallPos;
    // x position of right wall.
    private final int rightWallPos;
    // Check if it is moving right.
    private boolean isMovingRight;
    private Random rng;
    private Canvas canvas;
    // initial downward speed.
    private int ySpeed = 1;

    /**
     * Constructor for objects of class BoxBall.
     * 
     * @param xPos          the horizontal coordinate of the ball.
     * @param yPos          the vertical coordinate of the ball.
     * @param ballDiameter  the diameter (in pixels) of the ball.
     * @param groundPos     the position of the ground.
     * @param leftWall      the position of the left wall.
     * @param rightWall     the position of the right wall.
     * @param drawingCanvas the canvas to draw the ball on.
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, int groundPos, int leftWall, int rightWall, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        // the random color
        color = new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
        diameter = ballDiameter;
        groundPosition = groundPos;
        leftWallPos = leftWall;
        rightWallPos = rightWall;
        canvas = drawingCanvas;
        rng = new Random();
        // the ball moves in random direction.
        isMovingRight = rng.nextBoolean();
    }

    /**
     * Draw the ball at its current position on the canvas.
     *
     */
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Erase the ball at its current position.
     */
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }
    
        /**
     * Move the ball according to its position and speed then redraw.
     */
    public void move()
    {
        //remove from canvas at the current position erase();
        // compute new position.
        ySpeed += GRAVITY;
        yPosition += ySpeed;
        
        // check for ball direction
        if(isMovingRight) {
            xPosition += 2;
        } else {
            xPosition -= 2;
        }
        // check if it has hit a wall
        if(xPosition <= leftWallPos) {
            isMovingRight = true;
            xPosition = leftWallPos;
        }
        if(xPosition >= (rightWallPos - diameter)){
            isMovingRight = false;
            xPosition = rightWallPos - diameter;
        }
        // check if it has hit the ground
        if (yPosition >= (groundPosition - diameter) && ySpeed > 0) {
            yPosition = (int) (groundPosition - diameter);
            ySpeed = -ySpeed + ballDegradation;
        }
        
        // draw again at new position
        draw();
    }
    
    public int getyPosition()
    {
        return yPosition;
    }
    
    public int getxPosition()
    {
        return xPosition;
    }
    
    public int getDiameter()
    {
        return diameter;
    }
    
    /**
     * Simulate bouncing balls in a box.
     * @param numberOfBalls Number of bouncing balls
     */
    public void boxBounce(int numberOfBalls)
    {
        canvas.setVisible(true);
        //draw the box
        canvas.setForegroundColor(Color.pink);
        canvas.fillRectangle(20, 20, 560, 460);
        canvas.setForegroundColor(Color.WHITE);
        canvas.fillRectangle(25, 25, 550, 450);
        int ground = 25 + 450;
        int leftWall = 25;
        int rightWall = 25 + 550;
        
        // create and show the balls
        HashSet<BoxBall> balls = new HashSet<>(numberOfBalls, 1.0f);
        for (int i = 0; i < numberOfBalls; i++) {
            balls.add(new BoxBall(50 + rng.nextInt(250), 10 + rng.nextInt(80),
                16 + (i*4),ground, leftWall, rightWall, canvas));
        }
        // draw each ball
        balls.forEach(BoxBall::draw);
        
        // make them bounce
        boolean finished = false;
        while(!finished) {
            canvas.wait(50); // small delay
            finished = true;
            for(BoxBall ball : balls) {
                ball.move();
                
                // stop once each ball has landed
                if (ball.getyPosition() < (ground - ball.getDiameter())) {
                    finished = false;
                }
            }
        }
    }
}
