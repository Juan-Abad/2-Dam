package socketUDP_ejemplo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class UDPservidor {
	// Lista para almacenar las direcciones IP de los clientes conectados
	private static List<Jugador> clientesConectados = new ArrayList<>();
	private static Game game = new Game();
	private static Juego juego;
	private static DatagramSocket socketJuego;

	public static void main(String[] args) throws Exception {
		byte[] bufer = new byte[1024]; // Buffer para recibir el datagrama
		DatagramSocket socket = new DatagramSocket(49167, InetAddress.getLocalHost()); // Asociar el DatagramSocket al
																						// puerto 12345
		socketJuego = new DatagramSocket(49000, InetAddress.getLocalHost());
		// socket.bind(new
		// InetSocketAddress(InetAddress.getByName("192.168.1.119"),5005));
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
					boolean existente = false;
					for (Jugador j : clientesConectados) {
						if (j.getPort() == recibido.getPort()) {
							existente = true;
						}
					}
					if (clientesConectados.size() < 2 && !existente) {
						Jugador jugador = new Jugador(recibido.getAddress(), recibido.getPort());
						clientesConectados.add(jugador);

						Mensajes.MensajeJugadorConectado mensajeJugadorConectado = new Mensajes.MensajeJugadorConectado(
								jugador.getIdJugador(), game.getTablero());
						DatagramPacket packet;
						String mensajeRespuestaJson = gson.toJson(mensajeJugadorConectado);
						byte buf[] = mensajeRespuestaJson.getBytes();
						packet = new DatagramPacket(buf, buf.length, recibido.getAddress(), recibido.getPort());
						socket.send(packet);

						System.out.println("Cliente registrado: " + recibido.getAddress());
						System.out.println(recibido.getPort());
					} else {
						System.out.println("No se pudo registrar el cliente " + recibido.getAddress());
					}
					if (clientesConectados.size() == 2) {
						Mensajes.MensajeSimbolo_jugador mensajeRespuesta;
						DatagramPacket packet;
						Boolean jugadorX = null;
						int idJugador = -1;

						// Recorremos los clientes conectados
						for (int i = 0; i < 2; i++) {
							// Si el jugador tiene asignado el símbolo X, guardamos su información
							if (clientesConectados.get(i).getIsPlayerX() != null && jugadorX == null) {
								idJugador = clientesConectados.get(i).getIdJugador();
								jugadorX = clientesConectados.get(i).getIsPlayerX();
							}
						}

						// Enviamos los mensajes solo al jugador activo (cuyo símbolo X es diferente de
						// null)
						if (jugadorX == null) {
							jugadorX = true; // Primero jugador X
							for (Jugador j : clientesConectados) {
								mensajeRespuesta = new Mensajes.MensajeSimbolo_jugador(jugadorX);
								String mensajeRespuestaJson = gson.toJson(mensajeRespuesta);
								byte buf[] = mensajeRespuestaJson.getBytes();
								packet = new DatagramPacket(buf, buf.length, j.getAddress(), j.getPort());
								socket.send(packet);
								jugadorX = !jugadorX;
							}
						} else {

							// Enviamos los mensajes solo al jugador activo (cuyo símbolo X es diferente de
							// null)
							for (Jugador j: clientesConectados) {
								// Si es el mismo jugador al que se le asignó el símbolo X, enviamos el mensaje
								if (j.getIdJugador() != idJugador) {
									mensajeRespuesta = new Mensajes.MensajeSimbolo_jugador(!jugadorX);
									String mensajeRespuestaJson = gson.toJson(mensajeRespuesta);
									byte buf[] = mensajeRespuestaJson.getBytes();
									packet = new DatagramPacket(buf, buf.length,
											j.getAddress(),
											j.getPort());
									socket.send(packet);
								}
							}
						}

						juego = new Juego(socketJuego, game, clientesConectados);
						Thread j = new Thread(juego);
						j.start();
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
				case "Desconexion jugador":
					Integer idJugador = gson.fromJson(paquete, JsonObject.class).get("idJugador").getAsInt();
					for (Jugador j : clientesConectados) {
						if (j.getIdJugador() == idJugador) {
							clientesConectados.remove(j);
						}
					}
					boolean turnoJugador1 = juego.isTurnoJugador1();
					juego = new Juego(socketJuego, game, clientesConectados);
					juego.setTurnoJugador1(turnoJugador1);
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
}
