package conectividade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) {
	    try {
	    	InetAddress address = InetAddress.getByName("localhost");
			Socket cliente = new Socket(address, 12345);
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			//ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
			
			String serverMsg = (String) in.readLine();
			
			System.out.println("Mensagem recebida do servidor: " + serverMsg.toString());
			in.close();
			
			System.out.println("Conexão encerrada");
	    }
	    catch(Exception e) {
	      System.out.println("Erro: " + e.getMessage());
	    }
	  }
}
