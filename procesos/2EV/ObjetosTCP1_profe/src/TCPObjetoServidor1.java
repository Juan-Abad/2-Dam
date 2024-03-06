import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * El programa servidor crea un objeto  y lo envía al cliente
 * El cliente cambia los valores y se los devuelve al servidor
 */


public class TCPObjetoServidor1 {
	public static void main(String [] args) throws IOException, ClassNotFoundException {
		// Creamos el socket del servidor
		int puerto=6000;
		ServerSocket servidor=new ServerSocket(puerto);
		System.out.println("Esperando cliente.....");
		//Esperamos a recibir una petición y  creamos el socket de comunicación
		//Solo un cliente
		Socket cliente=servidor.accept();
		
		//Se perpara el flujo de entrada/salida para los objetos.
		//el socket recibe y envia byteStream mediante getInputStream() getOutputStream()
		// hay qu e transformarlo en ObjectOutputStream y ObjectInputStream
		
		//Creo un objeto persona  y lo envio
		Persona per=new Persona("Juan",35);
				
		//Se crea un ObjectoutputStream y un outputStream para el socket
		ObjectOutputStream outObjeto=new ObjectOutputStream(cliente.getOutputStream());
		//Se añade la persona con los datos modificados
		outObjeto.writeObject(per);
		System.out.println("Dato enviado "+ per.getNombre()+ " "+ per.getEdad());
		
		//Leo el objeto modificado del Socket
		//Se obtiene Stream para leer objetos
		ObjectInputStream inObjeto=new ObjectInputStream(cliente.getInputStream());
		//Se extrae el objeto persona on readObject();
		
		Persona dato=(Persona) inObjeto.readObject();
		System.out.println("Dato recibido "+ dato.getNombre()+ " "+ dato.getEdad());
		
		
		
		
		// Cerrar Stream y sockets
		outObjeto.close();
		inObjeto.close();
		cliente.close();
		servidor.close();
		
		
		
		
		
		
	}

}
