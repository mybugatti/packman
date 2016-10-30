package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PackModel {
	
	public final static int STILL=0, UP=1, RIGHT=2, DOWN=3, LETF=4;
	public int score = 0;
	public int packdir = RIGHT;
	private ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();

	public int time = 0;
	public float pacX=4, pacY=5;
	public int nextpackdir = packdir;
	public int packmouth = 10;
	private boolean closing = true;
	public ArrayList<Ghost> ghost = new ArrayList<Ghost>();
	
	private String startboard = 
							  "####################\n" 
							+ "#o.....#.....#....o#\n" 
							+ "#...##.......##....#\n"
							+ "#.....#............#\n"
							+ "#......###..##.##..#\n"
							+ "#.....#......#.....#\n"
							+ "#.....########.....#\n"
							+ "#..................#\n"
							+ "#..................#\n"
							+ "#........#.........#\n"
							+ "#..................#\n"
							+ "#..................#\n"
							+ "#..................#\n"
							+ "#........#.........#\n"
							+ "#..................#\n"
							+ "#..................#\n"
							+ "#...............#..#\n"
							+ "#o................o#\n" 
							+ "####################";
							
			

	
	public PackModel(){
		
		for(String row : startboard.split("\n")){
			ArrayList<Character> r = new ArrayList<Character>();
			for(int i=0; i < row.length(); i++){
				r.add(row.charAt(i));
			}
			board.add(r);
			//System.out.println(r);
		}
		
		ghost.add(new Ghost(3,10,Color.cyan));
		ghost.add(new Ghost(5, 15,Color.GREEN));
		//ghost.add(new Ghost(10, 8,Color.PINK));
	}

	public int getWidth() {
		//System.out.println("getWidth " +board.get(0).size());
		return board.get(0).size();
	}

	public int getHeight() {
		//System.out.println("getHeight " +board.size());
		return board.size();
	}

	public void setCell(int x, int y, char c){
		board.get(y).set(x, c);
	}
	
	public char getCell(int x, int y) {
		//System.out.println("getCell " +board.get(y).get(x));
		return board.get(y).get(x);
	}
	
	public void tick(){
		time +=1;
		
		if(packdir == RIGHT && nextpackdir == LETF)
			packdir = LETF;
		else if (packdir == LETF && nextpackdir == RIGHT)
			packdir = RIGHT;
		else if (packdir == UP && nextpackdir == DOWN)
			packdir = DOWN;
		else if (packdir == DOWN && nextpackdir == UP)
			packdir = UP;
		else if (Math.abs(pacX - (int)pacX) < .1 && Math.abs(pacY - (int)pacY) < .1)
			packdir = nextpackdir;
		
		int x = Math.round(pacX);
		int y = Math.round(pacY);
		//System.out.println("place X " +x);
		//System.out.println("place Y " +y);
		
		if(getCell(x,y) == '.')
			//System.err.println("score " + score++);
			//g.drawString(String.valueOf(score),WIDTH/2-15,125);
			setCell(x,y, ' ');
		
		if((packdir == RIGHT && getCell(x+1, y) == '#' && pacX >= x)
			|| (packdir == LETF && getCell(x-1, y) == '#' && pacX <= x)
			|| (packdir == UP && getCell(x, y-1) == '#' && pacY <= y)
			|| (packdir == DOWN && getCell(x, y+1) == '#' && pacY >= y)){
				packdir = STILL;
				pacX = x;
				pacY = y;
			}
		
		if(packmouth == 0)
			closing = false;
		else if (packmouth == 10)
			closing = true;
		
		if(closing)
			packmouth--;
		else
			packmouth++;
		
		if(packdir == RIGHT)
			pacX+= .1;
		else if(packdir == LETF)
			pacX-= .1;
		else if(packdir == UP)
			pacY-= .1;
		else if(packdir == DOWN)
			pacY+= .1;
		
		for(Ghost myghost : ghost){
			if(Math.abs(myghost.x - (int)myghost.x) < .1 && Math.abs(myghost.y - (int)myghost.y) < .1 ){
				myghost.chooseDir(this);
			}
			
			if(myghost.dir == LETF)
				//System.out.println(RIGHT);
				myghost.x -= .04;
			else if(myghost.dir == RIGHT)
				myghost.x += .04;
			else if(myghost.dir == UP)
				myghost.y -= .04;
			else if(myghost.dir ==  DOWN)
				myghost.y += .04;
		}
	}
	

	public boolean gameOver() {
		return false;
	}
}
