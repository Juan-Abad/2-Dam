
public class Principal {

	public static void main(String[] args) {
		Servidor server = new Servidor();
		server.start();
		Cliente cliente;
		for(int i=0; i<3; i++) {
			cliente = new Cliente();
			cliente.start();
		}
	}

}
