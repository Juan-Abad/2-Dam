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
		Statement smt;
		String sentencia;
		try {
			smt = (Statement) conn.createStatement();
			sentencia = "DELETE FROM pedidos";
			smt.execute(sentencia);
			sentencia = "DELETE FROM pedido_articulo";
			smt.execute(sentencia);
			sentencia = "DELETE FROM articulos";
			smt.execute(sentencia);
			sentencia = "DELETE FROM clientes";
			smt.execute(sentencia);
		} catch (SQLException e) {
			System.out.println("Error crear tabla");
			System.out.println(e.getErrorCode()+", "+e.getLocalizedMessage()+", "+e.getSQLState()+", "+e.getMessage());
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
			sentencia = "CREATE TABLE IF NOT EXISTS pedidos(numero_pedido Integer PRIMARY KEY,"
					+ "numero_cliente Integer, fecha text)";
			smt.execute(sentencia);
			sentencia = "CREATE TABLE IF NOT EXISTS pedido_articulo(numero_pedido Integer, codigo_articulo Integer, cantidad Integer)";
			smt.execute(sentencia);
			sentencia = "CREATE TABLE IF NOT EXISTS articulos(codigo_articulo Integer, descripcion text, familia text, stock Integer)";
			smt.execute(sentencia);
			sentencia = "CREATE TABLE IF NOT EXISTS clientes(numero_cliente Integer, nombre text, direccion text, telefono Integer)";
			smt.execute(sentencia);
		} catch (SQLException e) {
			System.out.println("Error crear tabla");
			System.out.println(e.getErrorCode()+", "+e.getLocalizedMessage()+", "+e.getSQLState()+", "+e.getMessage());
		}
	}// fin_crearTabla

	public static void insertarPedido(Long numero_pedido, Long numero_cliente, String fecha) {
		String sentencia = "INSERT INTO pedidos(numero_pedido,numero_cliente,fecha) VALUES (?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setLong(1, numero_pedido);
			smt.setLong(2, numero_cliente);
			smt.setString(3, fecha);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error insertando pedido");
			System.out.println(e.getMessage());

		}
	}// fin_insertarPedido
	
	public static void insertarPedido_articulo(Long numero_pedido, Long codigo_articulo, Integer cantidad) {
		String sentencia = "INSERT INTO pedido_articulo(numero_pedido,codigo_articulo,cantidad) VALUES (?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setLong(1, numero_pedido);
			smt.setLong(2, codigo_articulo);
			smt.setInt(3, cantidad);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error insertando pedido_articulo");

		}
	}// fin_insertarPedido_articulo
	
	public static void insertarArticulo(Long codigo_articulo, String descripcion, String familia, Integer stock) {
		String sentencia = "INSERT INTO articulos(codigo_articulo,descripcion,familia,stock) VALUES (?,?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setLong(1, codigo_articulo);
			smt.setString(2, descripcion);
			smt.setString(3, familia);
			smt.setInt(4, stock);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error insertando articulo");

		}
	}// fin_insertarArticulo
	
	public static void insertarCliente(Long numero_cliente, String nombre, String direccion, Long telefono) {
		String sentencia = "INSERT INTO pedidos(numero_pedido,numero_cliente,fecha) VALUES (?,?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setLong(1, numero_cliente);
			smt.setString(2, nombre);
			smt.setString(3, direccion);
			smt.setLong(4, telefono);
			smt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error insertando cliente");

		}
	}// fin_insertarCliente
	
	public static int comprobarPedido(Integer numero_cliente, Integer numero_pedido) {
		int resultado = 0;
		Statement smt;
		String sentencia = "SELECT numero_pedido, numero_cliente FROM pedidos WHERE numero_pedido = '"+numero_pedido+"'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while(rs.next()) {
				System.out.println(rs.getLong("numero_cliente")+", "+rs.getLong("numero_pedido"));
				resultado = 1;
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static int comprobarPedido_articulo(Long numero_pedido, Long codigo_articulo) {
		int resultado = 0;
		Statement smt;
		String sentencia = "SELECT numero_pedido, codigo_articulo FROM pedido_articulo WHERE numero_pedido = '"+numero_pedido+"' AND codigo_articulo = '"+codigo_articulo+"'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while(rs.next()) {
				System.out.println(rs.getLong("numero_pedido")+", "+rs.getLong("codigo_articulo")+", "+rs.getInt("cantidad"));
				resultado = 1;
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
		return resultado;
	}

	/*public static void borrarDatosTabla() {
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
	}// fin_borrarConctacto*/
}
