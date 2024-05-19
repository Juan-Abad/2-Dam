import java.util.ArrayList;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class Consultas {
	private static ODB odb;

	public Consultas(ODB odb) {
		Consultas.odb = odb;
	}

	public void Articulo_con_mayor_num_unidades() {
		Values valores = odb.getValues(new ValuesCriteriaQuery(Articulo.class).field("producto").field("cantidad")
				.field("tipo").field("fecha").field("precio"));
		ArrayList<Articulo> lista = new ArrayList<Articulo>();
		int cantidad_maxima = 0;
		ObjectValues valor;
		while (valores.hasNext()) {
			valor = valores.next();
			if (Integer.parseInt(valor.getByAlias("cantidad").toString()) > cantidad_maxima) {
				cantidad_maxima = Integer.parseInt(valor.getByAlias("cantidad").toString());
				lista.clear();
				lista.add(new Articulo(valor.getByAlias("fecha").toString(), valor.getByAlias("producto").toString(),
						valor.getByAlias("tipo").toString(), Double.valueOf(valor.getByAlias("precio").toString()),
						Integer.parseInt(valor.getByAlias("cantidad").toString())));
			} else if (Integer.parseInt(valor.getByAlias("cantidad").toString()) == cantidad_maxima) {
				lista.add(new Articulo(valor.getByAlias("fecha").toString(), valor.getByAlias("producto").toString(),
						valor.getByAlias("tipo").toString(), Double.valueOf(valor.getByAlias("precio").toString()),
						Integer.parseInt(valor.getByAlias("cantidad").toString())));
			}
		}
		System.out.println("\nArticulos con mayor cantidad de unidades:");
		for (Articulo obj : lista) {
			System.out.println("\t" + obj.toString());
		}
	}
	
	public Values Cant_articulos_por_tipo() {
		return odb.getValues(new ValuesCriteriaQuery(Articulo.class).sum("cantidad").field("tipo").groupBy("tipo"));
	}
	
	public void Calc_inventario_total() {
		Values valores = odb.getValues(new ValuesCriteriaQuery(Articulo.class).field("producto").field("cantidad")
				.field("tipo").field("fecha").field("precio"));
		Double producto_actual, inventario_total = 0.0;
		for (ObjectValues valor : valores) {
			producto_actual = Double.valueOf(valor.getByAlias("precio").toString())
					* Integer.parseInt(valor.getByAlias("cantidad").toString());
			inventario_total += producto_actual;
		}
		System.out.println("\nEl valor del inventario total es de: "+inventario_total);
	}

	public ArrayList<Articulo> obtenerBBDD() {
		ArrayList<Articulo> listaArticulos = new ArrayList<>();
		// Suponiendo que odb.getObjects() devuelve una lista de objetos de tipo Venta
		listaArticulos.addAll(odb.getObjects(Articulo.class));
		return listaArticulos;
	}
}
