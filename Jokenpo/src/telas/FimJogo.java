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
	private String[] placar;

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
		btnVoltar.setBounds(360, 650, 500, 40);
		add(btnVoltar);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(background, 0, 0, null);
	        
	        g.setColor(CUSTOMIZED_BLUE);
	        g.setFont(fredoka);
	        g.setFont(fredoka.deriveFont((float) 40));
	        g.drawString(nomeVencedor + " venceu o jogo!", 350, 460);
	        
	        /**
	         * Placar
	         */
	        //TODO Placar redimensionável
	   	 	g.setFont(fredoka.deriveFont((float) 45));
	   	 	
	   	 	if(placar == null) return;
	   	 	
	   	 	g.setFont(fredoka.deriveFont((float) 30));
	   	 	g.drawString(placar[0], 340, 510);
		 	g.drawString(placar[1], 550, 510);
		 	g.drawString("X", 595, 510);
		 	g.drawString(placar[3], 640, 510);
		 	g.drawString(placar[2], 680, 510);
	}
	
	public JButton getBtnVoltar() {
		return btnVoltar;
	}
	
	public void setPlacar(String[] placar) {
		this.placar = placar;
		validate();
		repaint();
	}
}

