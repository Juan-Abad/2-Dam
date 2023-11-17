package ej09_VentaEntradas;

import java.util.ArrayList;

public class Comprador extends Thread {
	private ArrayList<Entrada> entradaList = new ArrayList<Entrada>();

	private Integer idComprador;
	private static Integer siguienteID = 1;

	public Comprador() {
		this.idComprador = siguienteID;
		siguienteID++;
	}

	public void run() {
		while (true) {
			comprar_entradas((int) (Math.random() * 9 + 1));
			try {
				sleep((int) (Math.random() * 2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			vender_entradas((int) (Math.random() * entradaList.size()));
		}
	}

	public void comprar_entradas(int numEntradas) {
	    int entradasCompradas = 0;
	    for (Entrada entrada : Entrada.getListaEntradas()) {
	        if (entrada.getIsAvailable() && entradasCompradas < numEntradas) {
	            synchronized (entrada) {
	                if (entrada.getIsAvailable()) {
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
