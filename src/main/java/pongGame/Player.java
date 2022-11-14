package pongGame;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right, left;
	public int x, y;
	
	private final int WIDTH = 160;
	private final int HIGHT = 20;
	
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
		else if( x < 0)
		{
			x = 0;
		}		
	}
	public void Render( Graphics graph)
	{
		graph.setColor(new Color(100, 255, 100));
		graph.fillRect(x, y, this.WIDTH, this.HIGHT);
	}
}
