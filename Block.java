package collisiontest;

import java.awt.*;

public class Block {
    
    //properties
    private final int length;
    private Color color;
    private int row,col;
    private boolean landed;
    private int m;
    private int dc;
    private int delay;
    //for the menu
    private int x;
    private int y;
    
    //controls
    private boolean left;
    private boolean right;
    
    private long startTime = System.currentTimeMillis();
    
    //default block constructor call 
    public Block()
    {
        //39 length.
        length = ( ( (Grid.WIDTH*GamePanel.SCALE) / 20) * 2) - 1;
        color = Color.CYAN;
        x = (Grid.WIDTH * GamePanel.SCALE)/2 + 100;
        y = 25;
        m = 1;
        delay = 30;
        landed = false;  
    }
    //used for the fall method in Grid.
    public Block(int r,int c,Color color)
    {
        //39 length.
        length = ( ( (Grid.WIDTH*GamePanel.SCALE) / 20) * 2) - 1;
        m = 1;
        dc = 0;
        delay = 30;
        landed = false;  
        row = r;
        col = c;
        this.color = color;        
    }
    
    //gui
    public Block(int x,int y)
    {
         length = ( ( (Grid.WIDTH*GamePanel.SCALE) / 25) * 2) - 1;
         m = 0;
         setVector(x,y);       
    }
    
    public void move()
    {
        dc = 0;
        m = 0;
        
        if(!hasLanded())
        {
            if(System.currentTimeMillis() - startTime >= 500)
            {
                startTime+= 500;
                m = 1;
                delay = 10;
            }
            if(delay%2 == 0)
            {
                if(left)
                {//&& 0 <= col - 1 && Grid.grid[row][col-1] == Grid.A)
                  dc = -1;
                //  if(col - 1 >= 0 && Grid.grid[row][col-1] == Grid.B)
                  //    dc = 1;
                }
                else if(right)
                {// && Grid.colSize > col + 1 && Grid.grid[row][col+1] == Grid.A)
                  dc = 1;
                  //if(Grid.colSize > col + 1 && Grid.grid[row][col+1] == Grid.B)
                    //  dc = -1;
                }

            }

                setRow(row + m);    
                setCol(col + dc);
        } 
        
        delay--;
    }
   
    //might need to split up this method.
    public void checkBounds()
    {
      //boundaries that keep block within game window
       if(row + 1 >= Grid.rowSize)
       {
           m = 0;
           Grid.grid[row][col] = Grid.B;
           landed = true;
       }
       //keeps the block within bounds for sides.
       if(col <= 0)
       {
         col = 0;  
         dc = 0;
       }
       if(col + 1 >= Grid.colSize)
       {   
           dc = 0;
           col = Grid.colSize - 1;     
       }
      //checks to see if block is inside grid and checks to see if the grid has a block currently existing in the matrix.
       if(row + 1 < Grid.rowSize && (Grid.grid[row+1][col] == Grid.B || Grid.grid[row][col] == Grid.B))
       {
         landed = true;
         m = 0;
         Grid.grid[row][col] = Grid.B;
       }
             
    }
    
    public void drawBlock(Graphics2D g)
    {
        //outerblock
        g.setColor(color);
        g.fillRect(col * 40, row * 40, length, length);
        //innerblock
        g.setColor(color.darker());
        g.fillRect(col * 40 + 2, row * 40 + 1,length - 4,length - 4);
    }
    
    public void drawBlock(Graphics2D g, int x, int y)
    {
        g.setColor(color);
        //outerblock
        g.fillRect(x, y, length, length);
        //innerblock
        g.setColor(color.darker());
        g.fillRect(x + 2,y + 2,length - 4,length - 4);
    }
    
    public void setRow(int r)
    {
        row = r;
    }
    
    public void setCol(int c)
    {
        col = c;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public void setVector(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setLeft(boolean b)
    {
            left = b;
    }
    public void setRight(boolean b)
    {
            right = b;
    }
    
    public void setDC(int c)
    {
        dc = c;
    }
    
    public boolean hasLanded()
    {
        return landed;
    }
    
    public void setLand(boolean b)
    {
        landed = b;
    }
    
    public boolean getLeft()
    {
        return left;
    }
    
    public boolean getRight()
    {
        return right;
    }
    
    public void setColor(Color c)
    {
        color = c;
    }
   
    public Color getColor()
    {
        return color;
    }
    
    public int getM()
    {
        return m;
    }
    
    public void setM(int x)
    {
        m = x;
    }
}
