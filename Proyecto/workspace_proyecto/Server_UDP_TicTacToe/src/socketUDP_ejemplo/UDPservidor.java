package socketUDP_ejemplo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Datos.AccesoDatos;

public class UDPservidor {
	private static Thread j;
	private static Gson gson = new Gson();
	private static DatagramSocket socket;

	public static void main(String[] args) throws Exception {
		byte[] bufer = new byte[1024]; // Buffer para recibir el datagrama
		socket = new DatagramSocket(12345, InetAddress.getLocalHost()); // Asociar el
		// DatagramSocket al
		// puerto 12345
		AccesoDatos.conectar("jdbc:mysql://localhost:3306/infostats");// puerto 12345

		while (true) {
			// Esperar datagrama
			System.out.println("Esperando Datagrama ........");
			DatagramPacket recibido = new DatagramPacket(bufer, bufer.length);
			socket.receive(recibido); // Recibir el datagrama
			int bytesRec = recibido.getLength();
			String paquete = new String(recibido.getData(), 0, bytesRec); // Obtener el JSON de los datos del datagrama

			// Parsear el JSON utilizando Gson
			try {
				System.out.println(paquete);
				// Comprobar el tipo y actuar en consecuencia
				switch (gson.fromJson(paquete, JsonObject.class).get("tipo").getAsString()) {
				case "Login":
					switch (AccesoDatos.login(
							(gson.fromJson(paquete, JsonObject.class).get("email").getAsString().toString()),
							gson.fromJson(paquete, JsonObject.class).get("pwd_hash").getAsString().toString())) {
					case 0:
						sendMessage(new Mensajes.MensajeLoginCorrecto(), recibido.getAddress(), recibido.getPort());
						break;
					case 1:
						sendMessage(new Mensajes.MensajePasswordIncorrecta(), recibido.getAddress(),
								recibido.getPort());
						break;
					case 2:
						sendMessage(new Mensajes.MensajeUsuarioNoExistente(), recibido.getAddress(),
								recibido.getPort());
						break;
					}
					break;
				case "Register":
					switch (AccesoDatos.comprobar_usuarioExistente(
							gson.fromJson(paquete, JsonObject.class).get("email").getAsString().toString())) {
					case 0:
						sendMessage(new Mensajes.MensajeUsuarioExistente(), recibido.getAddress(), recibido.getPort());
						break;
					case -1:
						if (AccesoDatos.insertarUsuario(
								gson.fromJson(paquete, JsonObject.class).get("email").getAsString().toString(),
								gson.fromJson(paquete, JsonObject.class).get("pwd_hash").getAsString().toString())) {
							sendMessage(new Mensajes.MensajeRegisterCorrecto(), recibido.getAddress(),
									recibido.getPort());
						} else {
							sendMessage(new Mensajes.MensajeRegisterIncorrecto(), recibido.getAddress(),
									recibido.getPort());
						}
						break;
					}
					break;
				case "Get_Riot_ApiKey":
					String api_key = "";
					api_key = AccesoDatos.get_Riot_apiKey();
					if (!api_key.equals("")) {
						sendMessage(new Mensajes.MensajeUpdate_Riot_ApiKey(api_key), recibido.getAddress(),
								recibido.getPort());
					}
					break;
				default:
					// Aquí podrías manejar otros tipos de mensajes si es necesario
					AccesoDatos.desconectar();
					break;
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	public static void sendMessage(Object mensaje, InetAddress address, int port) throws Exception {
		String mensajeJson = gson.toJson(mensaje);
		byte buf[] = mensajeJson.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		socket.send(packet);
	}
}
