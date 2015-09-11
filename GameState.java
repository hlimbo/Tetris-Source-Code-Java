package collisiontest;

import java.awt.Graphics2D;

public abstract class GameState {
    
    protected GameStateManager gsm;
    
    public GameState(GameStateManager manager)
    {
        gsm = manager;
    }
    
    public abstract void update();
    public abstract void draw(Graphics2D g);
    public abstract void keyPressed(int key);
    public abstract void keyReleased(int key);
}
