import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
public static void main(String[] args) throws IOException {
         
        
        int portNumber = 5000;
         
        try {
            ServerSocket serverSocket =
                new ServerSocket(5000);
            while(true) {
            	Socket clientSocket = serverSocket.accept();
            	Hilo h=new Hilo(clientSocket);
            	h.start();
            }
        }catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
