package telas;

import static main.Main.CUSTOMIZED_BLUE;
import static main.Main.fredoka;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Main;

public class CreateRoom  extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	private String pathBackground = "/background.png";
	
	private JTextField roomCode;
	private JButton btnVoltar;
	
	public CreateRoom()
	{	
		try 
		{
			background = ImageIO.read(getClass().getResource(pathBackground));
			paintComponent(background.getGraphics());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		setLayout(null);
		
		roomCode = new JTextField(Main.server.getAddress());
		roomCode.setFont(fredoka);
		roomCode.setBounds(140, 400, 690, 60);
		roomCode.setEnabled(false);
		add(roomCode);
		
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("/ellipsis.gif")));
		label.setBounds(730, 550, 100, 100);
		add(label);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(fredoka);
		btnVoltar.setBackground(CUSTOMIZED_BLUE);
		btnVoltar.setBounds(390, 550, 200, 40);
		add(btnVoltar);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(background, 0, 0, null);
	        
	        g.setColor(CUSTOMIZED_BLUE);
	        g.setFont(fredoka);
	        g.drawString("C�digo da Sala", 390, 380);
	        
	        g.setColor(CUSTOMIZED_BLUE);
	        g.setFont(fredoka.deriveFont(6));
	        g.drawString("Aguardando Conex�o", 650, 650);
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}
}

