package socketUDP_ejemplo;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPcliente {
	public static void main(String args[]) throws Exception {
		//InetAddress host = InetAddress.getByName("localhost");
		InetAddress host = InetAddress.getLocalHost();

		DatagramSocket socket = new DatagramSocket();
		DatagramSocket socketJuego = new DatagramSocket();
		MessageSender s = new MessageSender(socket, host);
		MessageReceiver r = new MessageReceiver(socket, s, socketJuego);

		Thread st = new Thread(s);
		Thread rt = new Thread(r);

		st.start();
		rt.start();
	}
}