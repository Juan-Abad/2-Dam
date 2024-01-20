
public class Acumulador {
	
	private Integer cantidad;
	
	public Acumulador() {
		super();
		this.cantidad = 0;
	}

	synchronized public Integer getCantidad() {
		return cantidad;
	}
	
	synchronized public void sumar1() {
		this.cantidad++;
	}
}
