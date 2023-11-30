

public class Temperatura {

	public static void main(String[] args) {
		int temperatura = 0;

		if (args.length != 1) {
			System.out.println("ERROR");
		} else {
			temperatura = (int) ((Math.random() * 50) - 10);
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(temperatura);
	}

}
