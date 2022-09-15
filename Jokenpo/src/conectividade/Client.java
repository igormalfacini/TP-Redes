package conectividade;

import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) {
	    try {
	      Socket cliente = new Socket("kubernetes.docker.internal", 12345);
	      ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
	      String serverMsg = (String) entrada.readObject();
	      JOptionPane.showMessageDialog(null,"Mensagem recebida do servidor:" + serverMsg.toString());
	      entrada.close();
	      System.out.println("Conexão encerrada");
	    }
	    catch(Exception e) {
	      System.out.println("Erro: " + e.getMessage());
	    }
	  }
}
