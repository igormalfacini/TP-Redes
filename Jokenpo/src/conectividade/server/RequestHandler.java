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
		this.setName("RequestHandler" + socket.getInetAddress());
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

			/**
			 * Loop que fica constantemente verificando novas mensagens do cliente
			 */
			while (true) {
				String line = in.readLine();
				if("ping".equalsIgnoreCase(line)){
					out.println("Pong!");
					out.flush();	
				}
				else {
					out.println("No response available");
					out.flush();
				}
					
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}