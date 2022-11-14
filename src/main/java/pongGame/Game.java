package pongGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener 
{
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning;
	private Thread thread;
	
	public final static int WIDTH = 240;
	public final static int HEIGHT = 160;
	public final static int SCALE = 3;
	
	private JFrame frame;
	
	private BufferedImage GameImage;
	
	public static Player player;
	public static Enimy enimy;
	public static PongBall pongBall;
	
	
	public Game() 
	{
		this.GameImage = new BufferedImage(Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, BufferedImage.TYPE_INT_RGB);
		
		this.setPreferredSize(new Dimension(Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE));
		this.setJFrameConfigure();
		
		this.player = new Player( ((Game.WIDTH*Game.SCALE)/2), (Game.HEIGHT*Game.SCALE)-40);
		this.enimy = new Enimy( ((Game.WIDTH*Game.SCALE)/2), 20);
		this.pongBall = new PongBall( ((Game.WIDTH*Game.SCALE)/2), ((Game.HEIGHT*Game.SCALE)/2));
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.Start();
	}
	
	public synchronized void Start()
	{
		this.isRunning = true;
		this.thread = new Thread(this);
		this.thread.start();
		this.addKeyListener(this);
	}
	
	public synchronized void Stop()
	{
		
	}
	
	public void Update()
	{
		this.player.Update();
		this.enimy.Update();
		this.pongBall.Update();
	}
	
	public void Render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graph = this.GameImage.getGraphics();
		graph.setColor(new Color(200, 0, 200));
		graph.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		graph.dispose();
		graph = bs.getDrawGraphics();
		graph.drawImage(this.GameImage, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE, null);
		
		this.player.Render(graph);
		this.enimy.Render(graph);
		this.pongBall.Render(graph);
		
		bs.show();
		
		
	}

	public void run() 
	{
		long lastTimer = System.nanoTime();
		double amountUpdate = 60.0;
		double ns = 1000000000/amountUpdate;
		double delta = 0;
		
		double CountToFrame = 0;
		double timeToFrame = System.currentTimeMillis();
		
		while(this.isRunning)
		{
			long nowTimer = System.nanoTime();
			delta+= (nowTimer - lastTimer) / ns;
			lastTimer = nowTimer;
			
			if(delta >= 1)
			{
				this.Update();
				this.Render();
				
				CountToFrame ++;
				delta --;
			}
			if(System.currentTimeMillis() - timeToFrame >= 1000)
			{
				System.out.println("FPS: - " + CountToFrame);
				
				CountToFrame = 0;
				timeToFrame += 1000;
			}
			
			this.Stop();				
		}		
	}
	
	public void setJFrameConfigure()
	{
		this.frame = new JFrame("WELCOME GAME PONG BY WANG KAI");
		this.frame.add(this);
		this.frame.setResizable(false);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			this.player.right = true;
			this.player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			this.player.left = true;
			this.player.right = false;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			this.player.right = false;
			this.player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			this.player.left = false;
			this.player.right = false;
		}
	}
}
