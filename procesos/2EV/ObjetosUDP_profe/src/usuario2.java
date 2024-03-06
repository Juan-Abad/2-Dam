import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class usuario2 {

	public static void main(String[] args) {

		 try {
			 DatagramSocket socket=new DatagramSocket (12345); //puerto local
				//Recibo datagrama
				 byte[] recibidos = new byte[1024];
				 DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
				 socket.receive(paqRecibido); //recibo el datagrama
				 //convertimos bytes a objeto
				 ByteArrayInputStream b = new ByteArrayInputStream(recibidos);
				 ObjectInputStream in = new ObjectInputStream(b);
				 Persona persona = (Persona) in.readObject(); //obtengo el objeto
				 System.out.println(persona.toString()+" usuario2");
				 in.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //obtengo el objeto

	}

}
