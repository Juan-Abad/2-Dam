package ej07_AcademiaPatinaje;

public class Patinador extends Thread {
	private Almacen almacen;
	private Boolean principiante;

	private Integer numeroPie;
	
	private Patin patin;

	public Patinador(Boolean principiante, Almacen almacen) {
		this.principiante = principiante;
		this.almacen = almacen;
		this.numeroPie = ((int) (Math.random() * 10 + 34));
	}

	@Override
	public void run() {
		while(true) {s
			almacen.retirarPatin(numeroPie, principiante);
			System.out.println("Hilo" + Thread.currentThread() + "retiro patin");
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			almacen.almacenarPatin(numeroPie, principiante);
		}
	}

}
