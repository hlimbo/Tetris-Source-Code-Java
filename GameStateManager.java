package collisiontest;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class GameStateManager {
    
    
    public static ArrayList<GameState> states;
    public static Stack<GameState> states2;
    private int currentState;
    
    public static final int MENU = 0;
    public static final int GAME = 1;
    public static final int END = 2;
    public static final int PAUSE = 3;
    
    public GameStateManager()
    {
      //  states = new ArrayList<>();
        states2 = new Stack<>();
        states2.push(new MenuState(this));
        currentState = MENU;
      //  initStates();
    }
    
    public void initStates()
    {
        //for better memory management
        states.clear();
        
        //ADD YOUR GAMESTATES HERE.
        states.add(new MenuState(this));
       // states.add(new PlayState(this));
        states.add(new GameOverState(this));
        //states.add(new PauseState(this));
    }
    
    public void setState(int state)
    {
        currentState = state;
    }
    
    public void update()
    {
        try
        {
      //  states.get(currentState).update();
        states2.peek().update();
        }
        catch(EmptyStackException e)
        {
            e.printStackTrace();
            states2.push(new MenuState(this));
        }
        
    }
    
    public void draw(Graphics2D g)
    {
      //  states.get(currentState).draw(g);
        states2.peek().draw(g);
    }
    
    public void keyPressed(int k)
    {
      //  states.get(currentState).keyPressed(k);
        states2.peek().keyPressed(k);
    }
    
    public void keyReleased(int k)
    {
        //states.get(currentState).keyReleased(k);
        states2.peek().keyReleased(k);
    }
}
