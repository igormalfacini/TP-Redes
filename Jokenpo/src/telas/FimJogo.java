package telas;

import static main.Main.CUSTOMIZED_BLUE;
import static main.Main.fredoka;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;

public class FimJogo extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	private String pathBackground = "/background.png";

	private JButton btnVoltar;
	
	private String nomeVencedor;

	public FimJogo(String nomeVencedor)
	{	
		this.nomeVencedor = nomeVencedor;
		
		try 
		{
			background = ImageIO.read(getClass().getResource(pathBackground));
			paintComponent(background.getGraphics());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		setLayout(null);
		
		btnVoltar = new JButton("Voltar ao Menu Principal");
		btnVoltar.setFont(fredoka);
		btnVoltar.setBackground(CUSTOMIZED_BLUE);
		btnVoltar.setBounds(500, 650, 200, 40);
		add(btnVoltar);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(background, 0, 0, null);
	        
	        g.setColor(CUSTOMIZED_BLUE);
	        g.setFont(fredoka);
	        g.drawString("Informe o Código da Sala", 430, 480);
	}
	
	public JButton getBtnVoltar() {
		return btnVoltar;
	}
}

