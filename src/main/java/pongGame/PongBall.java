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
	private double speed = 7f;
	
	public PongBall(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		this.GenerationAngle();	
		
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
			new Game();
			return;
		}
		else if(this.y < 0)
		{
			System.out.println("PONTO DO JOGADOR");
			new Game();
			return;
		}
		
		Rectangle BallBounds = new Rectangle(
					(int) ( this.x + (this.directionX * this.speed)), 
					(int) ( this.y + (this.directionY * this.speed)),
					this.WIDTH,
					this.HEIGHT
				);
		Rectangle PlayerBounds = new Rectangle( Game.player.x, Game.player.y, Game.player.WIDTH, Game.player.HEIGHT );
		Rectangle EnimyBounds = new Rectangle((int) Game.enimy.x, (int) Game.enimy.y, Game.enimy.WIDTH, Game.enimy.HEIGHT);
		
		if(BallBounds.intersects(PlayerBounds))
		{
			this.GenerationAngle();
			if (directionY > 0)
			{
				this.directionY *= -1;
			}
			
		}
		else if (BallBounds.intersects(EnimyBounds))
		{
			this.GenerationAngle();
			if (this.directionX < 0)
			{
				this.directionY *= -1;
			}
			
		}
		
	}
	public void Render(Graphics Graph)
	{
		Graph.setColor(new Color(255, 200, 0));
		Graph.fillOval((int) this.x, (int) this.y, this.WIDTH, this.HEIGHT);;
	}
	
	public void GenerationAngle()
	{
		int angle = new Random().nextInt(120) + 45;
		this.directionX = Math.cos(Math.toRadians(angle));
		this.directionY = Math.sin(Math.toRadians(angle));
	}

}
