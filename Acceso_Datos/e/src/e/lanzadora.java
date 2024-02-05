package e;

import utilidades.Leer;

public class lanzadora {

	public static void main(String[] args) {
		int resultado;
		resultado = Leer.pedirEntero("Ya existe el pedido, quiere reemplazarlo, modificarlo o ignorar. Responda con (1,2,3) respectivamente", "^[1-3]{1}");
		System.out.println(resultado);
	}

}
