package socketTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
	Socket s;

	public ClientHandler(Socket s) {
		super();
		this.s = s;
	}

	public void run() {
		// Manejador del hilo
		try (BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter pr = new PrintWriter(s.getOutputStream());) {
			pr.println("Hola");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
