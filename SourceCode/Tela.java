
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Tela extends Canvas {
	 
	private static final long serialVersionUID = 5063235601130561100L;
	
	public Tela(int width, int height, String nome, Game game) 
	{
		JFrame frame = new JFrame(nome);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}


}
