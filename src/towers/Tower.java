package towers;

import java.awt.Image;
import java.util.ArrayList;

import monsters.Monster;
import core.GameObject;
import core.GamePanel;

public class Tower extends GameObject {
	private double range, fireRate, damagePS , updateCount;
	private GamePanel gamepanel;

	

	public Tower(double posX, double posY, double radius, double range, double fireRate,
			double damagePS, Image image, GamePanel gamepanel) {
		super(posX, posY, 0, radius);
		setImage(image);
		this.range = range;
		this.fireRate = fireRate;
		this.damagePS = damagePS;
		this.gamepanel=gamepanel;
	}

	@Override
	public void update(int deltaTime) {
		
		ArrayList<Monster> monsters = gamepanel.getMonsters();
		
		for (Monster monster : monsters) {
			if(getDistance(monster)<range && updateCount++%fireRate==0){
				//gamepanel.addGameObject(go); ADD BULLET
			}
		}
		
	}
	
	@Override
    public String toString() {
        return "Tower: " + "Xpos: " + getPosX() + " Ypos" + getPosY() + " Range: " + range;
	}
	
	
	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public double getFireRate() {
		return fireRate;
	}

	public void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}

	public double getDamagePS() {
		return damagePS;
	}

	public void setDamagePS(double damagePS) {
		this.damagePS = damagePS;
	}
	
	
	
	

}
