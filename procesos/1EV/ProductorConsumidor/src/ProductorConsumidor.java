/*
 * En este tipo de problemas tenemos un recurso compartido y unos hilos
 * que consumen de ese recurso compartido y otros que generan.
 * Se establecen unos limites para el consumo y la generación de manera que los hilos
 *  consumidores deben esperar hasta que puedan consumir y los generadores hasta que puedan generear
 *  
 */
public class ProductorConsumidor {

	public static void main(String[] args) {
		Recurso r=new Recurso();
		Productor p=new Productor(r,100);//priductor añade 1 al recurso
		Consumidor c=new Consumidor(r,30);//hilo consumidor consume 1
		Consumidor d=new Consumidor (r,20);// hilo consumidor consume 2
		c.start();
		d.start();
		p.start();
		
	}

}
