package conectividade.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
			boolean stop = false;
			do {
				String line = in.readLine();
				if("ping".equalsIgnoreCase(line)){
					out.println("Pong!");
					out.flush();	
				}
				else if("stop".equalsIgnoreCase(line)) {
					out.println("Encerrando Conexão");
					out.flush();
					
					stop = true;
				}
				else {
					out.println("No response available");
					out.flush();
				}
			} while (!stop);
			
			in.close();
			out.close();
			socket.close();
			this.interrupt();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}