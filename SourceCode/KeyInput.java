
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private HUD hud;
	private boolean[] keyDown1 = new boolean[2], keyDown2 = new boolean[2];
	//private Cliente cliente;
	
	public KeyInput(Handler handler, HUD hud) {
		//this.cliente = cliente;
		this.handler = handler;
		this.hud = hud;
		
		keyDown1[0]=false;
		keyDown1[1]=false;
		keyDown2[0]=false;
		keyDown2[1]=false;
	}
	
	public void SpawnarTiro(GameObject obj) 
	{
		if(!HUD.podeAtirar)
			return;
			
		
		if(obj.id == ID.Player) {
			handler.addObject(new Laser(obj.x, obj.y, ID.Laser, handler));
		return;}
		
		if(obj.id == ID.PlayerOnline) {
			handler.addObject(new Laser(obj.x, obj.y, ID.LaserOnline, handler));
		return;}
		
	}
	
	void RemoverObjetosFimDePartida() 
	{
		for(int i = 0; i< handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
				
			if(tempObject.getID() == ID.Laser || tempObject.getID() == ID.LaserOnline || tempObject.getID() == ID.Trail)
			{
				handler.removeObject(tempObject);
			}
		}	
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		if(key == KeyEvent.VK_R && !HUD.podeAtirar)
		{
			hud.ClearHUD();
			RemoverObjetosFimDePartida();
			System.out.println("Chegou");
		}
		
		for(int i = 0; i< handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) 
			{
				//Eventos para player 1
				
				if(key == KeyEvent.VK_SPACE)
				{
					//cliente.send(" ");
					//atirar Player 1
					SpawnarTiro(tempObject);
				}
				
				if(key == KeyEvent.VK_W)
				{	
					//tempObject.setY(tempObject.getY() - 10);
					//cliente.send("W");
					tempObject.setVelY(-5);
					keyDown1[0] = true;
				}
				
				if(key == KeyEvent.VK_S)
				{
					//tempObject.setY(tempObject.getY() + 10);
					//cliente.send("S");
					tempObject.setVelY(5);
					keyDown1[1] = true;
				}
			}
			
			if(tempObject.getID() == ID.PlayerOnline) 
			{
				//Eventos para player 2
				
				if(key == KeyEvent.VK_ENTER)
				{
					//atirar Player 2
					SpawnarTiro(tempObject);
				}
				
				if(key == KeyEvent.VK_UP)
				{
					//tempObject.setY(tempObject.getY() - 10);
					tempObject.setVelY(-5);
					keyDown2[0] = true;
				}
				
				if(key == KeyEvent.VK_DOWN)
				{
					//tempObject.setY(tempObject.getY() + 10);
					tempObject.setVelY(5);
					keyDown2[1] = true;
				}
			}
		}
		
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i< handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) 
			{
				//Eventos para player 1
				
				if(key == KeyEvent.VK_W)
				{
					//tempObject.setVelY(0);
					keyDown1[0] = false;
				}
				
				if(key == KeyEvent.VK_S)
				{
					//tempObject.setVelY(0);
					keyDown1[1] = false;
				}
				
				if(!keyDown1[0] && !keyDown1[1])
					tempObject.setVelY(0);
			}
			
			if(tempObject.getID() == ID.PlayerOnline) 
			{
				//Eventos para player 2
				
				if(key == KeyEvent.VK_UP)
				{
					//tempObject.setVelY(0);
					keyDown2[0] = false;
				}
				
				if(key == KeyEvent.VK_DOWN)
				{
					//tempObject.setVelY(0);
					keyDown2[1] = false;
				}
				
				if(!keyDown2[0] && !keyDown2[1])
					tempObject.setVelY(0);
			}
		}
	}

}
