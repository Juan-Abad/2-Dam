
public class Consumidor extends Thread{
	private Recurso r;
	private int n;
	public Consumidor(Recurso r, int n) {
		this.r = r;
		this.n = n;
	}
	public void run() {
		while(true) {
			r.sacar(n);
		}
	
	}

}
