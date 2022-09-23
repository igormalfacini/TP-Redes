package graficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static int SCALE = 3;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	
	public static final Color CUSTOMIZED_BLUE = new Color(119, 179, 214);
	
	public static String gameState = "menu";
	
	public static Font fredoka; 
	
	public MainWindow()
	{
		configureFont();
		
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		getContentPane().setLayout(new FlowLayout(0, 0, 0));
		
		setTitle("Jokenpô!");
		setResizable(false);
		
		JPanel menu = new Menu();
		add(menu);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon-2.png")));

	}
	
	private void configureFont() { 
		try {
			fredoka = Font.createFont(0, new File("res/FredokaOne-Regular.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
			fredoka =  new Font ("Garamond", Font.BOLD , 11);
		} catch (FontFormatException e) {
			e.printStackTrace();
			fredoka =  new Font ("Garamond", Font.BOLD , 11);
		}
		
		fredoka = fredoka.deriveFont(Font.BOLD, 25);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(fredoka);
	}
}
