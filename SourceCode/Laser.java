
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Laser  extends GameObject {
	
	private Handler handler;
	
	public Laser(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 32, 16);
	}
	
	public void tick() {	
		
		if(id==ID.Laser)
		{
			velX = 8;
			handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 32, 16, 0.08f, handler));
		}
		if(id==ID.LaserOnline)
		{
			velX = -8;
			handler.addObject(new Trail(x, y, ID.Trail, Color.red, 32, 16, 0.08f, handler));
		}

		x += velX;
		y += velY;
		
		Colisor();
		
		if (this.x <= 0 || this.x >= Game.width)
		{
			handler.removeObject(this);
			//System.out.println(x);
		}
		
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
					if(this.getID() == ID.LaserOnline)
					{	
						handler.removeObject(this);
						handler.removeObject(go);
					}
				}
				
			}
			
		}
	}


	public void render(Graphics g) {
		
		if(id == ID.Laser) 
		{
			g.setColor(Color.blue);
		}
		if(id == ID.LaserOnline) 
		{
			g.setColor(Color.green);
		}
		
		g.fillRect(x, y, 32, 16);
	}
	

}
