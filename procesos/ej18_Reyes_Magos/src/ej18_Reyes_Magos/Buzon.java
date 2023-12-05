package ej18_Reyes_Magos;

/*
 * author: Juan Abad Hernández
 * Date: 30/11/2023
 */
public class Buzon {// clase buzon
	private Integer numero_cartas_enviadas = 0;// variable Integer que guaarda el numero de cartas que se han enviado

	synchronized public void recibir_carta(Rey rey) {// metodo synchronized recibir_carta, recibe por parametros el rey
														// que envia la carta. En caso de que el rey sea null, la carta
														// ha sido enviada por un niño sin rey
		numero_cartas_enviadas++;// se suma uno, ya que se envia una carta
		if (rey != null) {// si es una carta de niño con rey entra
			System.out.println("Recibida carta: Rey" + rey.getIdRey() + " - niño " + Thread.currentThread().getId());
		} else {
			System.out.println("Recibida carta: SinRey - " + Thread.currentThread().getId());
		}
		try {
			Thread.sleep(100);// tiempo que trada el buzon en vaciarse
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		enviar_carta(rey);// llama a un metodo pasandole el rey
	}

	public void enviar_carta(Rey rey) {// metodo que recibe el rey, este metodo procesa las cartas, comprueba si es
										// caarta con rey o sin rey
		if (rey != null) {
			System.out.println("Procesada carta: Rey" + rey.getIdRey() + " - niño " + Thread.currentThread().getId());
		} else {
			System.out.println("Procesada carta: SinRey - " + Thread.currentThread().getId());
		}
	}

}
