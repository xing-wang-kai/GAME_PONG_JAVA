package pongGame;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right, left;
	public int x, y;
	
	public final int WIDTH = 160;
	public final int HEIGHT = 20;
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void Update()
	{
		if(this.right)
		{
			this.x ++;
		}
		else if (this.left)
		{
			this.x --;
		}
		
		if((this.x+this.WIDTH) > Game.WIDTH*Game.SCALE)
		{
			this.x = (Game.WIDTH*Game.SCALE) - this.WIDTH;
		}
		else if( this.x < 0)
		{
			this.x = 0;
		}		
	}
	public void Render( Graphics graph)
	{
		graph.setColor(new Color(0, 255, 255));
		graph.fillRect(this.x, this.y, this.WIDTH, this.HEIGHT);
	}
}
