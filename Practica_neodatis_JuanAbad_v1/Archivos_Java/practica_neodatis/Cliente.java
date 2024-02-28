package practica_neodatis;

public class Cliente {
	private Long numero_cliente;
	private String nombre;
	private String direccion;
	private Long telefono;

	public Cliente(Long numero_cliente, String nombre, String direccion, Long telefono) {
		this.numero_cliente = numero_cliente;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Long getNumero_cliente() {
		return numero_cliente;
	}

	public void setNumero_cliente(Long numero_cliente) {
		this.numero_cliente = numero_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
}
