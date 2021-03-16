
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	public static int ScorePlayer1 = 0, ScorePlayer2 = 0;
	public static boolean podeAtirar = true;
	private Graphics gLocal;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		gLocal = g;
		
		g.setColor(Color.white);
		g.drawString("Player 1: " + ScorePlayer1, 50, 20);
		g.drawString("Player 2: " + ScorePlayer2, 1150, 20);
		
		if(ScorePlayer1>=5)
		{
			podeAtirar= false;
			Font currentFont = g.getFont();
			Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.2F);
			g.setFont(newFont);
			g.setColor(Color.blue);
			g.drawString("Player 1 Venceu a partida!", 460, 330);
			g.setColor(Color.yellow);
			g.drawString("Aperte R para repetir a partida", 460, 380);
		}
		
		if(ScorePlayer2>=5)
		{
			podeAtirar= false;
			Font currentFont = g.getFont();
			Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.2F);
			g.setFont(newFont);
			g.setColor(Color.green);
			g.drawString("Player 2 Venceu a partida!", 460, 330);
			g.setColor(Color.yellow);
			g.drawString("Aperte R para repetir a partida", 460, 380);
		}
	}
	
	public void ClearHUD()
	{
		ScorePlayer1 = 0;
		ScorePlayer2 = 0;
		podeAtirar = true;
		new Game();		
	}

}
