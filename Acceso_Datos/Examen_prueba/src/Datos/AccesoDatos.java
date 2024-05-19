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
			sentencia = "DELETE FROM ventas";
			smt.execute(sentencia);
			sentencia = "DELETE FROM sqlite_sequence WHERE name='ventas'";
			smt.execute(sentencia);
		} catch (SQLException e) {
			System.out.println("Error crear tabla");
			System.out.println(
					e.getErrorCode() + ", " + e.getLocalizedMessage() + ", " + e.getSQLState() + ", " + e.getMessage());
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
			sentencia = "CREATE TABLE IF NOT EXISTS ventas(numero_venta Integer PRIMARY KEY AUTOINCREMENT,"
					+ "fecha text, producto text, talla text, color text, precio Integer, cantidad Integer)";
			smt.execute(sentencia);
		} catch (SQLException e) {
			System.out.println("Error crear tabla");
			System.out.println(
					e.getErrorCode() + ", " + e.getLocalizedMessage() + ", " + e.getSQLState() + ", " + e.getMessage());
		}
	}// fin_crearTabla

	public static boolean insertarVenta(String fecha, String producto, String talla, String color, Double precio, Integer cantidad) {
		String sentencia = "INSERT INTO ventas(fecha,producto,talla,color,precio,cantidad) VALUES (?,?,?,?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setString(1, fecha);
			smt.setString(2, producto);
			smt.setString(3, talla);
			smt.setString(4, color);
			smt.setDouble(5, precio);
			smt.setInt(6, cantidad);
			smt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error insertando pedido");
			System.out.println(e.getMessage());
			return false;
		}
	}// fin_insertarVenta


	public static boolean comprobarVenta(Integer numero_pedido) {
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

	public static boolean eliminarVenta_por_producto(String producto) {
		Statement smt;
		String sentencia = "DELETE FROM ventas WHERE producto LIKE '" + producto + "'";
		try {
			smt = conn.createStatement();
			smt.executeUpdate(sentencia);
			return true;
		} catch (SQLException e) {
			System.out.println("Error borrando venta");
			e.printStackTrace();
			return false;
		}
	}

	public static void mostrarVentas() {
		Statement smt;
		String sentencia = "SELECT * FROM ventas";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while (rs.next()) {
				System.out.println("\tNumero venta: " + rs.getInt("numero_venta") + "\n\tfecha: "
						+ rs.getString("fecha") + "\n\tProducto: " + rs.getString("Producto")+"\n\tTalla: "+rs.getShort(0)); // Imprime el resultado
																									// de la consulta
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
				System.out.println("\t\tArticulo:\n\t\t\tCodigo articulo: " + rs.getLong("codigo_articulo")
						+ "\n\t\t\tCantidad: " + rs.getInt("cantidad") + "\n"); // Imprime el resultado de la consulta
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
	}
}
