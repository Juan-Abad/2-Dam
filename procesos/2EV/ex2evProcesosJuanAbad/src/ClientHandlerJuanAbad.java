import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandlerJuanAbad extends Thread {//Es el handler del servidor para el cliente
	Socket s;
	ClienteServerJuanAbad cliente = new ClienteServerJuanAbad();
	private static Integer IDsiguiente = 1;
	private Integer idCliente;

	public ClientHandlerJuanAbad(Socket s) {
		super();
		this.s = s;
		this.idCliente = ejecucionID();
	}

	public void run() {
		try (DataInputStream in = new DataInputStream(s.getInputStream());
				DataOutputStream out = new DataOutputStream(s.getOutputStream());) {
			out.writeUTF("Bienvenido estimado cliente,\nEs vd. el cliente número " + this.idCliente
					+ "\nIntroduzca su nombre y el código promocional.");
			cliente.setNombre(in.readUTF());
			String codigo;
			while (!(codigo = in.readUTF()).matches("^[A-Z]{2}[0-9]{4}")) {
				out.writeUTF("Código con formato erroneo, vuelva a introducirlo:");
			}
			cliente.setCodigo(codigo);
			System.out.println("Cliente: " + cliente.getNombre() + ", código: " + cliente.getCodigo());
			if (ClienteServerJuanAbad.getListaCodigos().contains(codigo)) {
				out.writeUTF("Estimado cliente " + cliente.getNombre() + "\nSu código ya ha sido utilizado");
			} else {
				ClienteServerJuanAbad.getListaCodigos().add(codigo);
				if (codigo.matches("^[A-Z]{2}1234")) {

					out.writeUTF("Estimado " + cliente.getNombre()
							+ "\nGracias por utilizar nuestro producto.\nSu código " + codigo + " ha sido premiado");
				} else {
					out.writeUTF("Estimado " + cliente.getNombre()
							+ "\nGracias por utilizar nuestro producto.\nSu código " + codigo + " no ha sido premiado");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized static Integer ejecucionID() {
		int idCliente = IDsiguiente;
		IDsiguiente++;
		return idCliente;
	}
}