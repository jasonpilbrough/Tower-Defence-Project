package core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	
	public void addGameObject(GameObject go){
		gameObjects.add(go);
	}
	
	public void deleteGameObject(GameObject go){
		
		if(gameObjects.contains(go)){
			gameObjects.remove(gameObjects.indexOf(go));
		}else{
			JOptionPane.showMessageDialog(null, "Could not delete object");
		}
	}
	
	public int getGameObjectArrayLength(){
		return gameObjects.size();
	}
	
	public void paint(Graphics g){
		g.setColor(new Color(25, 70, 66));
		//g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
		for (GameObject gameObject : gameObjects) {
			gameObject.paint(g);
		}
		
		
	}
	
	public void update(int deltaTime){
		for (GameObject gameObject : gameObjects) {
			gameObject.update(deltaTime);
		}
	}
	
	

}
