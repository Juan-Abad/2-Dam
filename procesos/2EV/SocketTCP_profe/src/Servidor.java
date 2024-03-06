import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		int puerto = 5000;
		
		
		try {
			
			ServerSocket server = new ServerSocket(puerto);
			System.out.println("Servidor esperando en el puerto: "+puerto);	
			while(true) {
				Socket s = server.accept();
				ClienteHandler ch = new ClienteHandler(s);
				ch.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
