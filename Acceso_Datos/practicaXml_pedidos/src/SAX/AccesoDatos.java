package SAX;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class AccesoDatos {
	private static Connection conn;
	
	public static void conectar(String url) {
		try {
			conn=DriverManager.getConnection("jdbc:sqlite:pedidosConSAX.db");
		} catch (SQLException e) {
			System.out.println("Error conexión");
		}
	}//fin_conectar
	
	public static void desconectar() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error desconexión");
		}
	}//fin_desconectar
	
	public static void crearTabla() {
		Statement smt;
		String sentencia;
		try {
			smt=(Statement) conn.createStatement();
			sentencia="CREATE TABLE IF NOT EXISTS pedidos(numero_pedido Integer PRIMARY KEY,"
					+ "nombre text)";
			smt.execute(sentencia);
			sentencia="CREATE TABLE IF NOT EXISTS articulos(descripcion text, cantidad Integer)";
			smt.execute(sentencia);
		} catch (SQLException e) {
			System.out.println("Error crear tabla");
		}
	}//fin_crerTabla
	
	public static void insertarPedido(String nombre,Integer numero_pedido) {
		String sentencia="INSERT INTO diccionario(palabra,frecuencia) VALUES (?,?)";
		PreparedStatement smt;
		try {
			smt=conn.prepareStatement(sentencia);
			smt.setString(1, nombre);
			smt.setInt(2,numero_pedido);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error insertando palabra");
		}
	}//fin_insertarPalabra
	
	public static void borrarDatosTabla() {
		PreparedStatement smt;
		String sentencia="DELETE FROM diccionario";
		try {
			smt=conn.prepareStatement(sentencia);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error borrar palabra");
		}
	}//fin_borrarDatosTabla
	
	public static TreeMap<String, Integer> listarDiccionario() {
		Statement smt;
		TreeMap<String, Integer> mapaDiccionario = new TreeMap<String, Integer>();
		String sentencia="SELECT * FROM diccionario";
		try {
			smt=conn.createStatement();
			ResultSet rs=smt.executeQuery(sentencia);
			while(rs.next()) {
				mapaDiccionario.put(rs.getString("Palabra"),rs.getInt("Frecuencia"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
		}
		return mapaDiccionario;
	}//fin_listarDiccionario
	
	public static void modificarDiccionario(String palabra, Integer frecuencia) {
		Statement smt;
		String sentencia;
		try {
			smt=(Statement) conn.createStatement();
			sentencia="UPDATE diccionario SET frecuencia= "+frecuencia+" WHERE palabra = '"+palabra+"'";
			smt.execute(sentencia);
		} catch (SQLException e) {
			System.out.println("Error actualizando");
		}
	}//fin_modificarDiccionario
	
	public static void borrarPalabra(String palabra) {
		PreparedStatement smt;
		String sentencia="DELETE FROM diccionario WHERE palabra=?";
		try {
			smt=conn.prepareStatement(sentencia);
			smt.setString(1, palabra);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error borrar palabra");
		}
	}//fin_borrarConctacto
}
