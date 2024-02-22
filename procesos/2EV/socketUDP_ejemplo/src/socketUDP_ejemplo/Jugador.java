package socketUDP_ejemplo;

import java.net.InetAddress;

public class Jugador {
	private InetAddress address;
	private int port;
	private Boolean isPlayerX = null;
	
	public Jugador(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Boolean isPlayerX() {
		return isPlayerX;
	}

	public void setPlayerX(Boolean isPlayerX) {
		this.isPlayerX = isPlayerX;
	}
}
