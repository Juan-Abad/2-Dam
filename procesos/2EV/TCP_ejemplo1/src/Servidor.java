import java.net.*;
import java.io.*;

class Servidor {
	static final int PUERTO = 6000;

	//Constructor
	public Servidor() {
		try {
			ServerSocket SocketS = new ServerSocket(PUERTO);
			System.out.println("Servidor escuchando por el puerto " + PUERTO);
			for (int i = 0; i < 3; i++) {
				//Crea el objeto socket cliente
				Socket SocketC = SocketS.accept();
				System.out.println("Escuchando al cliente " + i);
				OutputStream os = SocketC.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeUTF("Hola cliente " + i);
				SocketC.close();
			}
			System.out.println("El servidor termina");
			SocketS.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {
		new Servidor();
	}
}
