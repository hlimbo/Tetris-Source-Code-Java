package collisiontest;

import static collisiontest.GamePanel.HEIGHT;
import static collisiontest.GamePanel.SCALE;
import static collisiontest.GamePanel.WIDTH;
import static collisiontest.GameStateManager.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GameOverState extends GameState{

    private String[] selection;
    private int index;
    public static String finalscore;
    
    public GameOverState(GameStateManager gsm)
    {
        super(gsm);
        init();
    }
    
    public void init()
    {
        selection = null;
        selection = new String[]{"Play Again","Quit"};
        index = 0;
    }
    
    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
             //draw the gameover screen here.
            g.setColor(Color.WHITE);
            g.fillRect(0, 0,WIDTH * SCALE, HEIGHT * SCALE);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Consolas",Font.PLAIN, 100));
            g.drawString("GAME OVER",WIDTH * SCALE/5,HEIGHT * SCALE/4);
            g.setFont(new Font("Consolas",Font.PLAIN,36));
            
            //selections
            for(int i = 0;i < selection.length;i++)
            {
                if(i == index)
                    g.setColor(Color.red);
                else
                    g.setColor(Color.black);
                
                g.drawString(selection[i],WIDTH * SCALE/5,HEIGHT * SCALE/4 + 100 + (50*i) );
            }
            
            //score output
            g.setFont(new Font("Consolas",Font.PLAIN,45));
            g.setColor(Color.BLACK);
            g.drawString("Your Score: " + finalscore, WIDTH * SCALE/4 + 100,HEIGHT * SCALE/2 - 50);
            
            //list font checker.
           /* GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String [] fonts = ge.getAvailableFontFamilyNames();
            
            for(String font : fonts)
            {
                System.out.println(font);
            }*/
    }

    @Override
    public void keyPressed(int key) {
        
        
         switch(key)
        {
            case KeyEvent.VK_UP:
                index--;
                if(index < 0)
                    index = 1;
                break;
            case KeyEvent.VK_DOWN:
                index++;
                if(index > 1)
                    index = 0;
                break;
            case KeyEvent.VK_ENTER:
                if(index == 0)
                {  
                 //   gsm.initStates();
                    gsm.setState(MENU);
                    GameStateManager.states2.pop();
                    GameStateManager.states2.push(new MenuState(gsm));
                }
                else
                    System.exit(0);
                break;
        }
    }

    @Override
    public void keyReleased(int key) {
     
    }
    
}
