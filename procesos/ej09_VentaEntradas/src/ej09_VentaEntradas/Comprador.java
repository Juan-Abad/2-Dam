package ej09_VentaEntradas;

import java.util.ArrayList;

public class Comprador extends Thread {
	private ArrayList<Entrada> entradaList = new ArrayList<Entrada>();//Cada comprador tiene su propia lista de entradas, en esta lista cada comprador tenda las entradas que ha comprado guardadas

	private Integer idComprador;//en esta variable se guarda el id del comprador
	private static Integer siguienteID = 1;//variable estatica, su funcion es crear los id de cada Comprador

	public Comprador() {//Contructor de la clase
		this.idComprador = siguienteID;//se guarda el id del comprador en su variable
		siguienteID++;//se aumenta la variable idsiguiente, para el siguiente comprador
	}

	public void run() {//Metodo run
		while (true) {//el comprador no parara de ejecutarse, comprando y vendiendo entradas
			comprar_entradas((int) (Math.random() * 9 + 1));//llama al metodo comprar_entradas, pasandole por argumentos el numero de entradas a comprar, el numero es aleatorio entre 1 y 10
			try {
				sleep((int) (Math.random() * 2000));//el hilo Comprador duerme durante entre 0 a 1999 milisegundos
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			vender_entradas((int) (Math.random() * entradaList.size()));//se llama al metodo vender_entradas, se le pasa por parametro el numero de entradasa a vender, el cual no puede ser mayor al numero de entradas que el comprador tiene, por eso se pasa un numero entre 0 y el tamaño del array
		}
	}

	public void comprar_entradas(int numEntradas) {//metodo comprar_entradas, recibe el numero de entradas a comprar
	    int entradasCompradas = 0;//variable que guarda el numero de entradas que ha comprado
	    for (Entrada entrada : Entrada.getListaEntradas()) {//recorremos el arrayList estatico de la clase Entrada, recibiendo en cada iteracion una entrada que se pueso a la venta
	        if (entrada.getIsAvailable() && entradasCompradas < numEntradas) {// se comprueba el estado de la entrada, si esta disponible, y el numero de entradas que lleva compradas es menor que el que solicitaba comprar entra.
	            synchronized (entrada) {//Se sincroniza el objeto entrada
	                if (entrada.getIsAvailable()) {//se comprueba si la entrada esta disponible
	                    entrada.setIsAvailable(false);
	                    System.out.println("Comprador: " + idComprador + ", compra entrada " + entrada.getIdEntrada());
	                    entradaList.add(entrada);
	                    entradasCompradas++;
	                }
	            }
	        }
	    }
	    Entrada.setNumeroEntradasDisponibles(Entrada.getNumeroEntradasDisponibles() - entradasCompradas);
	}


	public synchronized void vender_entradas(int numEntradas) {
	    for (int i = 0; i < numEntradas; i++) {
	        if (!entradaList.isEmpty()) {
	            Entrada entradaAVender = entradaList.remove(entradaList.size() - 1);
	            System.out.println("Comprador: " + idComprador + ", vende entrada " + entradaAVender.getIdEntrada());
	            entradaAVender.setIsAvailable(true);
	            Entrada.setNumeroEntradasDisponibles(Entrada.getNumeroEntradasDisponibles() + 1);
	        }
	    }
	    notifyAll();
	}

}
