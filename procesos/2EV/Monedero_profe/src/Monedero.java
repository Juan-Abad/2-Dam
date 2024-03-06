public class Monedero {
	private Integer cantidad;

	public Monedero() {
		super();
		this.cantidad = 0;
	}

	public synchronized Integer getCantidad() {
		return cantidad;
	}

	public synchronized void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public synchronized void donar(Integer n) {
		this.cantidad = cantidad+n;
		System.out.println("tengo "+getCantidad());
	}
}
