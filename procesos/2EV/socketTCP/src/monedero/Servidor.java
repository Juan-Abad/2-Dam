package monedero;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		int puerto = 5000;
		Monedero m = new Monedero();

		try {
			ServerSocket server = new ServerSocket(puerto);
			System.out.println("Servidor esperando en el puerto: " + puerto);
			while (true) {
				Socket s = server.accept();
				ClientHandler clientHandler = new ClientHandler(s, m);
				clientHandler.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
