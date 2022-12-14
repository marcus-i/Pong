package PongGame; //group of relate classes

import java.awt.*; //imports all abstract window tools
import java.awt.Graphics; //import java graphics

public class Paddles {
    private int x, y;
    private int vel = 0;
    private int speed = 10;
    private int width = 22, height = 85;
    private int score = 0;
    private Color color;
    private boolean left;

    //paddles constructor
    public Paddles(Color c, boolean left){
        color = c;

        this.left = left;

        if (left)
            x = 0;
        else
            x = Pong.length - width;
        y = Pong.length2 / 2 - height/2 ;
    }

    //updates the score
    public void addPoint(){
        score ++;
    }

    /*creates the paddle on the screen
    displays the score on the screen as well
     */
    public void draw(Graphics g) {
        // draws the paddle on the screen
        g.setColor(color);
        g.fillRect(x,y,width, height);
        // displays the score on the screen
        int sx;
        String scoreText = Integer.toString(score);
        Font font = new Font("Roboto", Font.PLAIN, 50);
        int strWidth = g.getFontMetrics(font).stringWidth(scoreText) + 1;
        int padding = 25;
        if (left)
            sx = Pong.length / 2 - padding /2 - strWidth;
        else
            sx = Pong.length /2 + padding /2;
        g.setFont(font);
        g.drawString(scoreText , sx, 50);

    }

    //updates the position of the paddle and its collision with ball
    public void update(PingPongBall b) {
        // update position of the ball
        y = Pong.ensureRange(y += vel, 0, Pong.length2 - height);
        int ballx = b.getX();
        int bally = b.getY();
        //  checks for collision with the ball
        if(left){
            if (ballx <= width && bally + b.SIZE >= y && bally <= y +height )
                b.changeXDir();
        }
        else {
            if (ballx + b.SIZE >= Pong.length - width && bally + b.SIZE >= y && bally <= y + height)
                b.changeXDir();

        }
    }

    //updates the direction of the paddle
    public void switchDirection(int direction) {
        vel = speed * direction;
    }

    //stops the paddle keeping the x coordinate 0
    public void stop(){
        vel = 0;
    }
}
