package Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class AccesoDatos {
	private static Connection conn;

	public static void conectar(String url) {
		try {
			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("Conexion completada");
		} catch (SQLException e) {
			System.out.println("Error al conectar" + e.getMessage() + ", sqlstate: " + e.getSQLState());
		}
	}// fin_conectar

	public static void desconectar() {
		try {
			conn.close();
			System.out.println("Desconexion completada");
		} catch (SQLException e) {
			System.out.println("Error al desconectar");
		}
	}// fin_desconectar

	public static boolean insertarUsuario(String email, String pwd_hash) {
		if (comprobar_usuarioExistente(email) == -1) {
			String sentencia = "INSERT INTO usuario(email,pwd_hash) VALUES (?,?)";
			PreparedStatement smt;
			try {
				smt = conn.prepareStatement(sentencia);
				smt.setString(1, email);
				smt.setString(2, pwd_hash);
				smt.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println("Error insertando Usuario");
				System.out.println(e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}// fin_insertarUsuario

	/*
	 * opciones de resultado: 0 = usuario existente -1 = usuario no existente
	 */
	public static int comprobar_usuarioExistente(String email) {
		int resultado = -1;
		PreparedStatement ps;
		String sentencia = "SELECT COUNT(*) AS total FROM usuario WHERE email = ?";
		try {
			ps = conn.prepareStatement(sentencia);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int totalUsuarios = rs.getInt("total");
				if (totalUsuarios == 1) {
					resultado = 0;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
		return resultado;
	}// fin_comprobar_usuarioExistente

	/*
	 * opciones de resultado: 1 = contraseña incorrecta 2 = usuario no existente 0 =
	 * login correcto
	 */
	public static int login(String email, String pwd_hash) {
		int resultado = 0;
		if (comprobar_usuarioExistente(email) == 0) {
			PreparedStatement ps;
			String sentencia = "SELECT pwd_hash FROM usuario WHERE email = ?";
			try {
				ps = conn.prepareStatement(sentencia);
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					String hash = rs.getString("pwd_hash");
					if (hash.equals(pwd_hash)) {
						resultado = 0;
					} else {
						resultado = 1;
					}
				}
			} catch (SQLException e) {
				System.out.println("Error obteniendo datos");
				e.printStackTrace();
			}
		} else {
			resultado = 2;
		}
		return resultado;
	}// fin_comprobar_usuarioExistente

	public static String get_Riot_apiKey() {
		String api_key = "";
		Statement smt;
		String sentencia = "SELECT api_key FROM api_key WHERE api_name = 'Riot_api' Limit 1";
		try {
			smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sentencia);
			if(rs.next()) {
				api_key = rs.getString("api_key");
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo datos");
			e.printStackTrace();
		}
		return api_key;
	}// fin_comprobar_usuarioExistente

	/*
	 * public static boolean insertarArticulo(String fecha, String producto, String
	 * tipo, Double precio, Integer cantidad) { String sentencia =
	 * "INSERT INTO inventario(fecha,producto,tipo,precio,cantidad) VALUES (?,?,?,?,?)"
	 * ; PreparedStatement smt; try { smt = conn.prepareStatement(sentencia);
	 * smt.setString(1, fecha); smt.setString(2, producto); smt.setString(3, tipo);
	 * smt.setDouble(4, precio); smt.setInt(5, cantidad); smt.executeUpdate();
	 * return true; } catch (SQLException e) {
	 * System.out.println("Error insertando Articulo");
	 * System.out.println(e.getMessage()); return false; } }// fin_insertarArticulo
	 * 
	 * public static boolean Articulo_con_mayor_num_unidades() { boolean resultado =
	 * false; Statement smt; String sentencia =
	 * "SELECT cantidad, producto, fecha, tipo, precio FROM inventario GROUP BY producto ORDER BY cantidad DESC LIMIT 1"
	 * ; try { smt = conn.createStatement(); ResultSet rs =
	 * smt.executeQuery(sentencia);
	 * System.out.println("\nArtículos con mayor número de unidades:\n"); while
	 * (rs.next()) { // System.out.println(rs.getLong("numero_cliente")+", //
	 * "+rs.getLong("numero_pedido")); Imprime el resultado de la consulta
	 * System.out.println("\t" + rs.getString("producto") + "  " +
	 * rs.getString("fecha") + "  " + rs.getString("tipo") + "  " +
	 * rs.getDouble("cantidad")); resultado = true; } } catch (SQLException e) {
	 * System.out.println("Error obteniendo datos"); e.printStackTrace(); } return
	 * resultado; }
	 * 
	 * public static boolean Cant_articulos_por_tipo() { boolean resultado = false;
	 * Statement smt; String sentencia =
	 * "SELECT SUM(cantidad) AS cantidad_suma, tipo FROM inventario GROUP BY tipo";
	 * try { smt = conn.createStatement(); ResultSet rs =
	 * smt.executeQuery(sentencia);
	 * System.out.println("\nCantidad de artículos por tipo:\n"); while (rs.next())
	 * { // System.out.println(rs.getLong("numero_cliente")+", //
	 * "+rs.getLong("numero_pedido")); Imprime el resultado de la consulta
	 * System.out.println("\t" + rs.getString("tipo") + " = " +
	 * rs.getString("cantidad_suma") + " unidades"); resultado = true; } } catch
	 * (SQLException e) { System.out.println("Error obteniendo datos");
	 * e.printStackTrace(); } return resultado; }
	 * 
	 * public static boolean Calc_inventario_total() { boolean resultado = false;
	 * Statement smt; String sentencia =
	 * "SELECT SUM(cantidad * precio) AS inventario_total FROM inventario"; try {
	 * smt = conn.createStatement(); ResultSet rs = smt.executeQuery(sentencia);
	 * while (rs.next()) { // System.out.println(rs.getLong("numero_cliente")+", //
	 * "+rs.getLong("numero_pedido")); Imprime el resultado de la consulta
	 * System.out.println("\nInventario total: " +
	 * rs.getString("Inventario_total")); resultado = true; } } catch (SQLException
	 * e) { System.out.println("Error obteniendo datos"); e.printStackTrace(); }
	 * return resultado; }
	 */

}
