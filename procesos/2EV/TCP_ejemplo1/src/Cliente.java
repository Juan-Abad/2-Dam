import java.net.*;
import java.io.*;

public class Cliente {
	static final String SERVIDOR = "localhost";
	static final int PUERTO = 6000;

//Constructor
	public Cliente() {
		try {
			Socket SocketC = new Socket(SERVIDOR, PUERTO);
			InputStream is = SocketC.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			System.out.println(dis.readUTF());
			SocketC.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {
		new Cliente();
	}
}