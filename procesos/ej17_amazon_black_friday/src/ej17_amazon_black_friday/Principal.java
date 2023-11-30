package ej17_amazon_black_friday;

public class Principal {

	public static void main(String[] args) {
		Amazon amazon = new Amazon();
		Cliente cliente;
		for(int i=0; i<15; i++) {
			cliente= new Cliente(amazon);
			cliente.start();
		}
	}
}
