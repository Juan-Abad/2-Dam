package monedero;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class Monedero {
	private Integer cantidad;

	public Monedero() {
		super();
		this.cantidad = 0;
	}

	synchronized public Integer getCantidad() {
		return cantidad;
	}

	synchronized public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	synchronized public void donar(Integer cantidad, InetAddress address) {
		setCantidad(getCantidad() + cantidad);
		System.out.println("Cantidad: " + this.cantidad);
		try {
			FileOutputStream fout = new FileOutputStream("log", true);
			
			DataOutputStream dtout = new DataOutputStream(fout);
			
			dtout.writeChars("Conexión a "+LocalDateTime.now()+", de "+address+" ha donado "+cantidad);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
