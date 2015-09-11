package collisiontest;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements KeyListener,Runnable{
       
    //Tetris board
    /*
        10 cells wide
        22 cells long
    */
    
    //screen size
    public static final int WIDTH = 400;
    public static final int HEIGHT = 440;
    public static final int SCALE = 2;    
    
    //timer and getting the image of the game stuff.
    private Thread thread;
    private volatile boolean running;
    private BufferedImage image;
    private Graphics2D g;
    
    private int FPS = 30;
    private int targetTime = 1000/FPS;  
        
    //GAME STATE MANAGER
    private GameStateManager gsm;
    
    public GamePanel()
    {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }
   
    @Override
    public void addNotify()
    {
        super.addNotify();
        addKeyListener(this);
        if(thread == null)
        {
            thread = new Thread(this);
            thread.start();
        }
    }
    
    @Override
    public void run()
    {
        init();
        long startTime;
        long urdTime;
        long waitTime;
        
        //timer code
        while(running)
        {                      
            startTime = System.nanoTime();
            
            update();
            render();
            draw();
            
            urdTime = (System.nanoTime() - startTime)/1000000;
            waitTime = Math.abs(targetTime - urdTime);
            
            
            //all time measured in miliseconds.            
         /*   System.out.println("Target time: " + targetTime);
            System.out.println("Elapsed time: " + urdTime);
            System.out.println("Wait time: " + waitTime);
           */ 
            
            //thread.sleep(waittime) gives time for the computer to process its updates
            //the waittime is measured in miliseconds.
            try
            {
                thread.sleep(waitTime);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    private void init()
    {
        running = true;
        image = new BufferedImage(WIDTH * SCALE,HEIGHT * SCALE,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D)image.getGraphics();
        
        //game state manager holds and manages all game variables and objects.
        gsm = new GameStateManager();
    }
    
    private void update()
    {
        gsm.update();
    }
    
    private void render()
    {   
        gsm.draw(g);
    }
    
    private void draw()
    {        
        Graphics2D g2 = (Graphics2D)this.getGraphics();
        g2.drawImage(image,0,0,null);
        g2.dispose();
    }
    
    @Override
    public void keyTyped(KeyEvent k)
    {      
    }
    
    @Override
    public void keyPressed(KeyEvent k)
    {
       // System.out.println("Key pressed: " + k.getKeyChar());
        gsm.keyPressed(k.getKeyCode());
    }
    
    @Override
    public void keyReleased(KeyEvent k)
    {
        gsm.keyReleased(k.getKeyCode());
    }
    
    
}
