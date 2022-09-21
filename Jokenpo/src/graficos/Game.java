package graficos;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int SCALE = 3;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;

	private JFrame jframe;
	
	public static String gameState = "menu";

	private Menu menu; 
	
	public Game()
	{
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
		menu = new Menu("/pattern-background.jpg");
		menu.render(this);
		
		jframe = new JFrame("Jokenpô");
		jframe.add(this);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jframe.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));

	}
}
