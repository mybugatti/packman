package game;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Packman {

	public static void main(String[] args) {
		Frame app = new Frame("Pac-Man");
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent w){
				System.exit(0);
			}
		});
		
		
		PackModel world = new PackModel();
		PackView screen = new PackView(world);
		
		screen.addKeyListener(new PackListener(world));
		
		app.add(screen);
		app.pack();
		app.setVisible(true);
		
		while(!world.gameOver()){
			world.tick();
			screen.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
