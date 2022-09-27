package telas;

import static main.Main.CUSTOMIZED_BLUE;
import static main.Main.fredoka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.Main;

public class Lobby extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	private String pathBackground = "/background-lobby.png";
	
	private JButton btnConfirm;
	
	public Lobby()
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
				
		JRadioButton tesoura = new JRadioButton();
		tesoura.setName("tesoura");
		tesoura.setBackground(new Color(0, 0, 0, 0));
		tesoura.setBounds(390, 650, 25, 25);
		add(tesoura);
		
		JRadioButton papel = new JRadioButton();
		papel.setName("papel");
		papel.setBackground(new Color(0, 0, 0, 0));
		papel.setBounds(590, 650, 25, 25);
		add(papel);
		
		JRadioButton pedra = new JRadioButton();
		pedra.setName("pedra");
		pedra.setBackground(new Color(0, 0, 0, 0));
		pedra.setBounds(790, 650, 25, 25);
		add(pedra);
		
//		ImageIcon imageIcon = new ImageIcon("/res/loading.gif");
//		JLabel label = new JLabel(imageIcon);
//		label.setBounds(570, 550, 60, 60);
//		add(label);
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setFont(fredoka);
		btnConfirm.setBackground(CUSTOMIZED_BLUE);
		btnConfirm.setBounds(500, 700, 200, 40);
		add(btnConfirm);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(background, 0, 0, null);
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public void setBtnConfirm(JButton BtnConfirm) {
		this.btnConfirm = BtnConfirm;
	}
}

