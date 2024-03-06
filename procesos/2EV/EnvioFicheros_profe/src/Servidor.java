import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		final String NOMBRE_ARCHIVO="img.png";
		final Integer PUERTO =12345;
		
		try {
			ServerSocket server=new ServerSocket(PUERTO);
			
			//Acepta una única conexión
			System.out.println("Servidor esperando conexiones...");
            Socket clienteSocket = server.accept();
            System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress() + ":" + clienteSocket.getPort());
			
			// Abrir el archivo
            File archivo = new File(NOMBRE_ARCHIVO);
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            // Obtener el stream de salida del socket
            OutputStream os = clienteSocket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            //Transmitir el archivo
            while ((bytesRead = bis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
                os.flush();
            }
             System.out.println("Archivo enviado exitosamente.");
            // Cerrar conexiones
            bis.close();
            fis.close();
            os.close();
            clienteSocket.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

}
