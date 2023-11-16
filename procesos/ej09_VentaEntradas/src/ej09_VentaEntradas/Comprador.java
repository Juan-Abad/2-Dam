package ej09_VentaEntradas;

import java.util.ArrayList;

public class Comprador extends Thread {
	private ArrayList<Entrada> entradaList = new ArrayList<Entrada>();

	private Integer numero_entradas_aComprar;
	private Integer numero_entradas_aVender;

	private Integer idComprador;
	private static Integer siguienteID = 1;

	public Comprador() {
		this.idComprador = siguienteID;
		siguienteID++;
	}

	public void run() {
		while (true) {
			comprar_entradas((int) (Math.random()*9+1));
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			vender_entradas((int) (Math.random()*entradaList.size()+1));
		}
	}

	synchronized public void comprar_entradas(int numEntradas) {
		while (Entrada.getNumeroEntradasDisponibles() < numEntradas) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		boolean entrada_comprada = false;
		for (int i=0; i<numEntradas; i++) {
			if (Entrada.getListaEntradas().get(i)!=null && Entrada.getListaEntradas().get(i).getIsAvailable() && entrada_comprada) {
				Entrada entrada = Entrada.getListaEntradas().get(i);
				System.out.println("Comprador: " + idComprador + ", compra entrada " + entrada.getIdEntrada());
				entrada.setIsAvailable(false);
				entradaList.add(entrada);
				entrada_comprada = true;
			}
		}
		Entrada.setNumeroEntradasDisponibles(Entrada.getNumeroEntradasDisponibles() - numEntradas);
		notifyAll();
	}

	synchronized public void vender_entradas(int numEntradas) {
		Entrada.setNumeroEntradasDisponibles(Entrada.getNumeroEntradasDisponibles() + numEntradas);
		for (int i = 0; i < numEntradas; i++) {
			System.out.println("Comprador: " + idComprador + ", vende entrada " + entradaList.get(i));
			entradaList.get(i).setIsAvailable(true);
			Entrada.getListaEntradas().get(entradaList.get(i).getIdEntrada() - 1);
			entradaList.remove(entradaList.get(i));
		}
		notifyAll();
	}
}
