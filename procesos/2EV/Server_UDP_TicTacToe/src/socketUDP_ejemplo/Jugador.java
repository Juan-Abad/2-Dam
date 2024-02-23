package socketUDP_ejemplo;

import java.net.InetAddress;

public class Jugador {
	private InetAddress address;
	private int port;
	private Boolean isPlayerX = null;
	private Integer idJugador;
	private static Integer idSiguiente = 1;

	public Jugador(InetAddress address, int port) {
		this.address = address;
		this.port = port;
		this.idJugador = idSiguiente;
		idSiguiente++;
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

	public Boolean getIsPlayerX() {
		return isPlayerX;
	}

	public void setIsPlayerX(Boolean isPlayerX) {
		this.isPlayerX = isPlayerX;
	}

	public Integer getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}
}
