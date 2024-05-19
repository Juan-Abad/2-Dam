
public class Articulo {

	private String fecha, producto, tipo;
	private double precio;
	private int cantidad;

	public Articulo(String fecha, String producto, String tipo, double precio, int cantidad) {
		super();
		this.fecha = fecha;
		this.producto = producto;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public Articulo(String producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "fecha: " + this.fecha + ", producto: " + this.producto + ", tipo: " + this.tipo + " precio: " + this.precio
				+ ", cantidad: " + this.cantidad;
	}
}
