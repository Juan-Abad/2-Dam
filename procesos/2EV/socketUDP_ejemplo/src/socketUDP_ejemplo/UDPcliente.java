package socketUDP_ejemplo;
/*
 * Envia un mensaje al servidor
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UDPcliente {

	public static void main(String[] args) throws Exception {
		
		//Direccion de destino
		InetAddress destino=InetAddress.getByName("localhost");
		//InetAddress destino=InetAddress.getByName("192.168.100.1 poner ip destino");  
		//puerto de destino
		int port=12345;
		//mensaje
		byte[] mensaje=new byte [1024];
		String saludo="Saludo desde el cliente!!!";
		mensaje= saludo.getBytes(); //paso el mensaje a byte[]
		
		//Cosntruyo el datagrama a enviar
		
		DatagramPacket envio=new DatagramPacket (mensaje, mensaje.length, destino, port);
		DatagramSocket socket=new DatagramSocket (3456); //puerto local
		System.out.println("Enviando datagrma ...");
		System.out.println("Longitud "+ mensaje.length);
		System.out.println("Host destino"+ destino.getHostName());
		System.out.println("IP destino "+ destino.getHostAddress());
		System.out.println("Puerto local del socket "+ socket.getLocalPort());
		System.out.println("Puerto getPort "+ socket.getPort());
		System.out.println("Puerto al que envio "+ envio.getPort());
		
		//Envio datagrama
		socket.send(envio);
		socket.close();
		
		
		
		
		
		
		
		
	}

}
