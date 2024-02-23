package socketUDP_ejemplo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Semaphore;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Graphic_game.Dialog_playAgain;
import Graphic_game.MiFrame;
import socketUDP_ejemplo.Mensajes.MensajeJugadorConectado;

public class MessageReceiver implements Runnable {
	private DatagramSocket sock;
	private byte buf[];
	private final MessageSender sender;
	private MiFrame frame;
	private Semaphore semaphore; // Semáforo para esperar el clic del usuario
	private Integer idJugador;
	private DatagramSocket socketJuego;

	MessageReceiver(DatagramSocket s, MessageSender sender, DatagramSocket socketJuego) {
		sock = s;
		buf = new byte[1024];
		this.sender = sender;
		semaphore = new Semaphore(0); // Inicializa el semáforo con 0 permisos
		this.socketJuego = socketJuego;
	}

	public void run() {
		Gson gson = new Gson();
		while (true) {
			try {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				sock.receive(packet);
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println(received);

				switch (gson.fromJson(received, JsonObject.class).get("tipo").getAsString()) {
				case "Conexion al servidor correcta":
					this.idJugador = gson.fromJson(received, JsonObject.class).get("idJugador").getAsInt();
					frame = new MiFrame(semaphore, sender, this.idJugador);
					MensajeJugadorConectado mensaje = gson.fromJson(received, MensajeJugadorConectado.class);
					frame.actualizarPartida(mensaje.tablero);
					frame.setVisible(true);
					frame.pauseJuego();
					sender.setConnected(true);
					break;
				case "Simbolo jugador":
					frame.setPlayerX(gson.fromJson(received, JsonObject.class).get("isPlayerX").getAsBoolean());
					frame.unpauseJuego();
					break;
				case "comprobar_conexion":
					Mensajes.MensajeComprobarConexion_ping mensajeRespuesta = new Mensajes.MensajeComprobarConexion_ping();
					String mensajeRespuestaJson = gson.toJson(mensajeRespuesta);
					sendMessage(mensajeRespuestaJson, packet.getAddress(), packet.getPort());
					break;
				case "Juega":
					frame.setLabel_mensaje_turno("Es tu turno de juego");
					frame.unpauseJuego();
					// Esperar la señal del clic del usuario
					semaphore.acquire();
					// Obtener la fila y la columna del frame una vez que el usuario hizo clic
					int fila = frame.getFilaJugada();
					int columna = frame.getColumnaJugada();
					// Crear el mensaje de "Juega"
					Mensajes.MensajeJugada mensajeJugada = new Mensajes.MensajeJugada(fila, columna);
					String mensajeJugadaJson = gson.toJson(mensajeJugada);
					sendMessageJuego(mensajeJugadaJson, packet.getAddress(), packet.getPort());
					break;
				case "Jugada erronea":
					frame.unpauseJuego();
					// Esperar la señal del clic del usuario
					semaphore.acquire();
					// Obtener la fila y la columna del frame una vez que el usuario hizo clic
					fila = frame.getFilaJugada();
					columna = frame.getColumnaJugada();
					// Crear el mensaje de "Jugada erronea"
					Mensajes.MensajeJugada mensajeJugadaErr = new Mensajes.MensajeJugada(fila, columna);
					String mensajeJugadaErrJson = gson.toJson(mensajeJugadaErr);
					sendMessageJuego(mensajeJugadaErrJson, packet.getAddress(), packet.getPort());
					break;
				case "Jugada oponente":
					frame.jugada_oponente(gson.fromJson(received, JsonObject.class).get("fila").getAsInt(),
							gson.fromJson(received, JsonObject.class).get("columna").getAsInt());
					break;
				case "Espera":
					frame.setLabel_mensaje_turno("Esperando al rival...");
					frame.pauseJuego();
					break;
				case "Ganador":
					frame.pauseJuego();
					Dialog_playAgain dialogo_partida_Ganada = new Dialog_playAgain(frame);
					dialogo_partida_Ganada.getLblNewLabel().setText("¡Has ganado!");
					dialogo_partida_Ganada.setVisible(true);
					break;
				case "Perdedor":
					frame.pauseJuego();
					Dialog_playAgain dialogo_partida_Perdida = new Dialog_playAgain(frame);
					dialogo_partida_Perdida.getLblNewLabel().setText("¡Has perdido!");
					dialogo_partida_Perdida.setVisible(true);
					break;
				case "Empate":
					frame.pauseJuego();
					Dialog_playAgain dialogo_partida_Empate = new Dialog_playAgain(frame);
					dialogo_partida_Empate.getLblNewLabel().setText("¡Habeis empatado!");
					dialogo_partida_Empate.setVisible(true);
					break;
				default:
					break;
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Método para enviar un mensaje
	private void sendMessage(String s, InetAddress address, int port) throws IOException {
		byte buf[] = s.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		sock.send(packet);
	}
	
	private void sendMessageJuego(String s, InetAddress address, int port) throws IOException {
		byte buf[] = s.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		socketJuego.send(packet);
	}

	// Método para liberar el semáforo y notificar que el usuario hizo clic en el
	// frame
	public void signalUserClick() {
		semaphore.release();
	}
}
