package conectividade.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import conectividade.Flag;

/**
 * Classe respons�vel por receber e enviar mensagens entre o Servidor e os Clientes
 */
public class RequestHandler extends Thread {
	private Socket socket;
	private Server server;
	private BufferedReader in;
	private PrintWriter out;

	RequestHandler(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		this.setName("Server RequestHandler" + socket.getInetAddress());
		
		/**
		 * in - Input de mensagens do cliente
		 * out - Output de mensasens para o cliente
		 */
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		try {
			/**
			 * Loop que fica constantemente verificando novas mensagens do cliente
			 */
			boolean stop = false;
			do {
				String[] line = in.readLine().split(":");
				String flag = line[0];
				String value = line[1];
				
				if("NICKNAME".equals(flag)) {
					adicionarJogador(value);
				}
				
				if("STOP".equals(flag)) {
					stop = true;
					server.stopServer();
				}
				
				if("JOGADA".equals(flag)) {
					String name = line[2];
					boolean fazerJogada = server.getJogo().armazenaJogada(name, value);
					
					if(fazerJogada) { 
						String nomeVencedor = server.getJogo().fazJogada();
						server.sendToClients(Flag.VENCEDOR + nomeVencedor);
						server.sendToClients(Flag.VENCEDORFINAL + server.getJogo().verificaFim());
					}
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

	private void adicionarJogador(String nomeJogador) {
		server.getNomesJogadores().add(nomeJogador);
	}
	
	/**
	 * @param message - Mensagem a ser enviada para o servidor
	 */
	public void sendToClient(String message) {
		out.println(message);
		out.flush();
	}
}