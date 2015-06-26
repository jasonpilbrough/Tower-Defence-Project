package bullets;

import java.awt.Toolkit;
import monsters.Monster;
import core.GameObject;
import core.GamePanel;

public class Bullet extends GameObject {
	
	private Monster target;
	private double bulletDamage;
	GamePanel gamePanel;
	
	

	public Bullet(double posX, double posY, double direction, double radius,
			Monster target, double bulletDamage, GamePanel gamepanel ) {
		super(posX, posY, direction, radius);
		setImage(Toolkit.getDefaultToolkit().getImage("images/redBullet.png"));
		this.target = target;
		this.bulletDamage = bulletDamage;
		this.gamePanel=gamepanel;
	}



	@Override
	public void update(int deltaTime) {
		setDirection(getAngle(target)+180);
		moveForward(500 * deltaTime / 1000.0);

		
		if(getDistance(target)<10){
    		target.setHealth(target.getHealth()-5);	
    		gamePanel.deleteGameObject(this);
    		
    	}
	}
	
	public double getBulletDamage() {
		return bulletDamage;
	}

	public void setBulletDamage(double bulletDamage) {
		this.bulletDamage = bulletDamage;
	}

	public Monster getTarget() {
		return target;
	}

	public void setTarget(Monster target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Bullet Target:   " + target ;

	}
}
