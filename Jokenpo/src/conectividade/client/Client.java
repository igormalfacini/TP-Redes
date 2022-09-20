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

	public void startClient() {
		try {
			clientSocket = new Socket(InetAddress.getByAddress(address), port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String ping() {
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
			out.println("Ping");
			out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String response = (String) in.readLine();
			in.close();

			return response;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}
}
