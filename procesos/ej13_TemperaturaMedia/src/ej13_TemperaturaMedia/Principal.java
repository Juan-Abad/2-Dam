package ej13_TemperaturaMedia;

public class Principal {

	public static void main(String[] args) {
		Double temperatura_media;
		
		if(args.length==1) {
			System.err.println("Error");
		}else {
			int temperatura = (int) (Math.random()*20 +10);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(temperatura);
		}
	}

}
