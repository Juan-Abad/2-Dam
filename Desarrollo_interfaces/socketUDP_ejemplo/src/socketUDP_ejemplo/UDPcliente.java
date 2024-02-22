package socketUDP_ejemplo;

import java.net.DatagramSocket;

public class UDPcliente {
	public static void main(String args[]) throws Exception {
		String host = "192.168.1.119";

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
