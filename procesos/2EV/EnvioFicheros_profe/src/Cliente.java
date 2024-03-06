import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost";
        final int PUERTO = 12345;
        final String NOMBRE_ARCHIVO_COPIA = "img_copia.png";

        try (Socket socket = new Socket(SERVIDOR, PUERTO);
                InputStream is = socket.getInputStream();
                FileOutputStream fos = new FileOutputStream(NOMBRE_ARCHIVO_COPIA);
                BufferedOutputStream bos = new BufferedOutputStream(fos);) {
        	

            byte[] buffer = new byte[1024];
            int bytesRead;
            // Leer datos del socket y escribirlos en el archivo de copia
            while ((bytesRead = is.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
                bos.flush();
            }

            System.out.println("Archivo recibido y guardado como " + NOMBRE_ARCHIVO_COPIA);
            // Cerrar conexiones
            bos.close();
            fos.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}