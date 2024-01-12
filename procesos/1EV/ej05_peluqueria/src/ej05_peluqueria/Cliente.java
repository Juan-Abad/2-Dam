package ej05_peluqueria;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Cliente extends Thread {// clase Cliente extiend de Thread
	private Peluqueria peluqueria;// objeto peluqueri inicializado en null
	private Integer idCliente;
	private static Integer siguienteID = 1;

	public Cliente(Peluqueria peluqueria) {// contructor de la clase Cliente, recibe peluqueria por parametros
		this.idCliente = siguienteID;
		siguienteID++;
		this.peluqueria = peluqueria;
	}

	@Override
	public void run() {// los clientes intentan ser atendidos, pasan por parametros al metodo a si
						// mismos
		peluqueria.atenderCliente(this);
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

}
