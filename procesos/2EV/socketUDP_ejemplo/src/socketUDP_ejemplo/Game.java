package socketUDP_ejemplo;

import java.util.Arrays;

public class Game {
	private String[][] tablero = { { "-", "-", "-" }, { "-", "-", "-" }, { "-", "-", "-" } };

	public int comprobarGanador() {
		int resultado = 0;
		int lineaCompleta = 0;
		int[] filaCompleta = new int[3];
		String[] diagonal1 = new String[5];
		String[] diagonal2 = new String[5];
		Arrays.fill(diagonal1, "");
		Arrays.fill(diagonal2, "");

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (getTablero()[i][j] != "-") {
					if (resultado == 0) {
						resultado = -1;
					}
					if (getTablero()[i][j].equals("X")) {
						filaCompleta[j]++;
						lineaCompleta++;
					} else {
						filaCompleta[j]--;
						lineaCompleta--;
					}
					char symbol = getTablero()[i][j].charAt(0);
					diagonal1[j + i] += symbol;
					diagonal2[2 - j + i] += symbol;
				} else {
					diagonal1[j + i] += " ";
					diagonal2[2 - j + i] += " ";
				}
			}
			if (lineaCompleta == 3) {
				resultado = 1;
			} else if (lineaCompleta == -3) {
				resultado = 2;
			}
			lineaCompleta = 0;
		}

		for (int i : filaCompleta) {
			switch (i) {
			case 3:
				resultado = 1;
				break;
			case -3:
				resultado = 2;
				break;
			}
		}

		for (String diagonal : diagonal1) {
			if (diagonal.matches(".*XXX.*")) {
				resultado = 1;
			} else if (diagonal.matches(".*OOO.*")) {
				resultado = 2;
			}
		}

		for (String diagonal : diagonal2) {
			if (diagonal.matches(".*XXX.*")) {
				resultado = 1;
			} else if (diagonal.matches(".*OOO.*")) {
				resultado = 2;
			}
		}
		return resultado;
	}

	public String[][] getTablero() {
		return this.tablero;
	}

	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}	
}