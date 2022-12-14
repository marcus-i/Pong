package PongGame; //group of relate classes

import java.awt.event.KeyAdapter; //imports event to allow keyboard inputs
import java.awt.event.KeyEvent; //helps computer locate which keybind was pressed

public class KeyInput extends KeyAdapter {
    private Paddles p1;
    private boolean up1 = false;
    private boolean down1 = false;
    private Paddles p2;
    private boolean up2 = false;
    private boolean down2 = false;

    //names the left and right paddles displayed on the screen
    public KeyInput(Paddles pd1, Paddles pd2) {
        p1 = pd1;
        p2 = pd2;
    }

    //method for when the key is pressed downward
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP){
            p2.switchDirection(-1);
            up2 = true;
        }
        if (key == KeyEvent.VK_DOWN){
            p2.switchDirection(1);
            down2 = true;
        }

        if (key == KeyEvent.VK_W){
            p1.switchDirection(-1);
            up1 = true;
        }
        if (key == KeyEvent.VK_S){
            p1.switchDirection(1);
            down2 = true;

        }
    }

    //method for when the key is released
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        //up and down arrow keys are release
        if (key == KeyEvent.VK_UP){
            up2 = false;
        }
        if (key == KeyEvent.VK_DOWN){
            down2 = false;
        }

        //w and s keys are released
        if (key == KeyEvent.VK_W){
            up1 = false;
        }
        if (key == KeyEvent.VK_S){
            down2 = false;
        }

        //stops the paddle from moving further when the key is released
        if (!up1 && !down1)
            p1.stop();
        if (!up2 && !down2)
            p2.stop();
    }
}
