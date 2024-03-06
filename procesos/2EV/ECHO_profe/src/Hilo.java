import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Hilo extends Thread {

	Socket clientSocket;
	public Hilo(Socket clientSocket) {
		this.clientSocket=clientSocket;
	}
	public void run() {
		
	        try (
	             
	            PrintWriter out =
	                new PrintWriter(clientSocket.getOutputStream(), true);                   
	            BufferedReader in = new BufferedReader(
	                new InputStreamReader(clientSocket.getInputStream()));
	        ) {
	            String inputLine;
	            while ((inputLine = in.readLine()) != null) {
	                out.println(inputLine.toUpperCase());
	            }
	        } catch (IOException e) {
	            System.out.println("Exception caught when trying to listen on port "
	                +  " or listening for a connection");
	            System.out.println(e.getMessage());
	        }
	    }
	
		
	}