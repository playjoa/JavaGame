
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Player extends GameObject {

	Image img1, img2;
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		try {
	        img1   = ImageIO.read(new File("Player 1.png"));
	        img2   = ImageIO.read(new File("Player 2.png"));
	      } catch (IOException e) {

	      }
		
	
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 64, 32);
	}
	
	public void tick() {	
		
		if(id==ID.Player)
		{
			handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 64, 32, 0.4f, handler));

		}
		if(id==ID.PlayerOnline)
		{
			handler.addObject(new Trail(x, y, ID.Trail, Color.green, 64, 32, 0.4f, handler));
			//Movimentacao do online
			
		}
		
		x += velX;
		y += velY;
		
		y = Game.clamp(y, 0, Game.height - 68);

		
		Colisor();
	}
	
	private void Colisor() 
	{
		for(int i = 0; i< handler.object.size(); i++) 
		{
			GameObject go = handler.object.get(i);
			
			if(go.getID() == ID.Laser)
			{
				if(getBounds().intersects(go.getBounds()))
				{
					if(this.getID() == ID.PlayerOnline)
					{
						HUD.ScorePlayer1++;
						handler.removeObject(go);
					}
				}
				
			}
			
			if(go.getID() == ID.LaserOnline)
			{
				if(getBounds().intersects(go.getBounds()))
				{
					if(this.getID() == ID.Player)
					{
						HUD.ScorePlayer2++;
						handler.removeObject(go);
					}
				}
				
			}
		}
	}


	public void render(Graphics g) {
		
		if(id==ID.Player) 
		{
			g.setColor(Color.blue);
		}
		if(id==ID.PlayerOnline) 
		{
			g.setColor(Color.green);
		}
		
		g.fillRect(x, y, 64, 32);
	}
	
	


	
	
	
	

}
