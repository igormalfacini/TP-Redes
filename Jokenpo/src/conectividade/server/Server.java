package conectividade.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server extends Thread {

	private ServerSocket serverSocket;
	private int port;
	private byte[] address;

	public Server(int port, byte[] address) {
		this.port = port;
		this.address = address;
		this.setName("Server");
	}
	
	public Server(int port, String hostName) {
		this.port = port;
		try {
			this.address = InetAddress.getByName(hostName).getAddress();
			this.setName("Server");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				if(serverSocket.isClosed()) {
					break;
				}
				
				System.out.println("Aguardando conexão");

				Socket cliente = serverSocket.accept();
		        System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
		  
		        RequestHandler requestHandler = new RequestHandler(cliente);
				requestHandler.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void startServer() {
		try {
			System.out.println("Iniciando Servidor no endereço " 
						+ InetAddress.getByAddress(address).getHostAddress() + ":" + port + "...");
			serverSocket = new ServerSocket(port);
			
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopServer() {
		System.out.println("Encerrando Servidor...");
		this.interrupt();
		try {
			serverSocket.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}