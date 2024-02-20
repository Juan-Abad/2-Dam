package socketUDP_ejemplo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class UDPservidor {
    // Lista para almacenar las direcciones IP de los clientes conectados
    private static List<InetAddress> clientesConectados = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        byte[] bufer = new byte[1024]; // Buffer para recibir el datagrama
        DatagramSocket socket = new DatagramSocket(12345); // Asociar el DatagramSocket al puerto 12345

        // Crear un temporizador para enviar el mensaje de comprobación de conexión cada 5 segundos
        Timer timer = new Timer();
        timer.schedule(new ComprobarConexionTask(socket), 0, 5000); // Se ejecuta inmediatamente y luego cada 5 segundos

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
                        Mensajes.MensajeRegistro mensajeRecibido = gson.fromJson(paquete, Mensajes.MensajeRegistro.class);
                        if (clientesConectados.size() < 2 && !clientesConectados.contains(recibido.getAddress())) {
                            clientesConectados.add(recibido.getAddress());
                            System.out.println("Cliente registrado: " + recibido.getAddress());
                        } else {
                            System.out.println("No se pudo registrar el cliente " + recibido.getAddress());
                        }
                        mensajeRecibido.toString();
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
                        System.out.println("comprobar");
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

    // Tarea para enviar el mensaje de comprobación de conexión cada 5 segundos
    static class ComprobarConexionTask extends TimerTask {
        private DatagramSocket socket;

        public ComprobarConexionTask(DatagramSocket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Mensajes.MensajeComprobarConexion mensajeComprobacion = new Mensajes.MensajeComprobarConexion();
                Gson gson = new Gson();
                String mensajeComprobacionJson = gson.toJson(mensajeComprobacion);
                byte buf[] = mensajeComprobacionJson.getBytes();
                
                // Enviar el mensaje de comprobación de conexión a cada cliente registrado
                for (InetAddress clientAddress : clientesConectados) {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, clientAddress, 12345); // Puerto del cliente
                    socket.send(packet);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
