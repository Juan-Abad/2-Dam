package utilidades;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;

public class Leer {
	
	/**
	 * Método para imprimir mensaje 
	 */
	static public void mostrarEnPantalla(String mensaje){
		System.out.println(mensaje);
	}
	
	/**
	 * @param texto - Referencia al contenido del dato solicitado
	 * @return dato - cadena introducida por teclado
	 */
	static public String pedirCadena(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String dato="";
		boolean error = true;
		while (error) {
			try {
				dato="";
				mostrarEnPantalla(texto);
				dato = dataIn.readLine();
				error=false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor. ");
			}
		}
		return dato;
	}

	/**
	 * @param texto - Referencia al contenido del dato solicitado
	 * @param regex - expresión regular a aplicar. Si es null no aplica ninguna
	 * @return dato - cadena introducida por teclado
	 */
	static public String pedirCadena(final String texto, String regex) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String dato="";
		boolean error = true;
		while (error) {
			try {
				dato="";
				mostrarEnPantalla(texto);
				dato = dataIn.readLine();
				if(regex==null){
					error=false;
				}else{
					error=!dato.matches(regex);
					if(error){
						mostrarEnPantalla("Por favor, vuelve a introducir el dato, no es un valor posible. ");			
					}
				}
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor. ");
			} catch (PatternSyntaxException e){
				mostrarEnPantalla("La expresión regular no es correcta.");
				System.exit(0);
			}
		}
		return dato;
	}
	
	/**
	 * @param texto - Referencia al contenido del dato solicitado
	 * @return dato - Entero tecleado
	 */
	static public int pedirEntero(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		int dato=0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Integer.parseInt(dataIn.readLine());
				error=false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor.");
			} catch(NumberFormatException e){
				mostrarEnPantalla("El dato introducido no es entero.");
			}
		}
		return dato;
	}

	/**
	 * @param texto - Referencia al contenido del dato solicitado
	 * @param regex - expresión regular a aplicar. Si es null se aplica un entero genérico
	 * @return dato - Entero tecleado
	 */
	static public Integer pedirEntero(final String texto, String regex) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		Integer dato=null;
		boolean error = true;
		String datoT="";
		if(regex==null){
			regex="^-?\\d+$";
		}
		while (error) {
			try {
				datoT="";
				mostrarEnPantalla(texto);
				datoT = dataIn.readLine();
				dato = Integer.parseInt(datoT);
				if(datoT.matches(regex)){
					error=false;
				} else {
					mostrarEnPantalla("Por favor, vuelve a introducir el dato, no es un valor posible. ");
				}
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor. ");
			} catch(NumberFormatException e){
				mostrarEnPantalla("El dato introducido no es entero.");
			} catch (PatternSyntaxException e){
				mostrarEnPantalla("La expresión regular no es correcta.");
				System.exit(0);
			}
		}
		return dato;
	}
	
	/**
	 * @param texto
	 * @return
	 */
	static public int pedirEntero(final String[] texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		int dato = -1;
		boolean error = true;
		while (error) {
			try {
				while (dato < 0 || dato > texto.length - 1) {
					for (int i = 1; i < texto.length; i++) {
						mostrarEnPantalla(i + " " + texto[i]);
					}
					mostrarEnPantalla(0 + " " + texto[0]);

					dato = Integer.parseInt(dataIn.readLine());
					error = false;
				}
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor");
				// error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es entero");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
			}
		}
		return dato;

	}

	
	/**
	 * @param texto - Referencia al contenido del dato solicitado
	 * @return dato - Double tecleado
	 */
	static public double pedirDouble(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		double dato=0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Double.parseDouble(dataIn.readLine());
				error=false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor. ");
			} catch(NumberFormatException e){
				mostrarEnPantalla("El dato introducido no es decimal.");
			}
		}
		return dato;
	}		

	/**
	 * @param texto - Referencia al contenido del dato solicitado
	 * @param regex - expresión regular a aplicar. Si es null se aplica un double genérico
	 * @return dato - Double tecleado
	 */
	static public Double pedirDouble(final String texto, String regex) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		Double dato=null;
		boolean error = true;
		String datoT="";
		if(regex==null){
			regex="^-?\\d+([.]\\d*)?$";
		}
		while (error) {
			try {
				datoT="";
				mostrarEnPantalla(texto);
				datoT = dataIn.readLine();
				dato = Double.parseDouble(datoT);
				if(datoT.matches(regex)){
					error=false;
				} else {
					mostrarEnPantalla("Por favor, vuelve a introducir el dato, no es un valor posible. ");
				}
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor. ");
			} catch(NumberFormatException e){
				mostrarEnPantalla("El dato introducido no es decimal.");
			} catch (PatternSyntaxException e){
				mostrarEnPantalla("La expresión regular no es correcta.");
				System.exit(0);
			}
		}
		return dato;
	}

	/**
	 * @param texto - Referencia al contenido del dato solicitado
	 * @return dato - Float tecleado
	 */
	static public float pedirFloat(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		float dato=0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Float.parseFloat(dataIn.readLine());
				error=false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor. ");
			} catch(NumberFormatException e){
				mostrarEnPantalla("El dato introducido no es decimal.");
			}
		}
		return dato;
	}

	/**
	 * @param texto - Referencia al contenido del dato solicitado
	 * @param regex - expresión regular a aplicar. Si es null se aplica un float genérico
	 * @return dato - Float tecleado
	 */
	static public Float pedirFloat(final String texto, String regex) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		Float dato=null;
		boolean error = true;
		String datoT="";
		if(regex==null){
			regex="^-?\\d+([.]\\d*)?$";
		}
		while (error) {
			try {
				datoT="";
				mostrarEnPantalla(texto);
				datoT = dataIn.readLine();
				dato = Float.parseFloat(datoT);
				if(datoT.matches(regex)){
					error=false;
				} else {
					mostrarEnPantalla("Por favor, vuelve a introducir el dato, no es un valor posible. ");
				}
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor. ");
			} catch(NumberFormatException e){
				mostrarEnPantalla("El dato introducido no es decimal.");
			} catch (PatternSyntaxException e){
				mostrarEnPantalla("La expresión regular no es correcta.");
				System.exit(0);
			}
		}
		return dato;
	}

}
