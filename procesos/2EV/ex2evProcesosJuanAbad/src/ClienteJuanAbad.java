import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;

public class ClienteJuanAbad {

	public static void main(String[] args) {

		try {
			boolean conectado = false;
			Socket s = null;
			System.out.println("Conectando al servidor...\n");
			do {
				try {
				s = new Socket("localhost", 5000);
				conectado = true;
				}catch (ConnectException e) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					conectado = false;
				}
			}while(!conectado);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			System.out.println(in.readUTF());
			String codigo = "", nombre = "", mensajeRecibido = "";
			System.out.println("\nIntroduzca su nombre:");
			nombre = stdIn.readLine();
			System.out.println("Introduzca su código:");
			codigo = stdIn.readLine();
			out.writeUTF(nombre);
			out.writeUTF(codigo);
			while ((mensajeRecibido = in.readUTF()).equals("Código con formato erroneo, vuelva a introducirlo:")) {
				System.out.println(mensajeRecibido);
				codigo = stdIn.readLine();
				out.writeUTF(codigo);
			}
			System.out.println(mensajeRecibido);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
