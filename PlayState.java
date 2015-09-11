//Author hlimbo 
//special thanks to ForeignGuyMike and his java game tutorials on youtube 
//for the gamemanager,gamestate,gamepanel classes.
//Date 9-10-15
//Language Java

package collisiontest;

import static collisiontest.GamePanel.HEIGHT;
import static collisiontest.GamePanel.SCALE;
import static collisiontest.GamePanel.WIDTH;
import static collisiontest.GameStateManager.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class PlayState extends GameState{

     //game fields   
    public static Grid gm;//static needed for pause state to display score;
    private Block[][] landed;
    private Shape current;
    private Shape nextShape;
    private int selection;  
    private boolean nextShapeSelected;
    private Random r1;
    
    public PlayState(GameStateManager gsm)
    {
        super(gsm);        
        init();
    }
    
    private void init()
    {
      //  delete();
        gm = new Grid();
        landed = new Block[Grid.rowSize][Grid.colSize];
        current = new Shape(true);
        nextShape = new Shape(false);
        nextShapeSelected = false;
        r1 = new Random();
        selection = r1.nextInt(7) + 1;
        current.addShape(selection);
    }
    
    //dereferences all instantiated objects to prevent memory leak
    private void delete()
    {
        gm = null;
        for(int r = 0;r < Grid.rowSize;r++)
        {
            for(int c = 0;c < Grid.colSize;c++)
            {
                landed[r][c] = null;
            }
        }
        landed = null;
        current = null;
        nextShape = null;
        r1 = null;
    }
    
    @Override
    public void update() {
     
        current.setPrevLoc();
        current.rotate();
        current.move();
        current.checkBounds();
        
        //next shape placing
        if(!current.hasLanded() && !nextShapeSelected)
        {
            selection = r1.nextInt(7) + 1;
            nextShape = null;
            nextShape = new Shape(false);
            nextShape.addShape(selection);
            nextShapeSelected = true;
        }
        //problematic - solved changed public static blocks to private blocks
        if(current.hasLanded())
        {
            //adding the landed blocks to a block landed list.
            for(int i = 0;i < 4;i++)
            {
                int row = current.getBlock(i).getRow();
                int col = current.getBlock(i).getCol();
                Color color = current.getBlock(0).getColor();
                landed[row][col] = new Block(row,col,color);

            }
           current = null; 
           current = new Shape(true);
           current.addShape(selection);
           nextShapeSelected = false;
        }

       //if a line or more is cleared.
       //update score; 
       gm.checkGrid();
       gm.clearLine(landed);
       gm.fall(landed);

        //for debugging purposes.
     //   gm.printMap();

        //gameover check.
        int firstrow = 0;
        for(int c = 0;c < Grid.colSize;c++)
        {
            if(Grid.grid[firstrow][c] == Grid.B)
            {  
                GameOverState.finalscore = gm.getScore();
                //remove this current instance of PlayState off the stack.
                GameStateManager.states2.pop();
                GameStateManager.states2.push(new GameOverState(gsm));
                gsm.setState(END);
            }
        }
         
    }

    @Override
    public void draw(Graphics2D g) {
      
            //DRAW GRID
            g.setColor(Color.BLACK);
            g.fillRect(0,0, WIDTH * SCALE,HEIGHT * SCALE);
            gm.drawGrid(g);
            
            //HUD background.
            g.setColor(Color.WHITE);
            g.fillRect(400,0,Grid.WIDTH * SCALE,Grid.HEIGHT * SCALE);
            
            //HUD FONT STUFF
            g.setColor(Color.BLACK);
            g.setFont(new Font("Consolas",Font.BOLD,45));
            g.drawString("TETRIS",(Grid.WIDTH * SCALE) + Grid.WIDTH/4,100);
            g.setFont(new Font("Consolas",Font.PLAIN,36));
            g.drawString("Score: " + gm.getScore(), (Grid.WIDTH * SCALE) + Grid.WIDTH/4,135);
            
            //HOW TO PLAY STUFF
            g.setColor(Color.BLACK);
            g.setFont(new Font("Consolas",Font.BOLD,36));
            g.drawString("CONTROLS:",(Grid.WIDTH * SCALE) + Grid.WIDTH/4,Grid.HEIGHT/2 * SCALE);
            g.setFont(new Font("Consolas",Font.PLAIN,26));
            g.drawString("Left Key - MOVE left",(Grid.WIDTH * SCALE) + Grid.WIDTH/4,Grid.HEIGHT/2 * SCALE + 50);
            g.drawString("Right Key - MOVE right",(Grid.WIDTH * SCALE) + Grid.WIDTH/4,Grid.HEIGHT/2 * SCALE + 100);
            g.drawString("UP Key - ROTATE",(Grid.WIDTH * SCALE) + Grid.WIDTH/4,Grid.HEIGHT/2 * SCALE + 150);
            g.drawString("P Key - PAUSE",(Grid.WIDTH * SCALE) + Grid.WIDTH/4,Grid.HEIGHT/2 * SCALE + 200);
            
            //NEXT SHAPE BOX
            g.setColor(new Color(212,232,173));
            g.fillRect((Grid.WIDTH * SCALE) + GamePanel.WIDTH/2 + 45,25,150,150);
            g.setColor(Color.BLACK);
            g.drawRect((Grid.WIDTH * SCALE) + GamePanel.WIDTH/2 + 45,25,150,150);
            nextShape.drawShape(g);
            
            //SHAPES AND BLOCKS
            current.drawShape(g);
            
            //drawing blocks already landed on screen.
            for(int i = 0;i < landed.length;i++)
            {
                for(int j = 0;j < landed[i].length;j++)
                {
                    if(landed[i][j] != null)
                        landed[i][j].drawBlock(g);
                }
            }
    }
    
    
    //CONTROLS
    @Override
    public void keyPressed(int key) {
       
       switch(key)
       {   
           case KeyEvent.VK_P:
               gsm.setState(PAUSE);
               GameStateManager.states2.push(new PauseState(gsm));
               break;
           case KeyEvent.VK_LEFT:              
               current.setLeft(true);
               break;
           case KeyEvent.VK_RIGHT:
               current.setRight(true);
               break;
           case KeyEvent.VK_UP:
               current.isRotatable(true);
               break;             
       }
    }
    
    public void keyTyped(char key)
    {
        switch(key)
        {
            case 'r':
                current.isRotatable(true);
                break;
        }
    }

    @Override
    public void keyReleased(int key) {
        switch(key)
       {           
           case KeyEvent.VK_LEFT:              
               current.setLeft(false);
               break;
           case KeyEvent.VK_RIGHT:
               current.setRight(false);
               break;
           case KeyEvent.VK_UP:
               current.isRotatable(false);
               break;             
       }
    }
    
}
