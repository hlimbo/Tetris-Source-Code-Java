package collisiontest;

import java.awt.*;

public class Shape {
   
   private Color color;
   private boolean land;
   private Block[] blocks; 
   private Block[] prev;   
   private int m;
   private int selection;
   private boolean onGrid;
   
   private int delay;
   private long startTime = System.currentTimeMillis();
   
   //controls
   private boolean left;
   private boolean right;
   private boolean rotate;
    
   public Shape(boolean set)
   {
       blocks = new Block[4];
       prev = new Block[4];
       onGrid = set;
       land = false;
       m = 1;
       delay = 4;
       
       //initialize blocks to avoid null pointer exception.
       if(onGrid)
       {
            for(int i = 0;i < blocks.length;i++)
            {
                blocks[i] = new Block();
                prev[i] = new Block(); 
            }
       }
       else
       {
           for(int i = 0;i < blocks.length;i++)
                blocks[i] = new Block((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,40);
       }
   }
    
   //adding shape into grid.
   public void addShape(int s)
   {
       selection = s;
       switch(s)
       {
           case 1: Line();
               break;
           case 2: Square();
               break;
           case 3: ZShape();
               break;
           case 4: SShape();
               break;
           case 5: TShape();
               break;
           case 6: LShape();
               break;
           case 7: ReverseLShape();
               break;
           default:
               System.out.println("No shape");
          
       }
   
   }
   
   public void deleteShape()
   {
       color = null;
       for(int i = 0;i < blocks.length;i++)
       {
           blocks[i] = null;
           prev[i] = null;
       }
       
       blocks = null;
       prev = null;
   }
   
   //////////////////SHAPE METHODS////////////////////////////
   public void Line()
   {
      setColor(Color.CYAN);
       if(onGrid)
      {
      for(int i = 0;i < blocks.length;i++)
      {
         
            //setting the initial location of line shape.
            blocks[i].setRow(i * 1);
            blocks[i].setCol(Grid.colSize/2);
          
      }
      }
      else
      {
         blocks[0].setVector(blocks[0].getX(),blocks[0].getY() + (0 * 30));
         blocks[1].setVector(blocks[1].getX(),blocks[1].getY() + (1 * 30));
         blocks[2].setVector(blocks[2].getX(),blocks[2].getY() + (2 * 30));
         blocks[3].setVector(blocks[3].getX(),blocks[3].getY() + (3 * 30));
      }
   }
   
   public void Square()
   {
       setColor(Color.yellow);
       
       if(onGrid)
       { //setting the initial location of square shape
            blocks[0].setRow(0);
            blocks[0].setCol((Grid.colSize/2) - 1);
            blocks[1].setRow(0);
            blocks[1].setCol(Grid.colSize/2);
            blocks[2].setRow(1);
            blocks[2].setCol((Grid.colSize/2) - 1);
            blocks[3].setRow(1);
            blocks[3].setCol(Grid.colSize/2);
       }
       else
       {
            blocks[0].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,40);
            blocks[1].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,70);
            blocks[2].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 135,40);
            blocks[3].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 135,70);
       }
  
   }
   
   public void TShape()
   {
       setColor(Color.MAGENTA);
       
       if(onGrid)
       {
       //setting the initial location of TShape;
       blocks[0].setRow(0);//middle top block
       blocks[0].setCol(Grid.colSize/2);
       blocks[1].setRow(1);
       blocks[1].setCol(Grid.colSize/2);
       blocks[2].setRow(1);
       blocks[2].setCol((Grid.colSize/2) - 1);
       blocks[3].setRow(1);
       blocks[3].setCol((Grid.colSize/2) + 1);
       }
       else
       {
       blocks[0].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,40);
       blocks[1].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,70);
       blocks[2].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 135,70);
       blocks[3].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 75,70);   
       }
   }
   
   public void LShape()
   {
       setColor(Color.BLUE);
       
       if(onGrid)
       {
       //setting the initial location of LShape.
       blocks[0].setRow(0);
       blocks[0].setCol(Grid.colSize/2);
       blocks[1].setRow(1);
       blocks[1].setCol(Grid.colSize/2);
       blocks[2].setRow(2);
       blocks[2].setCol(Grid.colSize/2);
       blocks[3].setRow(2);
       blocks[3].setCol((Grid.colSize/2) + 1);
       }
       else
       {
       blocks[0].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,40);
       blocks[1].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,70);
       blocks[2].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 135,100);
       blocks[3].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,100);
       }
   }
   
   public void ReverseLShape()
   {
       setColor(Color.ORANGE);
       
       if(onGrid)
       {
       //setting the initial location of ReverseLShape.
       blocks[0].setRow(0);
       blocks[0].setCol(Grid.colSize/2);
       blocks[1].setRow(1);
       blocks[1].setCol(Grid.colSize/2);
       blocks[2].setRow(2);
       blocks[2].setCol(Grid.colSize/2);
       blocks[3].setRow(2);
       blocks[3].setCol((Grid.colSize/2) - 1);
       }
       else
       {
        blocks[0].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,40);
        blocks[1].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,70);
        blocks[2].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,100);
        blocks[3].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 75,100);
        }
   }
   
   public void ZShape()
   {
       setColor(Color.GREEN);
       if(onGrid)
       {
       //setting the initial location of ZShape.
       blocks[0].setRow(1);
       blocks[0].setCol(Grid.colSize/2 + 1);
       blocks[1].setRow(0);
       blocks[1].setCol(Grid.colSize/2);
       blocks[2].setRow(1);
       blocks[2].setCol(Grid.colSize/2);
       blocks[3].setRow(0);
       blocks[3].setCol(Grid.colSize/2 - 1);
       }
       else
       {
        blocks[0].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,40);
        blocks[1].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,70);
        blocks[2].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 75,40);
        blocks[3].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 135,70);
       }
   }
   
   public void SShape()
   {
       setColor(Color.RED);
       if(onGrid)
       {
       //setting the initial location of SShape.
       blocks[0].setRow(0);
       blocks[0].setCol(Grid.colSize/2);
       blocks[1].setRow(1);
       blocks[1].setCol(Grid.colSize/2);
       blocks[2].setRow(0);
       blocks[2].setCol(Grid.colSize/2 + 1);
       blocks[3].setRow(1);
       blocks[3].setCol((Grid.colSize/2) - 1);
       }
       else
       {
        blocks[0].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,40);
        blocks[1].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 105,70);
        blocks[2].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 135,40);
        blocks[3].setVector((Grid.WIDTH * GamePanel.SCALE) + GamePanel.WIDTH/2 + 75,70);
       }
   }
   ///////////////////////////////////////////////////////////////////////////////////
   
   
   public void move()
   {
       for(Block b : blocks)
       {
           b.move(); 
       }
   }
   
   
   //side collision works.
   //PROBLEM - COLLISION WITH OTHER SHAPES side to side do not work properly.
   public void checkBounds()
   {   
       //fixes block overlap when moving the shape to the walls.
       if(this.getMaxCol() >= Grid.colSize || this.getMinCol() < 0)
       {
           for(int i = 0;i < blocks.length;i++)
           {
               blocks[i].setRow(prev[i].getRow());
               blocks[i].setCol(prev[i].getCol());
           }
       }
       
    //check if there is a shape to the left or right of shape --- WIP;
    /*    if(Grid.grid[this.getMaxRow()][this.getMaxCol()] == Grid.B)
        {
            for(int i = blocks.length - 1;i >= 0;i--)
            {
                Block block = blocks[i];
                block.setCol(prev[i].getCol());
                block.setRow(prev[i].getRow());

            }
                return;
        }
        
        if(Grid.grid[this.getMaxRow()][this.getMinCol()] == Grid.B)
        {
            for(int i = blocks.length - 1;i >= 0;i--)
            {
                Block block = blocks[i];
                block.setCol(prev[i].getCol());
                block.setRow(prev[i].getRow());

            }
                return;
        }
      */                   
       //using block's checkBounds method for almost every type of collision except wall collision.
       for(Block b: blocks)
       {
           b.checkBounds();              
           
           //checks to see if one of the blocks has landed
           //in order to prevent the shape's structure from changing.
           if(b.hasLanded())
           {
                land = true;                
                for(Block block:blocks)
                {
                    block.setM(0);
                    block.setLand(land);
                    Grid.grid[block.getRow()][block.getCol()] = Grid.B;
                }
           }
       }
       
   }
   
      //sets the previous location of the shape.
   //needed for side collision.
   public void setPrevLoc()
   {      
       for(int i = 0;i < prev.length;i++)
       {                   
           prev[i].setRow(blocks[i].getRow());
           prev[i].setCol(blocks[i].getCol());
       
       }
   }
   
   //LINEAR ALGEBRA APP FOR ROTATIONS
   public void rotate()
   {  
       //Rotation checks here if shape is on the borders of the screen.
       //if shapes are on the top, bottom, right, or left most side of the screen
       //rotation will not occur.
       
       if(this.getMaxCol() + 1 >= Grid.colSize)
           return;
       if(this.getMinCol() <= 0)
           return;
       if(this.getMinRow() <= 0)
           return;
       if(this.getMaxRow() + 1 >= Grid.rowSize)
           return;        
       
      if(rotate && !(left || right  || selection == 2))
      {
         if(System.currentTimeMillis() - startTime >= 400)
         {
            startTime+= 400; 
             
          //placeholders.
          Block[] temp = new Block[4];
          for(int i = 0; i < temp.length;i++)
              temp[i] = new Block();
          
          //origin pts. - the spot to rotate about.
          int ox = blocks[1].getCol();
          int oy = blocks[1].getRow();
          
          //step 1. subtract from origin.
          for(int i = 0;i < 4;i++)
          {
              Block b = blocks[i];
              Block t = temp[i];
              t.setCol(b.getCol() - ox);
              t.setRow(b.getRow() - oy);
          }
          
          //step 2 multiply using rotation matrix.
            /*
             * [0 -1]
             * [1  0] 
             */
          
          for(int i = 0;i < 4;i++)
          {
              Block t = temp[i];
              int tempx = t.getCol();
              int tempy = t.getRow();
              
              t.setCol((tempx * 0 ) - (tempy * 1 ));
              t.setRow((tempx * 1 ) - (tempy * 0 ));
          }
          
          //step 3. add origin back.
          
          for(int i = 0;i < 4; i++)
          {
              Block b = blocks[i];
              Block t = temp[i];
              
              b.setCol(t.getCol() + ox);
              b.setRow(t.getRow() + oy);
          }
           delay = 2;
           
           //reduce amount of memory being taken up by ram
           for(int i = 0;i < temp.length;i++)
           {
               temp[i] = null;
           }
      }
               delay--;
              
                   
      }
      
   }
   
   public Block getBlock(int blocknumber)
   {
       return blocks[blocknumber];
   }
   
   //max height and min height of shape.
   public int getMaxRow()
   {
      int max = blocks[0].getRow();
       
       for(Block b : blocks)
       {
           if(max < b.getRow())
               max = b.getRow();
       }
       
       return max;
   }
   
   public int getMinRow()
   {
       int min = blocks[0].getRow();
       
       for(Block b : blocks)
       {
           if(min > b.getRow())
               min  = b.getRow();
       }
       
       return min;
   }
   
   
   //max width and min width of shape.
   public int getMaxCol()
   {
       int max = blocks[0].getCol();
       
       for(Block b: blocks)
       {
          if(max < b.getCol())
              max = b.getCol();
       }
       
       return max;
   }
   
   public int getMinCol()
   {
       int min = blocks[0].getCol();
       
       for(Block b: blocks)
       {
           if(min > b.getCol())
               min = b.getCol();
       }
       
       return min;
   }
   
   public boolean hasLanded()
   {
       return land;
   }
   
   public void setLand(boolean b)
   {
     land = b;   
   }
   
   public void setColor(Color c)
   {
       color = c;
       for(Block block:blocks)
           block.setColor(color);
       
   }
   
   public void drawShape(Graphics2D g)
   {
       if(onGrid)
       {
        for(int i = 0;i < 4;i++)
            blocks[i].drawBlock(g);
       }
       else
       {
           for(int i = 0;i < 4;i++)
               blocks[i].drawBlock(g,blocks[i].getX(),blocks[i].getY());
       }
      
   }
      
   //controls////////////////////////////
   public void setLeft(boolean b)
   {              
       left = b;
       for(Block block : blocks)
           block.setLeft(left);
   }
   
   public void setRight(boolean b)
   {
       right = b;
       for(Block block : blocks)
           block.setRight(right);
   }
   
   public void isRotatable(boolean b)
   {       
       rotate = b;
   }
   
   public boolean getRotate()
   {
       return rotate;
   }
           
   
    
}
