package socketUDP_ejemplo;

public class Mensajes {

	static String tipo;

	static class MensajeRegistro {
		String tipo;
		String nombre;

		public MensajeRegistro(String nombre) {
			this.tipo = "registro";
			this.nombre = nombre;
		}
	}

	static class MensajeSimbolo_jugador {
		String tipo = "Simbolo jugador";
		Boolean isPlayerX;

		public MensajeSimbolo_jugador(Boolean isPlayerX) {
			this.isPlayerX = isPlayerX;
		}
	}

	static class MensajeEspera {
		String tipo = "Espera";
		String descripcion = "Espera a tu turno";
	}

	static class MensajeJuega {
		String tipo = "Juega";
		String descripcion = "Turno de jugar";
	}

	static class MensajeJugadaErronea {
		String tipo = "Jugada erronea";
	}

	static class MensajeJugada {
		String tipo = "Jugada";
		int fila;
		int columna;

		public MensajeJugada(int fila, int columna) {
			this.fila = fila;
			this.columna = columna;
		}
	}

	static class MensajeComprobarConexion {
		String tipo;

		public MensajeComprobarConexion() {
			this.tipo = "comprobar_conexion";
		}
	}

	static class MensajeComprobarConexion_ping {
		String tipo;
		String mensaje;

		public MensajeComprobarConexion_ping() {
			this.tipo = "cliente_ping_servidor";
			this.mensaje = "sigo conectado";
		}
	}

	static class MensajeMovimiento {
		String tipo;
		int fila;
		int columna;

		public MensajeMovimiento(int fila, int columna) {
			this.tipo = "movimiento";
			this.fila = fila;
			this.columna = columna;
		}
	}

	static class MensajeDesconexion {
		String tipo;

		public MensajeDesconexion() {
			this.tipo = "desconexion";
		}
	}

	static class MensajeResultado {
		String tipo;
		String resultado;
		String[][] tablero;

		public MensajeResultado(String resultado, String[][] tablero) {
			this.tipo = "resultado_juego";
			this.resultado = resultado;
			this.tablero = tablero;
		}
	}

	static class MensajeActualizacionTablero {
		String tipo;
		String[][] tablero;

		public MensajeActualizacionTablero(String[][] tablero) {
			this.tipo = "actualizacion_tablero";
			this.tablero = tablero;
		}
	}
}
