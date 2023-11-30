package ej17_amazon_black_friday;

import java.util.TreeMap;

public class Amazon {
	private TreeMap<Integer, Boolean> lista_moviles = new TreeMap<Integer, Boolean>();
	
	public Amazon() {
		for(int i=0; i<10; i++) {
			lista_moviles.put(i, true);
		}
	}
	
	synchronized public int comprar_movil() {
		for(Integer index: lista_moviles.keySet()) {
			if(lista_moviles.get(index)) {
				lista_moviles.put(index, false);
				return index;
			}
		}
		return -1;
	}
	
}
