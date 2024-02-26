package practica_neodatis;

public class PedidoArticulo {
	private Long numero_pedido;
	private Long codigo_articulo;
	private Integer cantidad;

	public PedidoArticulo(Long numero_pedido, Long codigo_articulo, Integer cantidad) {
		this.numero_pedido = numero_pedido;
		this.codigo_articulo = codigo_articulo;
		this.cantidad = cantidad;
	}

	public PedidoArticulo(Long numero_pedido) {
		this.numero_pedido = numero_pedido;
	}

	public Long getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(Long numero_pedido) {
		this.numero_pedido = numero_pedido;
	}

	public Long getCodigo_articulo() {
		return codigo_articulo;
	}

	public void setCodigo_articulo(Long codigo_articulo) {
		this.codigo_articulo = codigo_articulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
