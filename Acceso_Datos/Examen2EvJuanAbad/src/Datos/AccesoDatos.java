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

		// codigo que elimina la tabla inventario
		/*
		 * Statement smt; String sentencia; try { smt = (Statement)
		 * conn.createStatement(); sentencia = "DELETE FROM inventario";
		 * smt.execute(sentencia); sentencia =
		 * "DELETE FROM sqlite_sequence WHERE name='inventario'";
		 * smt.execute(sentencia); } catch (SQLException e) {
		 * System.out.println("Error crear tabla"); System.out.println( e.getErrorCode()
		 * + ", " + e.getLocalizedMessage() + ", " + e.getSQLState() + ", " +
		 * e.getMessage()); }
		 */

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
			sentencia = "CREATE TABLE IF NOT EXISTS inventario(articulo Integer PRIMARY KEY AUTOINCREMENT,"
					+ "fecha text, producto text, tipo text, precio Integer, cantidad Integer)";
			smt.execute(sentencia);
		} catch (SQLException e) {
			System.out.println("Error crear tabla");
			System.out.println(
					e.getErrorCode() + ", " + e.getLocalizedMessage() + ", " + e.getSQLState() + ", " + e.getMessage());
		}
	}// fin_crearTabla

	public static boolean insertarArticulo(String fecha, String producto, String tipo, Double precio,
			Integer cantidad) {
		String sentencia = "INSERT INTO inventario(fecha,producto,tipo,precio,cantidad) VALUES (?,?,?,?,?)";
		PreparedStatement smt;
		try {
			smt = conn.prepareStatement(sentencia);
			smt.setString(1, fecha);
			smt.setString(2, producto);
			smt.setString(3, tipo);
			smt.setDouble(4, precio);
			smt.setInt(5, cantidad);
			smt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error insertando Articulo");
			System.out.println(e.getMessage());
			return false;
		}
	}// fin_insertarArticulo

	public static boolean Articulo_con_mayor_num_unidades() {
		boolean resultado = false;
		Statement smt;
		String sentencia = "SELECT cantidad, producto, fecha, tipo, precio FROM inventario GROUP BY producto ORDER BY cantidad DESC LIMIT 1";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			System.out.println("\nArtículos con mayor número de unidades:\n");
			while (rs.next()) {
				// System.out.println(rs.getLong("numero_cliente")+",
				// "+rs.getLong("numero_pedido")); Imprime el resultado de la consulta
				System.out.println("\t" + rs.getString("producto") + "  " + rs.getString("fecha") + "  "
						+ rs.getString("tipo") + "  " + rs.getDouble("cantidad"));
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
		return resultado;
	}

	public static boolean Cant_articulos_por_tipo() {
		boolean resultado = false;
		Statement smt;
		String sentencia = "SELECT SUM(cantidad) AS cantidad_suma, tipo FROM inventario GROUP BY tipo";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			System.out.println("\nCantidad de artículos por tipo:\n");
			while (rs.next()) {
				// System.out.println(rs.getLong("numero_cliente")+",
				// "+rs.getLong("numero_pedido")); Imprime el resultado de la consulta
				System.out.println("\t" + rs.getString("tipo") + " = " + rs.getString("cantidad_suma") + " unidades");
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
		return resultado;
	}

	public static boolean Calc_inventario_total() {
		boolean resultado = false;
		Statement smt;
		String sentencia = "SELECT SUM(cantidad * precio) AS inventario_total FROM inventario";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			while (rs.next()) {
				// System.out.println(rs.getLong("numero_cliente")+",
				// "+rs.getLong("numero_pedido")); Imprime el resultado de la consulta
				System.out.println("\nInventario total: " + rs.getString("Inventario_total"));
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
		return resultado;
	}

}
