
public class Peluquera {
	
	private Boolean ocupada = false;
	private Integer tiempoOcupada;
	
	public Peluquera(){
		
	}
	
	
	synchronized public void atenderCliente(String nombreCliente) {
		tiempoOcupada = ((int) (Math.random()*149+51));
		System.out.println(tiempoOcupada);
		
		ocupada = true;
	}
	
	public void trabajar() {
		
	}
}
