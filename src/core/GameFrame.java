package core;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameFrame extends JFrame implements ActionListener{
	private GamePanel gp = new GamePanel();
	private Timer t = new Timer(40,this);
	private static int deltaTime;
	
	
	
	public GameFrame() {
		deltaTime=40;
		t.start();
		
	}
		
	public static void main(String[] args) {
    	GameFrame gf = new GameFrame();
    	
    	gf.add(gf.gp,BorderLayout.CENTER);
    	gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gf.setVisible(true);
       

    }
	



	@Override
	public void actionPerformed(ActionEvent arg0) {
		gp.update(deltaTime);
		gp.repaint();
		
	}

}
