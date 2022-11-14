package pongGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class PongBall {
	
	public double x, y;
	public final int WIDTH = 25;
	public final int HEIGHT = 25;
	
	private double directionX, directionY;
	private double speed = 1.8f;
	
	public PongBall(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		this.directionX = new Random().nextGaussian();
		this.directionY = new Random().nextGaussian();	
		
	}
	public void Update()
	{
		this.x += this.directionX * this.speed;
		this.y += this.directionY * this.speed;
		
		if(this.x + (this.directionX*this.speed) + this.WIDTH > Game.WIDTH*Game.SCALE
				|| this.x + (this.directionX*this.speed) <= 0)
		{
			this.directionX*=-1;
		}
		
		
		if(this.y >= Game.HEIGHT*Game.SCALE)
		{
			System.out.println("PONTO DO ENIMIGO");
		}
		else if(this.y < 0)
		{
			System.out.println("PONTO DO JOGADOR");
		}
		
		Rectangle BallBounds = new Rectangle(
					(int) ( this.x + (this.directionX*this.speed)), 
					(int) (this.y + (this.directionY * this.speed)),
					this.WIDTH,
					this.HEIGHT
				);
		Rectangle PlayerBounds = new Rectangle(Game.player.x, Game.player.y, Game.player.WIDTH, Game.player.HEIGHT);
		Rectangle EnimyBounds = new Rectangle((int) Game.enimy.x, (int) Game.enimy.y, Game.enimy.WIDTH, Game.enimy.HEIGHT);
		
		if(BallBounds.intersects(PlayerBounds))
		{
			this.directionY *= -1;
		}
		else if (BallBounds.intersects(EnimyBounds))
		{
			this.directionY *= -1;
		}
		
	}
	public void Render(Graphics Graph)
	{
		Graph.setColor(new Color(255, 200, 0));
		Graph.fillOval((int) this.x, (int) this.y, this.WIDTH, this.HEIGHT);;
	}

}
