package socketUDP_ejemplo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class UDPservidor {
	// Lista para almacenar las direcciones IP de los clientes conectados
	private static List<Jugador> clientesConectados = new ArrayList<>();
	private static Game game = new Game();

	public static void main(String[] args) throws Exception {
		byte[] bufer = new byte[1024]; // Buffer para recibir el datagrama
		DatagramSocket socket = new DatagramSocket(12345); // Asociar el DatagramSocket al puerto 12345
		while (true) {
			// Esperar datagrama
			System.out.println("Esperando Datagrama ........");
			DatagramPacket recibido = new DatagramPacket(bufer, bufer.length);
			socket.receive(recibido); // Recibir el datagrama
			int bytesRec = recibido.getLength();
			String paquete = new String(recibido.getData(), 0, bytesRec); // Obtener el JSON de los datos del datagrama

			// Parsear el JSON utilizando Gson
			Gson gson = new Gson();
			try {
				System.out.println(paquete);
				// Comprobar el tipo y actuar en consecuencia
				switch (gson.fromJson(paquete, JsonObject.class).get("tipo").getAsString()) {
				case "registro":
					if (clientesConectados.size() < 3) {
						clientesConectados.add(new Jugador(recibido.getAddress(), recibido.getPort()));
						System.out.println("Cliente registrado: " + recibido.getAddress());
						System.out.println(recibido.getPort());
					} else {
						System.out.println("No se pudo registrar el cliente " + recibido.getAddress());
					}
					if (clientesConectados.size() == 2) {
						empezarJuego(socket);
					}
					break;
				case "cliente_ping_servidor":
					// Aquí podrías implementar la lógica para responder al servidor
					// con un mensaje indicando que el cliente está conectado
					Mensajes.MensajeComprobarConexion_ping mensajeRespuesta = new Mensajes.MensajeComprobarConexion_ping();
					String mensajeRespuestaJson = gson.toJson(mensajeRespuesta);
					byte buf[] = mensajeRespuestaJson.getBytes();
					DatagramPacket packet = new DatagramPacket(buf, buf.length, recibido.getAddress(),
							recibido.getPort());
					socket.send(packet);
					break;
				default:
					// Aquí podrías manejar otros tipos de mensajes si es necesario
					break;
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	public static void empezarJuego(DatagramSocket socket) {
		boolean turnoJugador1 = true, jugadaErronea = false;
		Gson gson = new Gson();
		byte[] bufer = new byte[1024];
		DatagramPacket recibido = new DatagramPacket(bufer, bufer.length);
		int jugador_n = 0;
		while (true) {
			// Determinar de quién es el turno
			if (turnoJugador1) {
				jugador_n = 0;
			} else {
				jugador_n = 1;
			}
			Mensajes.MensajeJuega mensajeJuega = new Mensajes.MensajeJuega();
			String mensajeRespuestaJson = gson.toJson(mensajeJuega);
			byte buf[] = mensajeRespuestaJson.getBytes();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, clientesConectados.get(jugador_n).getAddress(),
					clientesConectados.get(jugador_n).getPort());
			try {
				if (!jugadaErronea) {
					socket.send(packet);
				} else {
					jugadaErronea = false;
					Mensajes.MensajeJugadaErronea mensajeJugadaErronea = new Mensajes.MensajeJugadaErronea();
					String mensajeResJugadaErronea = gson.toJson(mensajeJugadaErronea);
				    buf = mensajeResJugadaErronea.getBytes();
					packet = new DatagramPacket(buf, buf.length, clientesConectados.get(jugador_n).getAddress(),
							clientesConectados.get(jugador_n).getPort());
					socket.send(packet);
				}
				do {
					socket.receive(recibido);
				} while (recibido.getAddress() != clientesConectados.get(jugador_n).getAddress()
						&& !gson.fromJson(new String(recibido.getData(), 0, recibido.getLength()), JsonObject.class)
								.get("tipo").getAsString().equals("Jugada")
						&& recibido.getPort() != clientesConectados.get(jugador_n).getPort());
				int fila = gson.fromJson(new String(recibido.getData(), 0, recibido.getLength()), JsonObject.class)
						.get("fila").getAsInt();
				int columna = gson.fromJson(new String(recibido.getData(), 0, recibido.getLength()), JsonObject.class)
						.get("columna").getAsInt();
				System.out.println("fila: " + fila + ", columna: " + columna);

				// Validar la jugada
				if (fila < 0 || fila > 2 || columna < 0 || columna > 2
						|| !game.getTablero()[fila][columna].equals("-")) {
					jugadaErronea = true;
					continue;
				}

				// Realizar la jugada
				game.getTablero()[fila][columna] = turnoJugador1 ? "X" : "O";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Verificar si hay un ganador
			int resultado = game.comprobarGanador();
			if (resultado == 1 || resultado == 2) {
				// enviar info a client ganador
				if(resultado == 1) {
					System.out.println("Gana X");
					for(int i=0; i<game.getTablero().length; i++) {
						for(int j=0; j<game.getTablero()[0].length; j++) {
							System.out.print(" "+game.getTablero()[i][j]);
						}
						System.out.println();
					}
					
				}else {
					for(int i=0; i<game.getTablero().length; i++) {
						for(int j=0; j<game.getTablero()[0].length; j++) {
							System.out.print(" "+game.getTablero()[i][j]);
						}
						System.out.println();
					}
					System.out.println("Gana Y");
				}
				break;
			} else if (resultado == 0) {
				// empate
				System.out.println("Empate");
				for(int i=0; i<game.getTablero().length; i++) {
					for(int j=0; j<game.getTablero()[0].length; j++) {
						System.out.print(" "+game.getTablero()[i][j]);
					}
					System.out.println();
				}
				break;
			}

			// Cambiar el turno
			turnoJugador1 = !turnoJugador1;
		}
	}
}