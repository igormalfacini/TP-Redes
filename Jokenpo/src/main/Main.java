package main;

import java.util.Scanner;

import conectividade.client.Client;
import conectividade.server.Server;
import graficos.Game;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Conectividade
	 */
	private static boolean hasServer;
	
	private static Server server;
	private static Client client;
	
	/**
	 * Interface Gráfica
	 */
	
	private Game game;

	public static void main(String[] args) {
		
		Game game = new Game();
		
		System.out.println("Start server? \n1- yes \n2- no");
		int startServer = scanner.nextInt();
		
		hasServer = (startServer == 1) ? (true) : (false);
		
		//ip radmin
		//byte[] address = {(byte) 26, (byte) 158, (byte) 211, (byte) 135};
		
		if(hasServer) {
			
			server = new Server(12345, "localhost");
			server.startServer();	
		}
		
		client = new Client(12345, "localhost");
		client.startClient();
		
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
