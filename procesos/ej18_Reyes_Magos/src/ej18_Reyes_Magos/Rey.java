package ej18_Reyes_Magos;

public class Rey {
	private Integer idRey;
	private static Integer siguienteID = 1;
	private Boolean disponible = true;
	private String nombre_rey;
	private Buzon buzon;

	public Rey(String nombre_rey, Buzon buzon) {
		this.idRey = siguienteID;
		siguienteID++;
		this.nombre_rey = nombre_rey;
		this.buzon = buzon;
	}

	synchronized public void recibir_kid() {
		while (!disponible) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Rey: " + this.nombre_rey + ", recibe al niño: " + Thread.currentThread());
		try {
			Thread.sleep((int) Math.random()*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		buzon.recibir_carta(this);
		notifyAll();
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
