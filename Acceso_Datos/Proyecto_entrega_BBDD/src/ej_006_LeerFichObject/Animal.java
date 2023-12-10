package ej_006_LeerFichObject;

import java.io.Serializable;

public class Animal implements Serializable {
	/**
	 * Clase de ejemplo, para poder usarla en la clase del main, tiene una edad, e implementaa Serializable para poder escribirla en un archivo
	 * Laa interfaz permite que los objetos de esta clase puedan ser convertidos en bytes y guardados en un archivo
	 */
	private static final long serialVersionUID = 1L;
	private int edad;

	public Animal(int edad) {
		this.edad = edad;
	}

	public int getEdad() {
		return edad;
	}
}
