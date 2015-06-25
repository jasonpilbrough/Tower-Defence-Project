package monsters;

import java.awt.Color;
import java.awt.Graphics;

import core.Block;
import core.GameObject;
import core.GamePanel;

public class Monster extends GameObject {
	
	private Block[] path;
	private Block currentDestination;
	private int destinationCount;
	private double speed, health;
	private GamePanel gamepanel;
	
	

	public Monster(double radius, Block[] path, double speed, double health, 
			GamePanel gamepanel) {
		super(path[0].getPosX(), path[0].getPosY(), 0, radius);
		this.path = path;
		this.speed = speed;
		this.health = health;
		this.gamepanel=gamepanel;
		
		currentDestination = path[destinationCount++];
	}



	@Override
	public void update(int deltaTime) {
		if(health<0){
			gamepanel.deleteGameObject(this);
		}
		
		try {
			if(getDistance(currentDestination.getPosX(), currentDestination.getPosY())<(speed*deltaTime/1000)){
				currentDestination=path[destinationCount++];					
				setDirection(getAngle(currentDestination.getPosX(), currentDestination.getPosY())+180);
			}
			moveForward(speed * deltaTime / 1000.0);
		} catch (Exception e) {
			gamepanel.deleteGameObject(this);
            
		}  

	}
	
    @Override
	public void paint(Graphics g) {		
    	int startX = (int) (getPosX()-getRadius());
    	int startY = (int) (getPosY()-getRadius()*2);
    	int length = (int)(2*getRadius()*(health/100));
    	int totalLength=(int)(getRadius()*2);
    	
    	g.setColor(Color.green);
    	g.fillRect( startX,startY,length,3);
    	g.setColor(Color.red);
    	g.fillRect( startX+length,startY,totalLength-length,3);
		super.paint(g); 
	}
    
    @Override
    public String toString() {
        return "Monster :" + "Xpos: " + getPosX() + " Ypos" + getPosY();
    }
    
    public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

}
