package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class PackView extends Canvas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PackModel world;
	private final int T =30;
	
	public PackView(PackModel world){
		this.world = world;
		this.setSize(world.getWidth() * T,  world.getHeight() * T);
	}
	
	public void update(Graphics g){
		Image img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		paint(img.getGraphics());
		g.drawImage(img, 0, 0, null);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < world.getWidth(); i++){
			for(int j = 0; j < world.getHeight(); j++) {
				char c = world.getCell(i,j);
				switch (c) {
				case '.':
					if((world.time / 10)%2 == 0) 
						g.drawOval(i*T+T/3, j*T+T/3, T/3, T/3);
					break;
				case '#': g.fillRect(i*T, j*T, T, T);break;
				case ' ':break; 
				case 'o': g.drawOval(i*T+1, j*T+1, T-2, T-2);break;
				default: System.out.println(" introuvable => " + c);
				}
			}
		}
		g.setColor(Color.RED);
		g.drawString(String.valueOf(world.score),world.getWidth()/2-15,T/6);
		//System.out.println(world.score);
		
		g.setColor(Color.YELLOW);
		g.fillArc((int)(world.pacX*T), (int)(world.pacY*T), T, T, 6*world.packmouth, 360-2*6*world.packmouth);
		g.setColor(Color.BLACK);
		g.drawArc((int)(world.pacX*T), (int)(world.pacY*T), T, T, 6*world.packmouth, 360-2*6*world.packmouth);
		g.fillOval((int)(world.pacX*T)+T/3, (int)(world.pacY*T)+T/4, T/6, T/6);
		
		for(Ghost ghost : world.ghost){
			g.setColor(ghost.color);
			g.fillOval((int)(ghost.x*T), (int)(ghost.y*T), T, T);
			g.fillRect((int)(ghost.x*T), (int)(ghost.y*T)+T/2, T, T/2);
			g.setColor(Color.WHITE);
			g.fillOval((int)(ghost.x*T)+T/3 - T/10, (int)(ghost.y*T)+T/2 -T/10, T/5, T/5);
			g.fillOval((int)(ghost.x*T)+2*T/3 -T/10, (int)(ghost.y*T)+T/2 -T/10, T/5, T/5);
			
		}
		
	}
}
