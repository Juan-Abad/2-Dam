package practica_neodatis;

public class Pedido {
	private Long numero_pedido;
	private Long numero_cliente;
	private String fecha;

	public Pedido(Long numero_pedido, Long numero_cliente, String fecha) {
		this.numero_pedido = numero_pedido;
		this.numero_cliente = numero_cliente;
		this.fecha = fecha;
	}

	public Long getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(Long numero_pedido) {
		this.numero_pedido = numero_pedido;
	}

	public Long getNumero_cliente() {
		return numero_cliente;
	}

	public void setNumero_cliente(Long numero_cliente) {
		this.numero_cliente = numero_cliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/*public String toString() {
		return "numero_pedido: "+numero_pedido+", numero_cliente: "+numero_cliente+", fecha: "+fecha;
	}*/
}
