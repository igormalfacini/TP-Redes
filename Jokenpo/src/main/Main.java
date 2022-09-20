package main;

import conectividade.client.Client;
import conectividade.server.Server;

public class Main {
	
	public static void main(String[] args) {
		Server server = new Server(12345, "localhost");
		server.startServer();
		
		Client client = new Client(12345, "localhost");
		client.startClient();
		
		System.out.println("Ping");
		System.out.println(client.ping());
	}
}
