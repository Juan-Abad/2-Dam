package ej18_Reyes_Magos;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Rey {// Clase Rey
	private Integer idRey;
	private static Integer siguienteID = 1;
	private Boolean disponible = true;// variable Boolean, indica si el rey mago est disponible para recibir a un niño
	private String nombre_rey;// variable String nombre del rey
	private Buzon buzon;// objeto de la clase Buzon, se inicializa en el constructor

	public Rey(String nombre_rey, Buzon buzon) {// contructor, recibe por parametros el nombre del rey ey el objeto de
												// la clase Buzon
		this.idRey = siguienteID;
		siguienteID++;
		this.nombre_rey = nombre_rey;
		this.buzon = buzon;
	}

	synchronized public void recibir_kid() {// metodo synchrnized recibir_kir, el metodo comprueba si el rey esta
											// disponible, en caso negativo el hilo se espera, si esta disponible el
											// hilo kid ocupa al rey
		System.out.println("Rey: " + this.nombre_rey + ", recibe al niño: " + Thread.currentThread().getId());
		try {
			Thread.sleep((int) Math.random() * 1000);// el hilo se queda hablando con el rey de 0 a 999 milisegundos
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		buzon.recibir_carta(this);// el buzon recibe una carta con el objeto rey que ha llamado al metodo
	}

	public static Integer getSiguienteID() {
		return siguienteID;
	}

	public static void setSiguienteID(Integer siguienteID) {
		Rey.siguienteID = siguienteID;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public String getNombre_rey() {
		return nombre_rey;
	}

	public void setNombre_rey(String nombre_rey) {
		this.nombre_rey = nombre_rey;
	}

	public Integer getIdRey() {
		return idRey;
	}

	public Buzon getBuzon() {
		return buzon;
	}

}
