package socketUDP_ejemplo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.google.gson.Gson;

public class MessageSender implements Runnable {
	public final static int PORT = 49167;
	private DatagramSocket sock;
	private String hostname;
	private volatile boolean connected = false;
	private Gson gson;
	private byte buf[];

	MessageSender(DatagramSocket s, String h) {
		sock = s;
		hostname = h;
		gson = new Gson();
		this.buf = new byte[1024];
	}

	public void sendMessage(Object mensaje, InetAddress address, int port) throws Exception {
		String mensajeJson = gson.toJson(mensaje);
		byte buf[] = mensajeJson.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		sock.send(packet);
	}

	public void run() {
		try {
			do {
				Mensajes.MensajeRegistro mensajeRegistro = new Mensajes.MensajeRegistro("Juan");
				sendMessage(mensajeRegistro, InetAddress.getByName(hostname), PORT);
				Thread.sleep(1000);
			} while (!connected);
			System.out.println("conectado");
		} catch (Exception e) {
			System.err.println(e);
		}
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public static int getPort() {
		return PORT;
	}

	public DatagramSocket getSock() {
		return sock;
	}

	public String getHostname() {
		return hostname;
	}
	
}
