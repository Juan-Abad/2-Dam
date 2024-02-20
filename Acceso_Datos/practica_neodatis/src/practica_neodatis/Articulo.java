package practica_neodatis;

public class Articulo {
	private Long codigo_articulo;
	private String descripcion;
	private String familia;
	private Long stock;

	public Articulo(Long codigo_articulo, String descripcion, String familia, Long stock) {
		this.codigo_articulo = codigo_articulo;
		this.descripcion = descripcion;
		this.familia = familia;
		this.stock = stock;
	}

	public Long getCodigo_articulo() {
		return codigo_articulo;
	}

	public void setCodigo_articulo(Long codigo_articulo) {
		this.codigo_articulo = codigo_articulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}
}
