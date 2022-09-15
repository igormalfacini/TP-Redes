package conectividade;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	private ServerSocket serverSocket;
	private String hostName;
	private int port;

	public Server(int porta) {
		this.port = porta;
	}

	@Override
	public void run() {
		while (true) {
			try {
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
			System.out.println("Iniciando Servidor na porta " + port + "...");
			serverSocket = new ServerSocket(port);
			
			byte[] b = InetAddress.getByName("localhost").getAddress();
			System.out.println(b[0] + "." + b[1] + "." + b[2] + "." + b[3]);
			System.out.println(serverSocket.getLocalSocketAddress());
			System.out.println(InetAddress.getByAddress(b).getHostName());
			
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopServer() {
		System.out.println("Encerrando Servidor...");
		this.interrupt();
	}

	public static void main(String[] args) {
		int port = 12345;

		Server server = new Server(port);
		server.startServer();

		// Automatically shutdown in 1 minute
		try {
			Thread.sleep(60000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		server.stopServer();
	}
}