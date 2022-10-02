package telas;

import static main.Main.BACKGROUND_COLOR;
import static main.Main.CUSTOMIZED_BLUE;
import static main.Main.fredoka;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.Main;

public class Lobby extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	private String pathBackground = "/background-lobby.png";
	private ImageIcon selectedArrow = new ImageIcon("res/selected-arrow-right.png");
	private ImageIcon disabledArrow = new ImageIcon("res/disabled-arrow-right.png");

	private JButton btnConfirm;
	private ButtonGroup radioGroup;
	
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
				
		/**
		 * RadioButtons 
		 */
		radioGroup = new ButtonGroup();
		
		JRadioButton btnTesoura = new JRadioButton(disabledArrow);
		btnTesoura.setName("tesoura");
		btnTesoura.setSelected(true);
		btnTesoura.setBackground(BACKGROUND_COLOR);
		btnTesoura.setBounds(80, 350, 70, 64);
		btnTesoura.setSelectedIcon(selectedArrow);
		btnTesoura.setDisabledIcon(disabledArrow);
		radioGroup.add(btnTesoura);
		add(btnTesoura);
		
		JRadioButton btnPapel = new JRadioButton(disabledArrow);
		btnPapel.setName("papel");
		btnPapel.setBackground(BACKGROUND_COLOR);
		btnPapel.setBounds(80, 530, 70, 64);
		btnPapel.setSelectedIcon(selectedArrow);
		btnPapel.setDisabledIcon(disabledArrow);
		radioGroup.add(btnPapel);
		add(btnPapel);
		
		JRadioButton btnPedra = new JRadioButton(disabledArrow);
		btnPedra.setName("pedra");
		btnPedra.setBackground(BACKGROUND_COLOR);
		btnPedra.setBounds(80, 700, 70, 64);
		btnPedra.setSelectedIcon(selectedArrow);
		btnPedra.setDisabledIcon(disabledArrow);
		radioGroup.add(btnPedra);
		add(btnPedra);
		
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("/waiting.gif")));
		label.setBounds(470, 350, 200, 200);
		add(label);
		
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
	        
	        g.setColor(CUSTOMIZED_BLUE);
	        g.setFont(fredoka.deriveFont((float) 16));
	        
	        //Alternar para "voce selecionou *jogada*"
	        g.drawString("Selecione sua jogada clicando", 470, 650);
	        g.drawString("sobre a seta correspondente!", 470, 665);
	        
	        g.setColor(CUSTOMIZED_BLUE);
	        g.setFont(fredoka.deriveFont((float) 20));
	        //ALternar para "*nome* escolheu sua jogada!"
	        g.drawString("Aguardando jogada de *nome*", 435, 580);
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public void setBtnConfirm(JButton BtnConfirm) {
		this.btnConfirm = BtnConfirm;
	}

	public ButtonGroup getRadioGroup() {
		return radioGroup;
	}

	public void setRadioGroup(ButtonGroup radioGroup) {
		this.radioGroup = radioGroup;
	}
}

