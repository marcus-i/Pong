package PongGame; //group of relate classes

import java.awt.Graphics; //import java graphics
import java.awt.*; //imports all abstract window tools

public class PingPongBall {
    public static final int SIZE = 25;
    private int x, y;
    private int xVel, yVel; // value either 1 or -1
    private int speed = 5; //default ball speed

    //resets the ball when the ball scores
    public PingPongBall() {
        reset();
    }

    //sets the ball to the center of the screen
    //sets movement speed to default value
    private void reset() {
        x = Pong.length / 2 - SIZE / 2;
        y = Pong.length2 / 2 - SIZE / 2;
        // initial velocities or x and y displacement for the ball movement
        xVel = Pong.sign(Math.random() * 2.0 - 1);
        yVel = Pong.sign(Math.random() * 2.0 - 1);
    }

    //change in the y displacement speed
    public void changeYDir() {
        yVel *= -1;
    }

    //change in the x displacement speed
    public void changeXDir() {
        xVel *= -1;
    }

    /*
    creates the ball
    displays the ball with a color of white and its size
    */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void update(Paddles p1, Paddles p2) {
        /*
        update the movement speed of the balls
        by increasing the x and y displacement
        */
        x += xVel * speed;
        y += yVel * speed;

        //checks for the ball collision
        if (y + SIZE >= Pong.length2 || y <= 0)
            changeYDir();

        //checks for the ball collision with walls
        if (x + SIZE >= Pong.length) {
            p1.addPoint();
            reset();
        }
        if (x <= 0) {
            p2.addPoint();
            reset();
        }
    }

    //returns the x coordinate position of the center of ball
    public int getX() {
        return x;
    }

    //returns the y coordinate position of the center of ball
    public int getY() {
        return y;
    }
}