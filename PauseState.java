package collisiontest;

import static collisiontest.GamePanel.SCALE;
import static collisiontest.GameStateManager.GAME;
import static collisiontest.Grid.*;
import static collisiontest.PlayState.gm;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class PauseState extends GameState{

    public PauseState(GameStateManager gsm)
    {
        super(gsm);
    }
    
    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
            
            //HUD background.
            g.setColor(Color.WHITE);
            g.fillRect(400,0,Grid.WIDTH * SCALE,Grid.HEIGHT * SCALE);
            
            //HUD FONT STUFF
            g.setColor(Color.BLACK);
            g.setFont(new Font("Consolas",Font.PLAIN,45));
            g.drawString("PAUSED!",(WIDTH * SCALE) + WIDTH/2,HEIGHT/2 * SCALE);
            g.drawString("Score: " + gm.getScore(), (WIDTH * SCALE) + WIDTH/2,(HEIGHT/2 * SCALE) + 75 );
    }

    @Override
    public void keyPressed(int key) { 
        
        switch(key)
        {
            case KeyEvent.VK_P:
                gsm.setState(GAME);
                GameStateManager.states2.pop();
                GameStateManager.states2.peek();
                break;
        }
    }

    @Override
    public void keyReleased(int key) {
    }
    
}
