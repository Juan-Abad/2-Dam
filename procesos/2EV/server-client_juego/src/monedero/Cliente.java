package monedero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import utilidades.Leer;

public class Cliente {

	public static void main(String[] args) {
		try (Socket s = new Socket("localHost", 5000); // conexion a 'localhost' o '10.6.4.1'
				DataInputStream dr = new DataInputStream(s.getInputStream());
				DataOutputStream dw = new DataOutputStream(s.getOutputStream());) {
			System.out.println(dr.readUTF());
			dw.writeUTF("recibido");
			do {
				dw.writeUTF(String.valueOf(Leer.pedirEntero("introduzca un entero")));
				if (dr.readUTF().equals("numero_aceptado")) {
					System.out.println("bien");
				} else {
					System.out.println("mal");
				}
			} while (true);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
