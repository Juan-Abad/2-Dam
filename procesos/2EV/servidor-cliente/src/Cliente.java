import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente extends Thread{

	static final String HOST = "localhost";
	static final int PUERTO = 5000;

	public Cliente() {

	}
	
	public void run() {
		try {

			Socket skCliente = new Socket(HOST, PUERTO);
			
			InputStream aux = skCliente.getInputStream();

			DataInputStream flujo = new DataInputStream(aux);

			System.out.println(flujo.readUTF());

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}
}
