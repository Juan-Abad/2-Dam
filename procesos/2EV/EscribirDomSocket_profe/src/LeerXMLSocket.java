import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class LeerXMLSocket {

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Cambia la direcci�n del servidor seg�n tu configuraci�n
        int serverPort = 12345; // Cambia el puerto seg�n tu configuraci�n

        try {
            // Conectamos al servidor
            Socket socket = new Socket(serverAddress, serverPort);

            // Creamos un BufferedReader para leer desde el InputStream del socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Leemos y mostramos el XML desde el servidor
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                System.out.println("xx");
            }

            // Cerramos la conexi�n
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}