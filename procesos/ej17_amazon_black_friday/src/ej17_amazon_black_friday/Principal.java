package ej17_amazon_black_friday;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Principal {// clase Principal

	public static void main(String[] args) {// main
		Amazon amazon = new Amazon();// se instancia un objeto de la clase Amazon
		Cliente cliente;// se crea un objeto de la clase Cliente, con valor inicial null
		for (int i = 0; i < 15; i++) {// este for tiene 15 iteraciones, en cada una se crea un cliente pasandole por
										// parametros el objeto de la clase Amazon, y se pone a correr al hilo cliente
			cliente = new Cliente(amazon);
			cliente.start();
		}
	}
}
