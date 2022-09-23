package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import conectividade.client.Client;
import conectividade.server.Server;
import telas.CreateRoom;
import telas.JoinRoom;
import telas.Menu;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Dimensões
	 */
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	
	/**
	 * Estilização
	 */
	public static final Color CUSTOMIZED_BLUE = new Color(119, 179, 214);
	public static Font fredoka; 
	
	/**
	 * Panels
	 */
	private Menu menu;
	private JoinRoom joinRoom;
	private CreateRoom createRoom;
	
	/**
	 * Conectividade
	 */
	private static boolean hasServer;
	private static Server server;
	private static Client client;
	private static int defaultPort = 12345;
	
	public Main()
	{
		/**
		 * Inicialização da tela
		 */
		configureFont();
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		getContentPane().setLayout(new FlowLayout(0, 0, 0));
		
		setTitle("Jokenpô!");
		setResizable(false);
		
		/**
		 * A tela se inicia com o menu
		 */
		menu = new Menu();
		configureMenuActionListeners(menu);
		add(menu);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));

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
	
	private void configureMenuActionListeners(Menu menu) {
		
		menu.getBtnJoin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Redesenha a tela
				 */
				remove(menu);
				joinRoom = new JoinRoom();
				configureJoinRoomActionListeners(joinRoom);
				add(joinRoom);
				validate();
				repaint();
			}
		});
		
		menu.getBtnCreateRoom().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Inicia Servidor
				 */
				server = new Server(defaultPort, "localhost");
				server.startServer();
				hasServer = true;
				
				/**
				 * Redesenha a tela
				 */
				remove(menu);
				createRoom = new CreateRoom();
				configureCreateRoomActionListeners(createRoom);
				add(createRoom);
				validate();
				repaint();
			}
		});
		
		menu.getBtnExit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void configureJoinRoomActionListeners(JoinRoom joinRoom) {
		joinRoom.getBtnJoin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Adicionar exceção para servidor não encontrado
				
				//Pegar ip do textfield
				client = new Client(defaultPort, "localhost");
				client.startClient();
				
				//Tela de jogo
			}
		});
		
		joinRoom.getBtnBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Redesenha a tela
				 */
				remove(joinRoom);
				add(menu);
				validate();
				repaint();
			}
		});
	}
	
	private void configureCreateRoomActionListeners(CreateRoom createRoom) {
		createRoom.getBtnCancel().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.stopServer();
				
				/**
				 * Redesenha a tela
				 */
				remove(createRoom);
				add(menu);
				validate();
				repaint();
			}
		});
	}
	
	public static void main(String[] args) {
		
		Main mainWindow = new Main();
				
		//ip radmin
		//byte[] address = {(byte) 26, (byte) 158, (byte) 211, (byte) 135};

		/**
		 * Loop principal
		 * 
		 * Encerrado ao informar "stop"
		 */
		String message = "";
		do {
			message = scanner.next();
			System.out.println(client.sendToServer(message));
		} while(!message.equalsIgnoreCase("stop"));
		
		if(hasServer)
			server.stopServer();
	}
}
