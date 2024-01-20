
import java.util.concurrent.Semaphore;

public class FilosofosMain {

	public static void main(String[] args) {
		Semaphore[] palillos = { new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1),
				new Semaphore(1) };

		Filosofo[] filosofos = new Filosofo[5];

		for (int i = 0; i < filosofos.length; i++) {
			// Si es el último filósofo, intenta coger el último palillo y el primer
			// palillo.
			if (i == filosofos.length - 1) {// probar cambiando el orden 0,i
				// orden 0 i desbloqueado
				// orden i 0 no se produce bloqueo

				filosofos[i] = new Filosofo("Filosofo " + i, palillos[0], palillos[i]);
				filosofos[i].start();
			} else {
				// Los demás intentan coger su palillo (Mano izquierda) y el siguiente (Mano
				// derecha).
				filosofos[i] = new Filosofo("Filosofo " + i, palillos[i], palillos[i + 1]);
				filosofos[i].start();
			}

		}

	}

}