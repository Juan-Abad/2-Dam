
public class PrincipalTicTac {

	public static void main(String[] args) {
		//Creo dos hilos uno que escribe tic y otro tac
		Thread htic=new Thread(new Runnable() {

			@Override
			public void run() {
				while(true)
				System.out.println("tic");
				
			}});
		Thread htac=new Thread(new Runnable() {

			@Override
			public void run() {
				while(true)
				System.out.println("tac");
				
			}});
		htic.start();
		htac.start();

	}

}