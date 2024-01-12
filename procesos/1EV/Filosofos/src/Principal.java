
public class Principal {

	public static void main(String[] args) {// TODO Auto-generated method stub

		 Mesa m = new Mesa(5);
	        for (int i = 1; i <= 5; i++) {
	            Filosofo f = new Filosofo(m, i);
	            f.start();
	        }
	}

}
