
//recurso compartido
//contine un valor
// Si el valor es 0 no esta diospoible, no se puede sacar
//metodos sincronicados para consumir sacar
//metodos sincronicados para producir poner


public class Recurso {
	private int numero=0;
	
	
	public synchronized void poner(int valor) {
		while(numero>100) {
			try {
				wait(); 
				//Los procesos productores se bloquearan aqui 
				//A la espera de un notify
				//Si esta disponible no lleno
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		numero=numero+valor;
		System.out.println("productor"+ " añade "+ valor+ " cantidad recurso "+ numero);
		notifyAll();	
		}
	
	public synchronized void sacar(int valor) {
		System.out.println("Intentando sacar "+ valor);
		while (numero-valor<0) {
			try {
				wait();
				//los procesos consumidores se pararan aqui a la espera de un notify
				//mientras no este disponible espero
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		numero=numero-valor;
		System.out.println("Consumidor " + " consume "+ valor+ "recurso compartido vale "+ numero);
		notifyAll();
		
	}

}
