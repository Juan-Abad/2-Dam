package socketUDP_ejemplo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Graphic_game.Dialog_playAgain;
import Graphic_game.MiFrame;

import java.awt.Frame;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.Semaphore;

class MessageSender implements Runnable {
	public final static int PORT = 49167;
	private DatagramSocket sock;
	private String hostname;
	private volatile boolean connected = false;
	private Gson gson;

	MessageSender(DatagramSocket s, String h) {
		sock = s;
		hostname = h;
		gson = new Gson();
	}

	private void sendMessage(Object mensaje, InetAddress address, int port) throws Exception {
		String mensajeJson = gson.toJson(mensaje);
		byte buf[] = mensajeJson.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		sock.send(packet);
	}

	public void run() {
		try {
			Mensajes.MensajeRegistro mensajeRegistro = new Mensajes.MensajeRegistro("Edu");
			sendMessage(mensajeRegistro, InetAddress.getByName(hostname), PORT);
			connected = true;
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
}

class MessageReceiver implements Runnable {
    private DatagramSocket sock;
    private byte buf[];
    private final MessageSender sender;
    private MiFrame frame;
    private Semaphore semaphore; // Semáforo para esperar el clic del usuario

    MessageReceiver(DatagramSocket s, MessageSender sender) {
        sock = s;
        buf = new byte[1024];
        this.sender = sender;
        semaphore = new Semaphore(0); // Inicializa el semáforo con 0 permisos
    }

    public void run() {
        Gson gson = new Gson();
        while (!sender.isConnected()) {
        }
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);

                switch (gson.fromJson(received, JsonObject.class).get("tipo").getAsString()) {
                    case "Simbolo jugador":
                        frame = new MiFrame(gson.fromJson(received, JsonObject.class).get("isPlayerX").getAsBoolean(), semaphore);
                        frame.setVisible(true);
                        break;
                    case "comprobar_conexion":
                        Mensajes.MensajeComprobarConexion_ping mensajeRespuesta = new Mensajes.MensajeComprobarConexion_ping();
                        String mensajeRespuestaJson = gson.toJson(mensajeRespuesta);
                        sendMessage(mensajeRespuestaJson, packet.getAddress(), packet.getPort());
                        break;
                    case "Juega":
                    	frame.unpauseJuego();
                        // Esperar la señal del clic del usuario
                        semaphore.acquire();
                        // Obtener la fila y la columna del frame una vez que el usuario hizo clic
                        int fila = frame.getFilaJugada();
                        int columna = frame.getColumnaJugada();
                        // Crear el mensaje de "Juega"
                        Mensajes.MensajeJugada mensajeJugada = new Mensajes.MensajeJugada(fila, columna);
                        String mensajeJugadaJson = gson.toJson(mensajeJugada);
                        sendMessage(mensajeJugadaJson, packet.getAddress(), packet.getPort());
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
                        sendMessage(mensajeJugadaErrJson, packet.getAddress(), packet.getPort());
                        break;
                    case "Jugada oponente":
                        frame.jugada_oponente(gson.fromJson(received, JsonObject.class).get("fila").getAsInt(), gson.fromJson(received, JsonObject.class).get("columna").getAsInt());
                        break;
                    case "Espera":
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

    // Método para liberar el semáforo y notificar que el usuario hizo clic en el frame
    public void signalUserClick() {
        semaphore.release();
    }
}

public class UDPcliente {
	public static void main(String args[]) throws Exception {
		String host = "192.168.1.119";

		DatagramSocket socket = new DatagramSocket();
		MessageSender s = new MessageSender(socket, host);
		MessageReceiver r = new MessageReceiver(socket, s);

		Thread st = new Thread(s);
		Thread rt = new Thread(r);

		st.start();
		rt.start();
	}
}
