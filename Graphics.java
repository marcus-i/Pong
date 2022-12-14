package PongGame; //group of relate classes
import javax.swing.JFrame; //an api for graphical interface and provides the window on the screen

/*creates a graphics constructor
is able to create the game screen window with the title

*/
public class Graphics {
    public Graphics(String title , Pong game) {
        JFrame window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); //makes sure the user cannot resize the window
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null); //sets window in the center of the screen
        window.setVisible(true); //displays the window on the screen
        game.start();
    }
}
