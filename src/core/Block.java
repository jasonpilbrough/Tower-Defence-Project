package core;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;

import towers.Tower;

public class Block {
	private boolean traversable;
	private Tower tower;
	private int terrain;
	private static int width,height;
	private int row,col;
	private boolean[] borderOrientation = new boolean[4];
	
	public Block(boolean traversable, int terrain,int row,int col) {
        this.traversable = traversable;
        this.terrain = terrain;
        this.col=col;
        this.row=row;  

    }
	
	 public double getPosX() {
  		return col*width+width/2;
  	}

  	public double getPosY() {
  		return row*height+height/2;
  	}
  	
  	public boolean placeTower(Tower t){
        if((!traversable)&&tower==null){  
            tower=t;
            return true;
        }else{
            return false;
        }
    }
  
	
	public void paint(Graphics g2,int startBlockFaceDirection,int endBlockFaceDirection){
		Graphics2D g =(Graphics2D)g2;
		
		if(terrain==0){
			g.setColor(new Color(25, 70, 66));
            g.fillRect(col*width, row*height, width, height);
			g.setColor(new Color(13,85,83));
            g.drawRect(col*width, row*height, width, height);
            
        }else {
          
			if(terrain==1){
				g.setColor(new Color(0,35,35));
	            g.fillRect(col*width, row*height, width, height);
	            g.setColor(Color.white);
			}
			else if(terrain ==2){
				g.translate(width*col+width/2, height*row+height/2);
	            g.rotate(-Math.toRadians(startBlockFaceDirection));
	            
	            Image i = createGradientImage(width, height, Color.GREEN,new Color(0,35,35));
	            int h = i.getHeight(null);
	            int w = i.getWidth(null);
	            
	            g.drawImage(i,-w+width/2, -h+height/2,null);
	            g.rotate(Math.toRadians(startBlockFaceDirection));
	            g.translate(-(width*col+width/2), -(height*row+height/2));
	            g.setColor(Color.white);
			}
			else if(terrain==3){
	        	g.translate(width*col+width/2, height*row+height/2);
	            g.rotate(-Math.toRadians(endBlockFaceDirection));
	            
	            Image i = createGradientImage(width, height, Color.RED,new Color(0,35,35));
	            int h = i.getHeight(null);
	            int w = i.getWidth(null);
	            
	            g.drawImage(i,-w+width/2, -h+height/2,null);
	            g.rotate(Math.toRadians(endBlockFaceDirection));
	            g.translate(-(width*col+width/2), -(height*row+height/2));
	            g.setColor(Color.white);
			}
			
			//down
            if(!borderOrientation[0]){
           	 g.drawLine(col*width, row*height+height-1, col*width+width, row*height+height-1);
            }
            //right
            if(!borderOrientation[1]){
           	 g.drawLine(col*width+width-1, row*height, col*width+width-1, row*height+height);
            }
            if(!borderOrientation[2]){
           	 g.drawLine(col*width, row*height, col*width+width, row*height);
            }
            if(!borderOrientation[3]){
           	 g.drawLine(col*width, row*height, col*width, row*height+height);
            }
       }
  
	}
	
    public static BufferedImage createGradientImage(int width, int height, Color gradient1,
  	      Color gradient2) {

  	    BufferedImage gradientImage = createCompatibleImage(width, height);
  	    GradientPaint gradient = new GradientPaint(0, 0, gradient1, 0, height, gradient2, false);
  	    Graphics2D g2 = (Graphics2D) gradientImage.getGraphics();
  	    g2.setPaint(gradient);
  	    g2.fillRect(0, 0, width, height);
  	    g2.dispose();

  	    return gradientImage;
  	  }
  
  private static BufferedImage createCompatibleImage(int width, int height) {

      return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
          .getDefaultConfiguration().createCompatibleImage(width, height);

    }

  @Override
  public String toString() {
      return "row=" + row + ", col=" + col ;
  }
	
	
	

	public boolean isTraversable() {
		return traversable;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Block.width = width;
	}

	public static int getHeight() {
		return height;
	}
	
	public static void setHeight(int height) {
		Block.height = height;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Tower getTower() {
		return tower;
	}

	public void setBorderOrientation(boolean[] borderOrientation) {
		this.borderOrientation = borderOrientation;
	}

	public int getTerrain() {
		return terrain;
	}
	
	
	

  	
}
