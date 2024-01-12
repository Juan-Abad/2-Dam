package ej16_Agentes_codificacion;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Nombre {
	private String nombre = null;

	synchronized public String getNombre() {
		return nombre;
	}

	synchronized public void setNombre(String nombre) {
		this.nombre = nombre;
		notifyAll();
	}

}
