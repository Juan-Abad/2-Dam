import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		int puerto=5000;
		Monedero m = new Monedero();
		File f = new File("donaciones.txt");
		
		
	try {
		ServerSocket server = new ServerSocket(puerto);
		System.out.println("Servidor escuchando en el puerto 5000");
		while(true) {
			Socket s = server.accept();			
			ClientHandler c = new ClientHandler(s, m);
			c.start();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
