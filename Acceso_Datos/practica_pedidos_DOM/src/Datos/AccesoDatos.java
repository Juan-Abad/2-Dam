package Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class AccesoDatos {
	private static Connection conn;

	public static void conectar(String url) {
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("Error conexión");
		}
	}// fin_conectar

	public static void desconectar() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error desconexión");
		}
	}// fin_desconectar

	public static void crearTabla() {
		Statement smt;
		String sentencia;
		try {
			smt = (Statement) conn.createStatement();
			sentencia = "CREATE TABLE IF NOT EXISTS pedidos(numero-pedido Integer PRIMARY KEY,"
					+ "numero-cliente Integer, fecha text)";
			smt.execute(sentencia);
			sentencia = "CREATE TABLE IF NOT EXISTS articulos(codigo Integer PRIMARY KEY, cantidad Integer)";
			smt.execute(sentencia);
		} catch (SQLException e) {
			System.out.println("Error crear tabla");
		}
	}// fin_crerTabla

	public static void insertarPedido(Integer numero_cliente, Integer numero_pedido, String fecha) {
		String sentencia = "INSERT INTO pedidos(numero-pedido,numero-cliente,fecha) VALUES (?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setInt(1, numero_pedido);
			smt.setInt(2, numero_cliente);
			smt.setString(3, fecha);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error insertando palabra");

		}
	}// fin_insertarPalabra
	
	public static int comprobarPedido(Integer numero_cliente, Integer numero_pedido) {
		int resultado = 0;
		Statement smt;
		String sentencia = "SELECT numero-pedido, numero-cliente FROM pedidos WHERE numero-pedido = '"+numero_pedido+"'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			if(rs.wasNull()) {
				resultado = 0;
			}else {
				resultado = 1;
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
		}
		return resultado;
	}

	public static void borrarDatosTabla() {
		PreparedStatement smt;
		String sentencia = "DELETE FROM diccionario";
		try {
			smt = conn.prepareStatement(sentencia);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error borrar palabra");
		}
	}// fin_borrarDatosTabla

	public static TreeMap<String, Integer> listarDiccionario() {
		Statement smt;
		TreeMap<String, Integer> mapaDiccionario = new TreeMap<String, Integer>();
		String sentencia = "SELECT * FROM diccionario";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while (rs.next()) {
				mapaDiccionario.put(rs.getString("Palabra"), rs.getInt("Frecuencia"));
			}

		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
		}
		return mapaDiccionario;
	}// fin_listarDiccionario

	public static void modificarDiccionario(String palabra, Integer frecuencia) {
		Statement smt;
		String sentencia;
		try {
			smt = (Statement) conn.createStatement();
			sentencia = "UPDATE diccionario SET frecuencia= " + frecuencia + " WHERE palabra = '" + palabra + "'";
			smt.execute(sentencia);
		} catch (SQLException e) {
			System.out.println("Error actualizando");
		}
	}// fin_modificarDiccionario

	public static void borrarPalabra(String palabra) {
		PreparedStatement smt;
		String sentencia = "DELETE FROM diccionario WHERE palabra=?";
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setString(1, palabra);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error borrar palabra");
		}
	}// fin_borrarConctacto
}
