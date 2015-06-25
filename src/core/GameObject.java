package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public abstract class GameObject {
	
	private double posX,posY,direction,radius;
	private Image image;
	
	public GameObject(double posX, double posY, double direction, double radius) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.radius = radius;
	}

	public void moveForward(double distance) {
        posX += distance * Math.sin(Math.toRadians(direction));
        posY += distance * Math.cos(Math.toRadians(direction));
    }
	
	public double getAngle(GameObject go){
    	double result = Math.toDegrees(Math.atan2(go.posX - this.getPosX(), (this.getPosY() - go.posY)));
    	return -result;
    }
	
	public double getAngle(double posX,double posY){
    	double result = Math.toDegrees(Math.atan2(posX - this.getPosX(), (this.getPosY() - posY)));
    	
    	return -result;
    }
    
    public double getDistance(GameObject go){
    	double xdiff = this.posX-go.posX;
    	double ydiff = this.posY-go.posY;
    	double result = Math.sqrt((xdiff*xdiff)+(ydiff*ydiff));
    	
    	return result;
    }
    
    public double getDistance(double posX,double posY){
    	double xdiff = this.posX-posX;
    	double ydiff = this.posY-posY;
    	double result = Math.sqrt((xdiff*xdiff)+(ydiff*ydiff));
    	
    	return result;
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 =(Graphics2D)g;
         g2.translate(posX, posY);
         g2.rotate(-Math.toRadians(direction));
         g2.drawImage(image, (int) (- radius), (int) (- radius), (int) (2 * radius), (int) (2 * radius), null);
         g2.rotate(Math.toRadians(direction));
         g2.translate(-posX, -posY);
     }

     public abstract void update(int deltaTime);


	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	

}
