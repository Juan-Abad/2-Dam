package ej07_AcademiaPatinaje;

import java.util.ArrayList;
import java.util.TreeMap;

public class Almacen {
	private TreeMap<Integer, ArrayList<Patin>> mapaPatines;// mapa que guarda como clave el numero de pie de los patines, y en el arraylist, se guardan pares de patines

	public Almacen() {
		mapaPatines = new TreeMap<>();
		for (int i = 34; i < 44; i++) {//for que crea los patines segun el numero de pie
			ArrayList<Patin> listaParesPatin = new ArrayList<>();// se crea un arraylist que guarda dos patines de ese numero de pie, cada vez
			for (int j = 0; j < 2; j++) {// se añaden 2 patines, 2 pares por cada numero
				Patin patin = new Patin(i);//creamos un par de patin con ese numero de pie
				listaParesPatin.add(patin);//se añade al array dos pares a esa posicion
			}
			mapaPatines.put(i, listaParesPatin);//por cada numero de pie se guardan 2 pares de patines, se indica el numero como clave y el arraylist con los dos pares
		}
	}

	synchronized public void almacenarPatin(Integer numeroPie, Boolean pieDerecho) {//metodo synchronized que almacena patines en el almacen
		if (mapaPatines.containsKey(numeroPie)) {//comprobamos que el mapa contenga el numero de pie
			for (Patin patin : mapaPatines.get(numeroPie)) {//recorremos el arraylist del mapa de ese numero de pie
				if (!patin.isParCompleto()) {//comprobamos si el par es completo, entra si no lo esta
					if (pieDerecho != null) {//si pie derecho no es null significa que quiere almacenar un solo pie de patin izq o derecho
						if (pieDerecho && !patin.getPatinDerecho()) {//comprobamos que se quiere almacenar un pie derecho o no, y si es este el par de patines que le falta un patin derecho
							patin.setPatinDerecho(true);
						} else if (!patin.getPatinIzquierdo()) {//quiere almacenar un patin izq, comprueba si este es el par que le falta un patin izq
							patin.setPatinIzquierdo(true);
						}
					} else {
						if (!patin.getPatinDerecho() && !patin.getPatinIzquierdo()) {//se quiere almacenar un par completo, pie izq y derecho, comprueba si este es el par que le faltan los dos
							patin.setPatinDerecho(true);
							patin.setPatinIzquierdo(true);
						}
					}
				}
			}
			System.out.println("Hilo" + Thread.currentThread() + "almaceno patin, número de pie: " + numeroPie
					+ ", principiante: " + pieDerecho);//imprime que el hilo almacena un numero de pie
			notifyAll();
		} else {
			System.out.println("Número de pie no exitente");//imprime que el numero de pie dado no existe en el almacen
		}
	}

	synchronized public void retirarPatin(Integer numeroPie, Boolean pieDerecho) {//metodo para retirar patines
		if (mapaPatines.containsKey(numeroPie)) {//comprueba que el pie que se recibe para retirar, existe en el almacen
			for (Patin patin : mapaPatines.get(numeroPie)) {//recorre el arraylist del mapa para ese pie(clave del mapa), cada iteracion es un patin
				if (pieDerecho != null) {//comprueba si se quiere retirar un par completo
					if (pieDerecho == true) {//comprueba si quiere retirar solo el pie derecho
						while (!patin.getPatinDerecho()) {//si no se puede retirar espera a que lo vuelvan a almacenar
							try {
								wait();//espera a que se pueda retirar un pie derecho
							} catch (InterruptedException ex) {
								ex.printStackTrace();
							}
						}
						patin.setPatinDerecho(false);//cambiariamos el estado del patin derecho a false
					} else if (pieDerecho == false) {//comprueba si quiere retirar solo el pie izquierdo
						while (!patin.getPatinIzquierdo()) {
							try {
								wait();//espera a que se pueda retirar un pie izq
							} catch (InterruptedException ex) {
								ex.printStackTrace();
							}
						}
						patin.setPatinIzquierdo(false);//cambiariamos el estado del patin izq a false
					}
				} else {
					while (!patin.isParCompleto()) {//comprueba si puede retirar el par completo
						try {
							wait();
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
					patin.setPatinDerecho(false);
					patin.setPatinIzquierdo(false);
				}
			}
			System.out.println("Hilo" + Thread.currentThread() + "retiro patin, número de pie: " + numeroPie
					+ ", principiante: " + pieDerecho);
			notifyAll();//notifica que se han retirado patines
		} else {
			System.out.println("Número de pie no exitente");
		}
	}
}
