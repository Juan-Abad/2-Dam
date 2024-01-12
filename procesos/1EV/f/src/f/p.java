package f;

public class p {

	public static void main(String[] args) {
		int a = ((int) (Math.random() * 9 + 34));
		for(int i=0; i<30;i++) { 
			a = ((int) (Math.random() * 10 + 34));
			if(a == 34 || a == 44)
				System.out.println(a);
			}

	}

}
