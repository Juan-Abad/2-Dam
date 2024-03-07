import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorJuanAbad {//clase que recive las conexiones de los clientes y lanza los hilos
	public static void main(String[] args) {
		int puerto=5000;	
		
	try (ServerSocket server = new ServerSocket(puerto);){
		System.out.println("Servidor escuchando en el puerto 5000");
		while(true) {
			Socket s = server.accept();
			ClientHandlerJuanAbad c = new ClientHandlerJuanAbad(s);
			c.start();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
