import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread{
	Socket s;
	Monedero m;
	
	public ClientHandler(Socket s, Monedero m) {
		super();
		this.s = s;
		this.m = m;
	}
	
	public void run() {
		try(DataInputStream in = new DataInputStream(s.getInputStream());
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
				){
			synchronized (m) {			
			System.out.println("Se ha conectado: "+s.getInetAddress());
			int cantidad= in.readInt();
			if(cantidad<200) {
			m.donar(cantidad);
			}else {
				s.close();
			}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
