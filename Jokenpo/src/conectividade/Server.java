package conectividade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread
{
	private static ArrayList<BufferedWriter> clientes;
	private static ServerSocket server;
	private String nome;
	private Socket socket;
	private InputStream inputStream;
	private InputStreamReader inputReader;
	private BufferedReader bfr;
	
	public Server(Socket socket)
	{
	   this.socket = socket;
	   try {
	         inputStream  = socket.getInputStream();
	         inputReader = new InputStreamReader(inputStream );
	          bfr = new BufferedReader(inputReader);
	   } catch (IOException e) {
	          e.printStackTrace();
	   }
	}
	
	/**
	  * Método run
	  */
	public void run() 
	{

		try {
			String msg;
			OutputStream ou = this.socket.getOutputStream();
			Writer ouw = new OutputStreamWriter(ou);
			BufferedWriter bfw = new BufferedWriter(ouw);
			clientes.add(bfw);
			nome = msg = bfr.readLine();

			while (!"Sair".equalsIgnoreCase(msg) && msg != null) 
			{
				msg = bfr.readLine();
				// sendToAll(bfw, msg);
				System.out.println(msg);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
