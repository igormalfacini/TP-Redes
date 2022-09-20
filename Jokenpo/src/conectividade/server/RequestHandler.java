package conectividade.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Disponível em
 * https://www.infoworld.com/article/2853780/socket-programming-for-scalable-systems.html
 */
public class RequestHandler extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			/**
			 * in - Input de mensagens do cliente
			 * out - Output de mensasens para o cliente
			 */
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

			// Echo lines back to the client until the client closes the connection or we
			// receive an empty line
			String line = in.readLine();
			while (line != null && line.length() > 0) {
				if(line.equalsIgnoreCase("ping"));
				out.println("Pong!");
				out.flush();
				line = in.readLine();
			}

			// Close our connection
			in.close();
			out.close();
			socket.close();

			System.out.println("Conexão encerrada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}