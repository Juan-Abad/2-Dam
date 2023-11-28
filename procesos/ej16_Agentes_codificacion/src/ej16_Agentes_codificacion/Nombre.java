package ej16_Agentes_codificacion;

public class Nombre {
	private String nombre;

	synchronized public String getNombre() {
		return nombre;
	}

	synchronized public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
