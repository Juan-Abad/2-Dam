
public class Productor extends Thread{
	private Recurso r;
	private int n;
	public Productor(Recurso r, int n) {
		this.r = r;
		this.n = n;
	}
	public void run() {
		while(true) {
			r.poner(n);			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	
	}

}
