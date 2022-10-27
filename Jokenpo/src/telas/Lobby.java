package telas;

import static main.Main.BACKGROUND_COLOR;
import static main.Main.CUSTOMIZED_BLUE;
import static main.Main.fredoka;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import main.Main;

public class Lobby extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage background;
	private String pathBackground = "/background-lobby.png";
	
	private ImageIcon selectedArrow = new ImageIcon("res/selected-arrow-right.png");
	private ImageIcon disabledArrow = new ImageIcon("res/disabled-arrow-right.png");
	
	private JLabel lblLupa;
	private JLabel lblPlacar;
	private JLabel lblTimer;
	
	private JButton btnConfirm;
	private ButtonGroup radioGroup;
	
	private String jogadaArdversario = null;
	private boolean adversarioJogou = false;
	private boolean fimRound = false;
	private String nomeVencedor = null;
	
	private Timer timer;
	private int segundos, minutos;
	private String ddSegundos, ddMinutos;
	private DecimalFormat dFormat = new DecimalFormat("00");
	
	private String[] placar;
	
	public Lobby()
	{	
		try 
		{
			background = ImageIO.read(getClass().getResource(pathBackground));
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
		btnTesoura.setName("TESOURA");
		btnTesoura.setSelected(true);
		btnTesoura.setBackground(BACKGROUND_COLOR);
		btnTesoura.setBounds(60, 270, 70, 64);
		btnTesoura.setSelectedIcon(selectedArrow);
		btnTesoura.setDisabledIcon(disabledArrow);
		radioGroup.add(btnTesoura);
		add(btnTesoura);
		
		JRadioButton btnPapel = new JRadioButton(disabledArrow);
		btnPapel.setName("PAPEL");
		btnPapel.setBackground(BACKGROUND_COLOR);
		btnPapel.setBounds(60, 420, 70, 64);
		btnPapel.setSelectedIcon(selectedArrow);
		btnPapel.setDisabledIcon(disabledArrow);
		radioGroup.add(btnPapel);
		add(btnPapel);
		
		JRadioButton btnPedra = new JRadioButton(disabledArrow);
		btnPedra.setName("PEDRA");
		btnPedra.setBackground(BACKGROUND_COLOR);
		btnPedra.setBounds(60, 560, 70, 64);
		btnPedra.setSelectedIcon(selectedArrow);
		btnPedra.setDisabledIcon(disabledArrow);
		radioGroup.add(btnPedra);
		add(btnPedra);
		
		lblLupa = new JLabel(new ImageIcon(getClass().getResource("/magnifier.gif")));
		lblLupa.setBounds(690, 300, 200, 200);
		add(lblLupa);
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setFont(fredoka);
		btnConfirm.setBackground(CUSTOMIZED_BLUE);
		btnConfirm.setBounds(390, 550, 200, 40);
		add(btnConfirm);	
		
		/**
		 * Timer 
		 */
		lblTimer = new JLabel();
		lblTimer.setBounds(440, 350, 100, 40);
		lblTimer.setHorizontalAlignment(JLabel.CENTER);
		lblTimer.setFont(fredoka);
		lblTimer.setForeground(CUSTOMIZED_BLUE);
		add(lblTimer);
		
		lblTimer.setText("01:00");
		segundos = 00;
		minutos = 1;
		countdownTimer();
		timer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		/**
         * Esconde a jogada do outro jogador
         */
		if(jogadaArdversario == null || !jogadaArdversario.equalsIgnoreCase("TESOURA")) {
			g.setColor(BACKGROUND_COLOR);
			g.fillRect(690, 250, 150, 120);
		}
        
		if(jogadaArdversario == null || !jogadaArdversario.equalsIgnoreCase("PAPEL")) {
			g.setColor(BACKGROUND_COLOR);
			g.fillRect(690, 390, 150, 120);
		}
		
        if(jogadaArdversario == null || !jogadaArdversario.equalsIgnoreCase("PEDRA")) {
        	g.setColor(BACKGROUND_COLOR);
        	g.fillRect(690, 540, 150, 120);
        }
        
        
        g.setColor(CUSTOMIZED_BLUE);
        
        /**
         * Placar
         */
   	 	g.setFont(fredoka.deriveFont((float) 45));
   	 	g.drawString("Round " + placar[4], 410, 150);
   	 	
   	 	String strPlacar = placar[0] + "  " + placar[1] + "  " + "X" + "  " + placar[3] + "  " + placar[2]; 
   	 	if(lblPlacar != null)
   	 		remove(lblPlacar);
   	 	lblPlacar = new JLabel(strPlacar, SwingConstants.CENTER);
   	 	lblPlacar.setFont(fredoka.deriveFont((float) 28));
   	 	lblPlacar.setForeground(CUSTOMIZED_BLUE);
   	 	lblPlacar.setBounds(0, 170, 960, 40);
   	 	add(lblPlacar);
        
   	 	/**
   	 	 * Informações que somente aparecem enquanto o round está sendo jogado
   	 	 */
        if(!fimRound) {
            g.setFont(fredoka.deriveFont((float) 16));
             
        	g.drawString("Selecione sua jogada clicando", 370, 520);
        	g.drawString("sobre a seta correspondente!", 370, 535);   
        	
       	 	g.setFont(fredoka.deriveFont((float) 16));
       	 	
        	if(!adversarioJogou) {
				g.drawString("Aguardando jogada", 690, 520);
				g.drawString("adversária", 725, 545);
           } else {
           		g.drawString("Jogada Confirmada!", 690, 390);
           }
        }
        /**
         * Fim do round
         */
        else {
        	lblTimer.setVisible(false);
        	
        	if(nomeVencedor != null && !nomeVencedor.equalsIgnoreCase("null")){
                g.setFont(fredoka.deriveFont((float) 40));
                g.drawString(nomeVencedor, 410, 380);
            	g.drawString("venceu o round!", 340, 420);
        	} else {
        		g.setFont(fredoka.deriveFont((float) 50));
            	g.drawString("Empate!", 390, 390);
        	}
        }
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public void setJogadas(String[] jogadas) {
		this.jogadaArdversario = (getJogada().equals(jogadas[0])) ? (jogadas[1]) : (jogadas[0]);
		validate();
		repaint();
	}
	
	public void setAdversarioJogou(boolean adversarioJogou) {
		if(this.adversarioJogou)
			return;
		
		this.adversarioJogou = adversarioJogou;
		if(adversarioJogou)
			lblLupa.setVisible(false);
		validate();
		repaint();
	}
	
	public void setPlacar(String[] placar) {
		this.placar = placar;
		validate();
		repaint();
	}
	
	public void setNomeVencedorRound(String nomeVencedor) {
		fimRound = true;
		btnConfirm.setVisible(false);
		this.nomeVencedor = nomeVencedor;
		validate();
		repaint();
	}
	
	public void nextRound() {
		/**
		 * Refaz todas as configurações iniciais
		 */
		jogadaArdversario = null;
		adversarioJogou = false;
		fimRound = false;
		nomeVencedor = null;
		
		lblLupa.setVisible(true);
		
		btnConfirm.setText("Confirmar");
		btnConfirm.setEnabled(true);
		btnConfirm.setVisible(true);
		
		lblTimer.setVisible(true);
		lblTimer.setText("01:00");
		segundos = 00;
		minutos = 1;
		countdownTimer();
		timer.start();
		
		for (Enumeration<AbstractButton> buttons = radioGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.setEnabled(true);
        }
		
		validate();
		repaint();
	}
	
	public String getJogada() {
		String buttonName = null;
		
		for (Enumeration<AbstractButton> buttons = radioGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.setEnabled(false);

            if (button.isSelected())
                buttonName = button.getName();
        }
        return buttonName;
	}
	
	public void countdownTimer() {
		
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				segundos--;
				ddSegundos = dFormat.format(segundos);
				ddMinutos = dFormat.format(minutos);	
				lblTimer.setText(ddMinutos + ":" + ddSegundos);
				
				if(segundos == -1) {
					segundos = 59;
					minutos--;
					ddSegundos = dFormat.format(segundos);
					ddMinutos = dFormat.format(minutos);	
					lblTimer.setText(ddMinutos + ":" + ddSegundos);
				}
				if(minutos == 0 && segundos == 0) {
					timer.stop();
					btnConfirm.doClick();
					lblTimer.setVisible(false);
				}
			}
		});		
	}
}

