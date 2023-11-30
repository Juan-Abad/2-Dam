package ej05_peluqueria;

public class Cliente {
	private Peluqueria peluqueria;
	private Integer idCliente;
	private static Integer siguienteID = 1;

	public Cliente(Peluqueria peluqueria) {
		this.idCliente = siguienteID;
		siguienteID++;
		this.peluqueria = peluqueria;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

}
