
public class Mesa {
	private boolean[] tenedores;
	int [] frecuencia;
    
    public Mesa(int numTenedores){
        this.tenedores = new boolean[numTenedores];
        this.frecuencia=new int[numTenedores];
    }
     
    public int tenedorIzquierda(int i){
        return i;
    }
     
    public int tenedorDerecha(int i){
        if(i == 0){
            return this.tenedores.length - 1;
        }else{
            return i - 1;
        }
    }
     
    public synchronized void cogerTenedores(int comensal){
         
        while(tenedores[tenedorIzquierda(comensal)] || tenedores[tenedorDerecha(comensal)]){
            try {   
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
         
        tenedores[tenedorIzquierda(comensal)] = true;
        tenedores[tenedorDerecha(comensal)] = true;

    }
     
    public synchronized void dejarTenedores(int comensal){
    	frecuencia[comensal]++;
        tenedores[tenedorIzquierda(comensal)] = false;
        tenedores[tenedorDerecha(comensal)] = false;
        notifyAll();
    }

	public synchronized int[] getFrecuencia() {
		return frecuencia;
	}
    

}
