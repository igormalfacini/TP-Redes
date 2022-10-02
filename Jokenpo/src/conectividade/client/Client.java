package conectividade.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket clientSocket;
	private int port;
	private byte[] address;
	
	private PrintWriter out;
	private BufferedReader in; 

	public Client(int port, byte[] address) {
		this.port = port;
		this.address = address;
	}

	public Client(int port, String hostName) {
		this.port = port;
		try {
			this.address = InetAddress.getByName(hostName).getAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public boolean startClient() {
		try {
			clientSocket = new Socket(InetAddress.getByAddress(address), port);
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String sendToServer(String message) {
		try {
			out = new PrintWriter(clientSocket.getOutputStream());
			out.println(message);
			out.flush();
			
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String response = (String) in.readLine();

			return response;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}
	
	public String ping() {
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
			out.println("Ping");
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String response = (String) in.readLine();

			return response;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}
}
