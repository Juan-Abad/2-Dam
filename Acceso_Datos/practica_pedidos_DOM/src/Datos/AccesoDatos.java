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
		
		  /*Statement smt; String sentencia; try { smt = (Statement)
		  conn.createStatement(); sentencia = "DELETE FROM pedidos";
		  smt.execute(sentencia); sentencia = "DELETE FROM pedido_articulo";
		  smt.execute(sentencia); sentencia = "DELETE FROM articulos";
		  smt.execute(sentencia); sentencia = "DELETE FROM clientes";
		  smt.execute(sentencia); } catch (SQLException e) {
		  System.out.println("Error crear tabla");
		  System.out.println(e.getErrorCode()+", "+e.getLocalizedMessage()+", "+e.
		  getSQLState()+", "+e.getMessage()); }*/
		 
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
			System.out.println(
					e.getErrorCode() + ", " + e.getLocalizedMessage() + ", " + e.getSQLState() + ", " + e.getMessage());
		}
	}// fin_crearTabla

	public static boolean insertarPedido(Long numero_pedido, Long numero_cliente, String fecha) {
		String sentencia = "INSERT INTO pedidos(numero_pedido,numero_cliente,fecha) VALUES (?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setLong(1, numero_pedido);
			smt.setLong(2, numero_cliente);
			smt.setString(3, fecha);
			smt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error insertando pedido");
			System.out.println(e.getMessage());
			return false;
		}
	}// fin_insertarPedido

	public static boolean insertarPedido_articulo(Long numero_pedido, Long codigo_articulo, Integer cantidad) {
		String sentencia = "INSERT INTO pedido_articulo(numero_pedido,codigo_articulo,cantidad) VALUES (?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setLong(1, numero_pedido);
			smt.setLong(2, codigo_articulo);
			smt.setInt(3, cantidad);
			smt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error insertando pedido_articulo");
			return false;

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

	public static boolean insertarCliente(Long numero_cliente, String nombre, String direccion, Long telefono) {
		String sentencia = "INSERT INTO clientes VALUES (?,?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setLong(1, numero_cliente);
			if(nombre!=null) {
				smt.setString(2, nombre);
			}else {
				smt.setNull(2, 0);
			}
			if(direccion!=null) {
				smt.setString(3, direccion);
			}else {
				smt.setNull(3, 0);
			}
			if(telefono!=null) {
				smt.setLong(4, telefono);
			}else {
				smt.setNull(4, 0);
			}
			smt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error insertando cliente");
			System.out.println(e.getMessage());
			return false;

		}
	}// fin_insertarCliente

	public static boolean comprobarPedido(Integer numero_pedido) {
		boolean resultado = false;
		Statement smt;
		String sentencia = "SELECT numero_pedido, numero_cliente FROM pedidos WHERE numero_pedido = '" + numero_pedido
				+ "'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while (rs.next()) {
				// System.out.println(rs.getLong("numero_cliente")+",
				// "+rs.getLong("numero_pedido")); Imprime el resultado de la consulta
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
		return resultado;
	}

	public static boolean comprobarCliente(Long numero_cliente) {
		boolean resultado = false;
		Statement smt;
		String sentencia = "SELECT numero_cliente FROM clientes WHERE numero_cliente = '" + numero_cliente + "'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while (rs.next()) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public static int comprobarPedido_articulo(Long numero_pedido, Long codigo_articulo) {
		int resultado = 0;
		Statement smt;
		String sentencia = "SELECT numero_pedido, codigo_articulo, cantidad FROM pedido_articulo WHERE numero_pedido = '"
				+ numero_pedido + "' AND codigo_articulo = '" + codigo_articulo + "'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while (rs.next()) {
				/*System.out.println(rs.getLong("numero_pedido") + ", " + rs.getLong("codigo_articulo") + ", "
						+ rs.getInt("cantidad"));*/
				//Este codigo imprime cada fila de ese pedido, en la tabla pedido_articulos
				resultado = 1;
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static boolean comprobarArticulo(Long codigo_articulo) {
		boolean resultado = false;
		Statement smt;
		String sentencia = "SELECT codigo_articulo FROM articulos WHERE codigo_articulo = '" + codigo_articulo + "'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while (rs.next()) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static int comprobar_stock_articulo(Long codigo_articulo) {
		int stock = 0;
		Statement smt;
		String sentencia = "SELECT stock FROM articulos WHERE codigo_articulo = '" + codigo_articulo + "'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while (rs.next()) {
				stock = rs.getInt("stock");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stock;
	}
	
	public static boolean eliminarPedido(Long numero_pedido) {
		Statement smt;
		String sentencia_pedido = "DELETE FROM pedidos WHERE numero_pedido = '" + numero_pedido + "'";
		String sentencia_pedidoArticulos = "DELETE FROM pedido_articulo WHERE numero_pedido = '" + numero_pedido + "'";
		try {
			smt = conn.createStatement();
			smt.executeUpdate(sentencia_pedido);
			smt.executeUpdate(sentencia_pedidoArticulos);
			return true;
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
			return false;
		}
	}

	public static void mostrarPedido(Long numero_pedido) {
		Statement smt;
		String sentencia = "SELECT * FROM pedidos WHERE numero_pedido = '" + numero_pedido + "'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while (rs.next()) {
				System.out.println("\tNumero pedido: " + rs.getLong("numero_pedido") + "\n\tNumero cliente: "
						+ rs.getLong("numero_cliente") + "\n\tFecha: " + rs.getString("fecha")); // Imprime el resultado de la consulta
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
	}
	
	public static void mostrarPedido_articulo(Long numero_pedido) {
		Statement smt;
		String sentencia = "SELECT * FROM pedido_articulo WHERE numero_pedido = '" + numero_pedido + "'";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			System.out.println("\tArticulos:");
			while (rs.next()) {
				System.out.println("\t\tArticulo:\n\t\t\tCodigo articulo: "
						+ rs.getLong("codigo_articulo") + "\n\t\t\tCantidad: " + rs.getInt("cantidad")+"\n"); // Imprime el resultado de la consulta
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
	}
}
