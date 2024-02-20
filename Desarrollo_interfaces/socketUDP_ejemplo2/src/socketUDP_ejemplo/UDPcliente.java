package socketUDP_ejemplo;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.*;

class MessageSender implements Runnable {
    public final static int PORT = 12345;
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
            Mensajes.MensajeRegistro mensajeRegistro = new Mensajes.MensajeRegistro("Jose");
            sendMessage(mensajeRegistro, InetAddress.getByName(hostname), PORT);
            connected = true;
            System.out.println("conectado");
        } catch (Exception e) {
            System.err.println(e);
        }
        while(true) {
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

    MessageReceiver(DatagramSocket s, MessageSender sender) {
        sock = s;
        buf = new byte[1024];
        this.sender = sender;
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
                    case "comprobar_conexion":
                        Mensajes.MensajeComprobarConexion_ping mensajeRespuesta = new Mensajes.MensajeComprobarConexion_ping();
                        String mensajeRespuestaJson = gson.toJson(mensajeRespuesta);
                        sendMessage(mensajeRespuestaJson, packet.getAddress(), packet.getPort());
                        break;
                    case "Juega":
                    	Mensajes.MensajeJugada mensajeJugada = new Mensajes.MensajeJugada((int) (Math.random()*3), (int) (Math.random()*3));
                    	String mensajeJugadaJson = gson.toJson(mensajeJugada);
                    	sendMessage(mensajeJugadaJson, packet.getAddress(), packet.getPort());
                    	break;
                    case "Jugada erronea":
                    	Mensajes.MensajeJugada mensajeJugada_err = new Mensajes.MensajeJugada((int) (Math.random()*3), (int) (Math.random()*3));
                    	String mensajeJugadaErrJson = gson.toJson(mensajeJugada_err);
                    	sendMessage(mensajeJugadaErrJson, packet.getAddress(), packet.getPort());
                    	break;
                    default:
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(String s, InetAddress address, int port) throws IOException {
        byte buf[] = s.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        sock.send(packet);
    }
}

public class UDPcliente {
    public static void main(String args[]) throws Exception {
        String host = "localhost";

        DatagramSocket socket = new DatagramSocket();
        MessageSender s = new MessageSender(socket, host);
        MessageReceiver r = new MessageReceiver(socket, s);

        Thread st = new Thread(s);
        Thread rt = new Thread(r);

        st.start();
        rt.start();
    }
}
