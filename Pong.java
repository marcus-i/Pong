package PongGame; //group of relate classes
 
import java.awt.Graphics; //import java graphics
import java.awt.*;  //imports all abstract window tools
import java.awt.image.BufferStrategy;

/*
extends Canvas to create a rectangular screen for the game
and implements Runnable to be the core code of the java program
and is able to run threads
*/
public class Pong extends Canvas implements Runnable{
    public static final int length =1000;
    public static final int length2 = length *9/16;
    public boolean running = false;
    private Thread gameThread;
    private PingPongBall ball;
    private Paddles paddle1;
    private Paddles paddle2;

    //constructor for pong
    public Pong() {
        canvasSetup();
        init();
        new PongGame.Graphics("Pong", this);
        this.addKeyListener(new KeyInput(paddle1, paddle2));
        this.setFocusable(true);
    }

    //returns the middle numbers of the 3 values given
    public static int ensureRange(int val, int min, int max) {
        return Math.min(Math.max(val, min), max);
    }

    //creates the ball and paddles for the game
    private void init() {
        ball = new PingPongBall();
        paddle1 = new Paddles(Color.white, true);
        paddle2 = new Paddles(Color.white, false);
    }

    //sets up the display to the size needed for the game
    private void canvasSetup() {
        this.setPreferredSize(new Dimension(length,length2));
        this.setMaximumSize(new Dimension(length,length2));
        this.setMinimumSize(new Dimension(length,length2));
    }

    //maintains time
    //keeps frames high in order to prevent buffering in paddle and ball movement
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTick = 60.0;
        double ns = 1000000000 / amountOfTick;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now -lastTime) /ns;
            lastTime = now;
            while (delta >-1){
                update();
                delta--;
            }
            if (running)
                draw();
            frames ++;

            if(System.currentTimeMillis() - timer >1000){
                timer += 1000;
                System.out.println("FPS: "+frames);
                frames++;
            }
        }
        stop();
    }

    //draws the paddles and ball on the graphical display screen
    //also displays the score for each paddle
    private void draw() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = buffer.getDrawGraphics();
        drawBackground(g);
        ball.draw(g);
        paddle1.draw(g);
        paddle2.draw(g);
        g.dispose();
        buffer.show();
    }

    //draws the background of the screen such as the dashed line in the middle
    private void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,length,length2);
        g.setColor(Color.white);
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{10},0);
        g2d.setStroke(dashed);
        g2d.drawLine(length/2,0,length/2,length2);
    }

    //updates the overall position of the paddle and ball
    private void update() {
        ball.update(paddle1, paddle2);
        paddle1.update(ball);
        paddle2.update(ball);

    }

    //starts the game
    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    //stops the game
    public void stop(){
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //helps x and y displacement speed for the ball
    public static int sign(double d){
        if (d <= 0)
            return -1;
        return 1;
    }

    public static void main (String[] args){
        new Pong();
    }
}