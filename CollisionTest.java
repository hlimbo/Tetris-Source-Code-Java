//Author hlimbo
//Date 9-10-15
//Language Java

//GAME LEAK IN GAME.
//potential fix for this could be to use stack based data structure
// that handles the gamestates efficiently.


package collisiontest;

import javax.swing.JFrame;

public class CollisionTest {

    public static void main(String[] args) {
        JFrame window = new JFrame("The Tetris");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new GamePanel());
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
    
}
