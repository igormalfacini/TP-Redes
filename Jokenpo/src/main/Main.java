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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import conectividade.client.Client;
import conectividade.server.Server;
import telas.CreateRoom;
import telas.JoinRoom;
import telas.Lobby;
import telas.Menu;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Leitores
	 */
	private static Scanner scanner = new Scanner(System.in);
	private static BufferedReader reader;
	
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
	private Lobby lobby;
	
	/**
	 * Conectividade
	 */
	private static boolean hasServer;
	public static Server server;
	public static Client client;
	public static int defaultPort = 12345;
	public static File ipServer = new File("ip.txt");
	
	public void initComponents()
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
	
	/**
	 * Configuração de Action Listeners
	 */
	
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

				server = getServerByIpTxt(ipServer);
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
				//TODO Adicionar exceção para servidor não encontrado
				
				String ip = joinRoom.getRoomCode().getText();
				byte[] address = convertStringToAddress(ip);
						
				client = new Client(defaultPort, address);
				client.startClient();
				
				//TODO Tela de jogo
				remove(joinRoom);
				lobby = new Lobby();
				add(lobby);
				validate();
				repaint();
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
	
	private byte[] convertStringToAddress(String ip) {
		String[] addressString = ip.split("\\.");
		int tam = addressString.length;
		byte[] address = new byte[tam];

		for (int i = 0; i < addressString.length; i++) {
			address[i] = (byte) Integer.parseInt(addressString[i]);
		}
		
		return address;
	}
	
	private Server getServerByIpTxt(File file) {
		if (file.exists()) {
			try {
				reader = new BufferedReader(new FileReader(file.getName()));
				String ip = reader.readLine();
				byte[] address = convertStringToAddress(ip);
				return new Server(defaultPort, address);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Iniciando servidor em 'localhost'");
			}
			
		}
		
		return new Server(defaultPort, "localhost");
	}
	
	public static void main(String[] args) {
		
		Main mainWindow = new Main();
		mainWindow.initComponents();
		
				
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
