package telas;

import static main.Main.CUSTOMIZED_BLUE;
import static main.Main.fredoka;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Main;

public class InicioJogo extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	private String pathBackground = "/background.png";
	
	private JLabel lblLoading;
	private JLabel lblMensagem;

	public InicioJogo()
	{		
		try 
		{
			background = ImageIO.read(getClass().getResource(pathBackground));
			paintComponent(background.getGraphics());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		lblLoading = new JLabel(new ImageIcon(getClass().getResource("/spin.gif")));
		lblLoading.setBounds(420, 500, 100, 100);
		add(lblLoading);
		
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		setLayout(null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        
        /**
         * Mensagem inicio
         */
   	 	
   	 	String strMensagem = "Iniciando Partida"; 
   	 	lblMensagem = new JLabel(strMensagem, SwingConstants.CENTER);
		lblMensagem.setFont(fredoka.deriveFont((float) 30));
		lblMensagem.setForeground(CUSTOMIZED_BLUE);
		lblMensagem.setBounds(0, 400, 960, 40);
   	 	add(lblMensagem);
	}
}

