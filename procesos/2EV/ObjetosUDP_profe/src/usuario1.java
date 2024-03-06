import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class usuario1 {

	public static void main(String[] args) {

		try {
			DatagramSocket socket=new DatagramSocket(12346);
			Persona persona = new Persona("Marina", 22);
			// convertimos el objeto a bytes
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bs);
			out.writeObject(persona);// escribimos el objeto persona en el stream
			out.close(); // cerramos el stream
			byte[] bytes = bs.toByteArray(); // objeto en bytes
			socket.send(new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 12345));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
