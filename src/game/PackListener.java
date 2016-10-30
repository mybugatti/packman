package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PackListener implements KeyListener {
	
	PackModel world;
	
	public PackListener(PackModel world) {
		this.world = world;
	}

	public void keyPressed(KeyEvent k) {
		int code = k.getKeyCode();
		//System.out.println(code);
		if(code == KeyEvent.VK_LEFT) {
			world.nextpackdir = world.LETF;
		}
		else if (code == KeyEvent.VK_RIGHT){
			world.nextpackdir = world.RIGHT;
		}
		else if (code == KeyEvent.VK_UP){
			world.nextpackdir = world.UP;
		}
		else if(code == KeyEvent.VK_DOWN){
			world.nextpackdir = world.DOWN;
		}
			
	}

	public void keyReleased(KeyEvent k) {

	}
 
	public void keyTyped(KeyEvent k) {
	}

}
