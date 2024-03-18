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

	public Values Calc_total_uds_vendidas() {
		return odb.getValues(new ValuesCriteriaQuery(Venta.class).sum("cantidad"));
	}

	public Values Calc_venta_por_color() {
		return odb.getValues(new ValuesCriteriaQuery(Venta.class).sum("cantidad").field("color").groupBy("color"));
	}

	public ArrayList<ObjectValues> Producto_con_mayor_num_ventas() {
		Values valores = odb.getValues(new ValuesCriteriaQuery(Venta.class).field("producto").groupBy("producto").sum("cantidad", "cantidad"));
		ArrayList<ObjectValues> lista = new ArrayList<ObjectValues>();
		int cantidad_maxima = 0;
		ObjectValues valor;
		while(valores.hasNext()) {
			valor = valores.next();
			if(Integer.parseInt(valor.getByAlias("cantidad").toString())>cantidad_maxima) {
				cantidad_maxima = Integer.parseInt(valor.getByAlias("cantidad").toString());
				lista.clear();
				lista.add(valor);
			}else if(Integer.parseInt(valor.getByAlias("cantidad").toString())==cantidad_maxima) {
				lista.add(valor);
			}
		}
		return lista;
	}
	
	public ArrayList<Venta> obtenerBBDD() {
        ArrayList<Venta> listaVentas = new ArrayList<>();
        // Suponiendo que odb.getObjects() devuelve una lista de objetos de tipo Venta
        listaVentas.addAll(odb.getObjects(Venta.class));
        return listaVentas;
    }
}
