package game;

import java.awt.Color;

public class Ghost {
	public float x,y;
	public Color color;
	int dir;
	
	public Ghost(int x, int y, Color c){
		this.x = x;
		this.y = y;
		this.color = c;
	}

	public void chooseDir(PackModel packModel) {
		float dX = packModel.pacX - this.x;
		float dY = packModel.pacY - this.y;
		
		
		if(Math.round(Math.abs(this.x)) == Math.round(packModel.pacX) && Math.round(Math.abs(this.y)) == Math.round(packModel.pacY))
			System.out.println(" GaMe OvEr");
			
		
		if(Math.abs(dX) > Math.abs(dY)){
			if(dX < 0 )
				dir = PackModel.LETF;
				//System.out.println(PackModel.LETF);
			else
				dir = PackModel.RIGHT;
		} else {
			if(dY < 0 )
				dir = PackModel.UP;
			else
				dir = PackModel.DOWN;
		}
		
		
	}
}
