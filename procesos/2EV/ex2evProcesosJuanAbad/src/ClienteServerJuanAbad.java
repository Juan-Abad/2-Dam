import java.util.ArrayList;

public class ClienteServerJuanAbad {//es el objeto compartido, el cual contiene el nombre y codigo de cliente, además de tener acceso a una variable estatica arrayList que contiene los codigos ya introducidos
	private String nombre;
	private String codigo;
	private static ArrayList<String> listaCodigos = new ArrayList<String>();

	public ClienteServerJuanAbad() {
		super();
		this.codigo = "";
	}

	public ClienteServerJuanAbad(String codigo) {
		super();
		this.codigo = codigo;
	}

	public synchronized String getCodigo() {
		return codigo;
	}

	public synchronized void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public static synchronized ArrayList<String> getListaCodigos() {
		return listaCodigos;
	}

	public synchronized String getNombre() {
		return nombre;
	}

	public synchronized void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
