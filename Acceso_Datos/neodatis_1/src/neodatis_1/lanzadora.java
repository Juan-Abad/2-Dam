package neodatis_1;

import java.math.BigInteger;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import utilidades.Leer;

public class lanzadora {
	private static String fileName = "C:/Users/usuario/Desktop/2ºDam/Acceso_Datos/neodatis_1/tutorial.odb";

	public static void main(String[] args) {
		String[] vOpciones = {"Salir", "Añadir objetos", "Listar objetos", "Seleccionar objeto", "Eliminar objeto",
				"Modificar objeto" };
		int opcion = 0;
		do {
			opcion = Leer.pedirEntero(vOpciones);
			gestionMenu(opcion);
		}while(opcion != 0);
	}

	public static void gestionMenu(int opcion) {
		switch (opcion) {
		case 1:
			addObjects();
			break;
		case 2:
			listar_Objects();
			break;
		case 3:
			select_Object();
			break;
		case 4:
			delete_Objects();
			break;
		case 5:
			modify_Objects();
			break;
		}
	}

	public static void addObjects() {
		Sujeto s1 = new Sujeto("Maria", 23, "Madrid");
		Sujeto s2 = new Sujeto("Juan", 26, "Madrid");
		Sujeto s3 = new Sujeto("Lucia", 28, "Zaragoza");
		Sujeto s4 = new Sujeto("Marcos", 20, "Zaragoza");
		ODB odb = ODBFactory.open(fileName);
		odb.store(s1);
		odb.store(s2);
		odb.store(s3);
		odb.store(s4);
		odb.close();
	}

	public static void listar_Objects() {
		ODB odb2 = ODBFactory.open(fileName);
		Objects<Sujeto> objects = odb2.getObjects(Sujeto.class);
		while (objects.hasNext()) {
			Sujeto sujeto_actual2 = objects.next();
			System.out.println("\t: " + sujeto_actual2.getName() + " " + sujeto_actual2.getEdad() + " "
					+ sujeto_actual2.getCiudad());
		}
		odb2.close();
	}

	public static void select_Object() {
		ODB odb3 = ODBFactory.open(fileName);
		ICriterion criterio = Where.equal("edad", 23);
		CriteriaQuery query = new CriteriaQuery(Sujeto.class, criterio);
		Objects<Sujeto> objects3 = odb3.getObjects(query);
		while (objects3.hasNext()) {
			Sujeto sujeto_actual3 = objects3.next();
			System.out.println("\t: " + sujeto_actual3.getName() + " " + sujeto_actual3.getEdad());
		}
		odb3.close();
	}

	public static void delete_Objects() {
		ODB odb4 = ODBFactory.open(fileName);
		ICriterion criterio4 = Where.gt("edad", 1);
		CriteriaQuery query4 = new CriteriaQuery(Sujeto.class, criterio4);
		Objects<Sujeto> objects4 = odb4.getObjects(query4);
		while (objects4.hasNext()) {
			Sujeto sujeto_actual4 = objects4.next();
			System.out.println("\t: " + sujeto_actual4.getName() + " " + sujeto_actual4.getEdad() + " <--Elimiado");
			odb4.delete(sujeto_actual4);
		}
		odb4.close();
	}

	public static void modify_Objects() {
		ODB odb5 = ODBFactory.open(fileName);
		ICriterion criterio5 = Where.gt("edad", 2);
		CriteriaQuery query5 = new CriteriaQuery(Sujeto.class, criterio5);
		Objects<Sujeto> objects5 = odb5.getObjects(query5);
		while (objects5.hasNext()) {
			Sujeto sujeto_actual5 = objects5.next();
			System.out.println(
					"\t: " + sujeto_actual5.getName() + " " + sujeto_actual5.getEdad() + " <--Ciudad Cambiada");
			sujeto_actual5.setCiudad("XXXX");
			odb5.store(sujeto_actual5);
			odb5.commit();
		}
		odb5.close();
	}

	public static void agregados() {
		ODB odb6 = ODBFactory.open(fileName);
		IValuesQuery valuesQuery6 = new ValuesCriteriaQuery(Sujeto.class).count("nombre");
		Values values6 = odb6.getValues(valuesQuery6);
		ObjectValues objectValues6 = values6.nextValues();
		BigInteger count6 = (BigInteger) objectValues6.getByAlias("nombre");
		System.out.println("NÃºmero de Jugadores: " + count6.intValue());
		odb6.close();
	}

	public static void agregados_con_seleccion() {
		ODB odb7 = ODBFactory.open(fileName);
		IValuesQuery valuesQuery7 = new ValuesCriteriaQuery(Sujeto.class, Where.gt("edad", 21)).count("nombre")
				.field("ciudad").groupBy("ciudad");
		Values values7 = odb7.getValues(valuesQuery7);
		while (values7.hasNext()) {
			ObjectValues objectValues7 = (ObjectValues) values7.next();
			System.out.println(objectValues7.getByAlias("ciudad") + " ---> " + objectValues7.getByAlias("nombre"));
		}
		odb7.close();
	}

	public static void agrupados() {
		ODB odb8 = ODBFactory.open(fileName);
		IValuesQuery valuesQuery8 = new ValuesCriteriaQuery(Sujeto.class, Where.gt("edad", 1)).avg("edad")
				.field("ciudad").groupBy("ciudad");
		Values values8 = odb8.getValues(valuesQuery8);
		while (values8.hasNext()) {
			ObjectValues objectValues8 = (ObjectValues) values8.next();
			System.out.println(objectValues8.getByAlias("ciudad") + " ---> " + objectValues8.getByAlias("edad"));
		}
		odb8.close();
	}

}
