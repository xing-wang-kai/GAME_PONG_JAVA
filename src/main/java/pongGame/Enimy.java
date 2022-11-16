package pongGame;

import java.awt.Color;
import java.awt.Graphics;

public class Enimy {

	public double x, y;
	public final int WIDTH = 160;
	public final int HEIGHT = 20;
	
	public Enimy(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void Update()
	{
		this.x += (Game.pongBall.x - this.x)  * 0.07;
		
		if((this.x + this.WIDTH) >= Game.WIDTH*Game.SCALE)
		{
			this.x = (Game.WIDTH * Game.SCALE) - this.WIDTH;
		}
		else if( this.x <= 0)
		{
			this.x = 0;
		}
	}
	public void Render( Graphics graph)
	{
		graph.setColor(new Color(255, 0, 0));
		graph.fillRect((int) this.x, (int) this.y, this.WIDTH, this.HEIGHT);
	}
}
