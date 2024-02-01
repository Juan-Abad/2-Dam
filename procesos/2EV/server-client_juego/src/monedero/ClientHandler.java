package monedero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
	Socket s;
	Juego juego;
	int numero_jugador;

	public ClientHandler(Socket s, Juego juego, int numero_jugador) {
		super();
		this.s = s;
		this.juego = juego;
		this.numero_jugador = numero_jugador;
	}

	public void run() {
		// Manejador del hilo
		int loop;
		String numero;
		try (DataInputStream dr = new DataInputStream(s.getInputStream());
				DataOutputStream dw = new DataOutputStream(s.getOutputStream());) {
			dw.writeUTF("Hola jugador " + numero_jugador);
			dr.readUTF();
			do {
				numero = dr.readUTF();
				System.out.println("numero:" + numero + ", del jugador " + numero_jugador);
				do {
					loop = juego.recibe_numero(numero, this);
					if (loop == 0) {
						dw.writeUTF("numero_aceptado");
					} else {
						dw.writeUTF("numero_denegado");
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (loop == -1);
			} while (true);

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
