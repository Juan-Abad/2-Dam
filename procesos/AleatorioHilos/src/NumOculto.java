
public class NumOculto {
	private static Integer numOculto = (int) (Math.random()*9+1);
	private static Boolean acertado = false;
	
	public NumOculto() {
		
	}
	
	public int adviniarNumero(int numero) {
		int resultado = 0;
		if(acertado != true) {
			if(numero == this.numOculto) {
				acertado = true;
				resultado = 1;
			}
		}else {
			resultado = -1;
		}
		return resultado;
	}

	public Integer getNumOculto() {
		return numOculto;
	}
	
	
}
