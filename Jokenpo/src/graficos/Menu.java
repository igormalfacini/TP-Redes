package graficos;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Menu {

	private BufferedImage background;

	public Menu(String path)
	{
		try 
		{
			background = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(JPanel jpanel)
	{
//		game.g.drawImage(background, 0, 0, null);
//		
//		game.g.setColor(new Color(0,0,0, 120));
//		game.g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
//		
//		game.g.setFont(new Font("Arial", Font.BOLD, 30));
//		game.g.setColor(Color.white);
//		game.g.drawString("Jokenpô!", (Game.WIDTH/2) - 84, 60);

		Button join = new Button("Juntar-se");
		jpanel.add(join);
		
		Button createRoom = new Button("Criar Sala");
		createRoom.setSize(100, 60);
		jpanel.add(createRoom);
		
		Button exit = new Button("Sair");
		jpanel.add(exit);
	}
}
