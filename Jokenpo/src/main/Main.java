package main;

import java.util.Scanner;

import conectividade.client.Client;
import conectividade.server.Server;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);;
	private static boolean hasServer;
	
	private static Server server;
	private static Client client;

	public static void main(String[] args) {
		
		System.out.println("Start server? \n1- yes \n2- no");
		int startServer = scanner.nextInt();
		
		hasServer = (startServer == 1) ? (true) : (false);
		
		if(hasServer){
			server = new Server(12345, "localhost");
			server.startServer();	
		}
		
		client = new Client(12345, "localhost");
		client.startClient();
		
		String message = "";
		while(!message.equalsIgnoreCase("stop"))
		{
			message = scanner.next();
			System.out.println(client.sendToServer(message));
		}		
		
		if(hasServer)
			server.stopServer();
	}
}
