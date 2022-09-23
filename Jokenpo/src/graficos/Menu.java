package graficos;

import static graficos.MainWindow.CUSTOMIZED_BLUE;
import static graficos.MainWindow.fredoka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	private String pathBackground = "/background.png";
	
	/**
	 * Componentes da tela
	 */
	private JTextField inputNickname;
	private JButton btnJoin;
	private JButton btnCreateRoom;
	private JButton btnExit;

	public Menu()
	{	
		try 
		{
			background = ImageIO.read(getClass().getResource(pathBackground));
			paintComponent(background.getGraphics());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(MainWindow.WIDTH * MainWindow.SCALE, MainWindow.HEIGHT * MainWindow.SCALE));
		setLayout(null);
		
		inputNickname = new JTextField("Informe seu nome");
		inputNickname.setFont(fredoka);;
		inputNickname.setBounds(250, 500, 700, 60);
		add(inputNickname);
		
		btnJoin = new JButton("Juntar-se");
		btnJoin.setFont(fredoka);
		btnJoin.setBackground(CUSTOMIZED_BLUE);
		btnJoin.setBounds(500, 600, 200, 40);
		add(btnJoin);
		
		btnCreateRoom = new JButton("Criar Sala");
		btnCreateRoom.setFont(fredoka);
		btnCreateRoom.setBackground(CUSTOMIZED_BLUE);
		btnCreateRoom.setBounds(500, 650, 200, 40);
		add(btnCreateRoom);
		
		btnExit = new JButton("Sair");
		btnExit.setFont(fredoka);
		btnExit.setBackground(CUSTOMIZED_BLUE);
		btnExit.setBounds(500, 700, 200, 40);
		add(btnExit);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(background, 0, 0, null);
	}

}
