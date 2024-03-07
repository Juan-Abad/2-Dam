
public class Venta {
	private String fecha, producto, talla, color;
	private double precio;
	private int cantidad;

	public Venta(String fecha, String producto, String talla, String color, double precio, int cantidad) {
		this.fecha = fecha;
		this.producto = producto;
		this.talla = talla;
		this.color = color;
		this.precio = precio;
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

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
		return "fecha:" + this.fecha + ", producto:" + this.producto + ", talla:" + this.talla + " color:" + this.color
				+ " precio:" + this.precio + ", cantidad: " + this.cantidad;
	}
}
