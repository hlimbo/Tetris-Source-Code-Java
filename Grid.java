package collisiontest;

import java.awt.Color;
import java.awt.Graphics2D;

public class Grid {
    
    //grid used for the board.
    public static int[][] grid;
    
       //Tetris board
    /*
        10 cells wide
        22 cells long
    */
    
    
    /*
        A = NOT BLOCKED
        B = BLOCKED;
    */
    
    public static final int A = 0;
    public static final int B = 1;
    
    public static final int HEIGHT = 440;
    public static final int WIDTH = 200;
    public static final int rowSize = (HEIGHT * GamePanel.SCALE)/40;
    public static final int colSize = (WIDTH * GamePanel.SCALE)/40;
    
    private int score;
    private int crow;
    private final Color color;
    private boolean isFilled;
    
    public Grid()
    {        
        //grayish blue.
        color = new Color(212,232,173);        
        grid = new int[rowSize][colSize];
        score = 0;
        generateGrid();
        
    }
    
    private void generateGrid()
    {
        for(int i = 0; i < rowSize;i++)
        {
            for(int j = 0; j < colSize;j++)
            {
                grid[i][j] = A; 
            }
        }
    }
        
    //Checks the grid to see if an array of blocks fully occupies a row.
    public void checkGrid()
    {
       crow = -1;
        for(int r = 0; r < grid.length; r++)
        {
            isFilled = true;
            for(int c = 0; c < grid[r].length;c++)
            {
                if(grid[r][c] == A)
                    isFilled = false;
            }
            
            if(isFilled)
            {
                crow = r;
                return;
            }
                
        }
       
    }
    
    //if isFilled is true, that means an entire horizontal line of blocks is filled on the board.
    //and is cleared by the followiing method.
    public void clearLine(Block[][] map)
    {
            
        if(isFilled)
        {
            for(int c = 0;c < grid[crow].length;c++)
                {
                    map[crow][c] = null;
                    grid[crow][c] = A;
                  
                }
           score+=10;
                
        }
    }
    
    //landed blocks above the previously cleared line of blocks will fall down exactly one row down
    public void fall(Block[][] map)
    {
        if(isFilled)
        {
            int urow = crow  - 1;
            for(int r = urow;r >= 0;r--)
            {
                for(int c = 0;c < grid[r].length;c++)
                {
                    if(map[r][c] != null)
                    {
                        grid[r][c] = A;
                        Color thecolor = map[r][c].getColor();
                        map[r][c] = null;
                        grid[r+1][c] = B;
                        map[r+1][c] = new Block(r+1,c,thecolor);

                    }
                }
            }
            
        }
    }
    
    public void drawGrid(Graphics2D g)
    {
        for(int i = 0;i < rowSize * 4;i++)
            for(int j = 0;j < colSize * 4;j++)
            {
                g.setColor(color);
                g.fillRect(i*40,j*40,39,39);
            }
    }
    
    public void insertBlock(Block b)
    {
        b.setRow(0);
        b.setCol(colSize/2);
    }
    
    public int getRowSize()
    {
        return rowSize;
    }
    
    public int getColSize()
    {
        return colSize;
    }
    
    public int getStatus(int row,int col)
    {
        return grid[row][col];
    }
    
    public void setStatus(int row,int col,int state)
    {
        grid[row][col] = state;
    }
    
    public String getScore()
    {
        return Integer.toString(score);
    }

    //prints grid map in console
    public void printMap()
    {
        System.out.println("Map");
        for(int i = 0;i < rowSize;i++)
        {    for(int g = 0;g < colSize;g++)
            {
                System.out.print(grid[i][g] + " ");
            }
            System.out.println();
        }
    }
}
