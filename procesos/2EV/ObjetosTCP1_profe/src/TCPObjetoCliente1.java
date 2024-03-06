import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * El programa cliente recibe un objeto persona del servidor
 * Cambia los datos y los reenv�a de nuevo.
 * @author juanjo
 *
 */

public class TCPObjetoCliente1 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		
		//Creo el socket para conectarme al servidor
		String host="localhost"; //poner la direccion IP o el nombre de equipo, el ejemplo del profesor en clase es: ServidorDAM
		int puerto=6000;
		
		Socket cliente=new Socket(host,puerto);
		
		//flujo de entrada
		ObjectInputStream perEntrada=new ObjectInputStream(cliente.getInputStream());
		//Extraigo el objeto
		Persona dato=(Persona) perEntrada.readObject();
		System.out.println("Dato recibido. "+ dato.getNombre()+ " "+ dato.getEdad());
		
		//modifico el objeto
		dato.setNombre(dato.getNombre()+ " modificado");
		dato.setEdad(dato.getEdad()+10);
		
		//Flujo de salida para objetos
		ObjectOutputStream perSalida=new ObjectOutputStream(cliente.getOutputStream());
		//Envio el objeto
		perSalida.writeObject(dato);
		
		System.out.println("Dato Enviado. "+ dato.getNombre()+ " "+ dato.getEdad());
		
		//Cerrar streams y sockets
		perEntrada.close();
		perSalida.close();
		cliente.close();
		
		
		
		
		
		
	}

}
