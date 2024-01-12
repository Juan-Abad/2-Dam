import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {

	static final int PUERTO = 5000;

	public Servidor() {

	}
	
	public void run() {
		try {

			ServerSocket skServidor = new ServerSocket(PUERTO);

			System.out.println("Escucho el puerto " + PUERTO);

			for (int numCli = 0; numCli < 3; numCli++) {

				Socket skCliente = skServidor.accept();

				System.out.println("Sirvo al cliente " + numCli);

				OutputStream aux = skCliente.getOutputStream();

				DataOutputStream flujo = new DataOutputStream(aux);

				flujo.writeUTF("Hola cliente " + numCli);
				
				Thread.sleep(500);

			}

			System.out.println("Demasiados clientes por hoy");

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}
}
