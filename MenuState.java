package collisiontest;

import static collisiontest.GamePanel.HEIGHT;
import static collisiontest.GamePanel.SCALE;
import static collisiontest.GamePanel.WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{

    private String[] selection;
    private int index;
    
    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        init();
    }
    
    public void init()
    {
        selection = null;
        selection = new String[]{"Play","Quit"};
        index = 0;
    }
    
    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH * SCALE,HEIGHT * SCALE);
        g.setColor(Color.black);
        g.setFont(new Font("Consolas",Font.PLAIN,45));
        g.drawString("Tetris",(WIDTH * SCALE) / 2 - 100, (HEIGHT * SCALE) / 2 - 100);
        g.setFont(new Font("Consolas",Font.PLAIN,36));
        
        //selecting
        for(int i = 0;i < selection.length;i++)
        {
            if(i == index)
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLACK);
            
            g.drawString(selection[i],(WIDTH * SCALE) / 2 - 100, (HEIGHT * SCALE) / 2 + (50 * i));
        }
        
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
                    gsm.setState(GameStateManager.GAME);
                    GameStateManager.states2.pop();
                    GameStateManager.states2.push(new PlayState(gsm));
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
