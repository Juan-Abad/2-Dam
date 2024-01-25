package monedero;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		int puerto = 5000;
		Juego juego = new Juego();
		int jugadores = 0;

		try {
			ServerSocket server = new ServerSocket(puerto);
			System.out.println("Servidor esperando en el puerto: " + puerto);
			while (jugadores < 2) {
				Socket s = server.accept();
				jugadores ++;
				ClientHandler clientHandler = new ClientHandler(s, juego, jugadores);
				clientHandler.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
