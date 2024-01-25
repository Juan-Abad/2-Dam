package monedero;

import java.io.Serializable;
import java.util.TreeMap;

public class Juego implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int turno = 0;
	private int sesion = 0;
	private String[] jugadores;
	private int puntos_jugador1 = 0;
	private int puntos_jugador2 = 0;
	private TreeMap<Integer, TreeMap<Integer, Integer>> mapa = new TreeMap();
	private TreeMap<Integer, Integer> mapa2 = new TreeMap();

	public Juego() {

	}

	synchronized public int recibe_numero(String numero, ClientHandler cliente) {
		if (sesion == cliente.numero_jugador - 1) {
			if (sesion == 0) {
				mapa2.put(cliente.numero_jugador, Integer.parseInt(numero));
				mapa.put(turno, mapa2);
			} else {
				mapa2.put(cliente.numero_jugador, Integer.parseInt(numero));
			}
			System.out
					.println("turno " + turno + ", jugador " + cliente.numero_jugador + ", introduce numero " + numero);
			sesion++;
			if (cliente.numero_jugador == 2) {
				turno++;
				mapa2.clear();
			}
			return 0;
		} else {
			System.out.println("a este jugador no le toca, turno " + turno + ", jugador " + cliente.numero_jugador);
			return -1;
		}

	}

}
