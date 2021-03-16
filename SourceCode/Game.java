
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 2661778265670034243L;
	public static int width = 1280, height = 720;
	
	private Thread thread;
	private boolean running = true;
	
	private Handler handler;
	private Servidor servidor;
	private Cliente cl;
	private HUD hud;	
	
	public Game() 
	{
		handler = new Handler();
		hud = new HUD();
		
		new Tela(width, height, "Jogo Java", this);	
		
		this.addKeyListener(new KeyInput(handler, hud));
		
		handler.addObject(new Player(40, 360, ID.Player, handler));
		handler.addObject(new Player(1150, 360, ID.PlayerOnline, handler));
		
	}
	
	public synchronized void start() 
	{
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() 
	{
		try {
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
	}
	
	public void run() 
	{
		this.requestFocus();
		

		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / 60;
		final double timeF = 1000000000 / 60;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		while (running) {

        long currentTime = System.nanoTime();
        deltaU += (currentTime - initialTime) / timeU;
        deltaF += (currentTime - initialTime) / timeF;
        initialTime = currentTime;

        if (deltaU >= 1) {
            //getInput();
            tick();
            ticks++;
            deltaU--;
        }

        if (deltaF >= 1) {
            render();
            frames++;
            deltaF--;
        }

        if (System.currentTimeMillis() - timer > 1000) {
           
            frames = 0;
            ticks = 0;
            timer += 1000;
        }
    }
		stop();
	}
	
	
	private void tick() 
	{
		handler.tick();
		hud.tick();
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
	
		if(bs == null) 
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
	 	g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		handler.render(g);
		hud.render(g);

		g.dispose();
		bs.show();
	}
	
	public static int clamp(int v, int min, int max)
	{
		if(v>=max)
			return v = max;
		else if(v<= min)
			return v= min;
		else 
			return v;
	}
	
	public static void main (String args[])
	{
		new Game();
	}
}
