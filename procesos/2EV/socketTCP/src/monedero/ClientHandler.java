package monedero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
	Socket s;
	Monedero m;

	public ClientHandler(Socket s, Monedero m) {
		super();
		this.s = s;
		this.m = m;
	}

	public void run() {
		// Manejador del hilo
		try (DataInputStream dr = new DataInputStream(s.getInputStream());
				DataOutputStream dw = new DataOutputStream(s.getOutputStream());) {
			int cantidad = dr.read();
			m.donar(cantidad, s.getInetAddress());
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
