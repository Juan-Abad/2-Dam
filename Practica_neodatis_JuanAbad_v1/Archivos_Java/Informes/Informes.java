package Informes;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import practica_neodatis.Pedido;
import practica_neodatis.PedidoArticulo;

public class Informes {

	private static ODB odb;

	public Informes(ODB odb) {
		Informes.odb = odb;
	}

	public void Num_pedidos_procesados() {
		// Contar el número de pedidos recibidos
        Long numPedidosRecibidos = odb.count(new CriteriaQuery(Pedido.class)).longValue();

        // Imprimir los resultados
        System.out.println("Número de pedidos recibidos: " + numPedidosRecibidos);
	}

	public void Num_lineas_pedido_procesadas() {
		long numLineasPedidoRecibidas = odb.count(new CriteriaQuery(PedidoArticulo.class)).longValue();

        // Imprimir el resultado
        System.out.println("Número de líneas de pedido recibidas: " + numLineasPedidoRecibidas);
	}

	public void List_articulos_unicos() {
		Objects<PedidoArticulo> detallePedidos = odb.getObjects(PedidoArticulo.class);

        // Crear un mapa para almacenar los artículos y la cantidad de veces que aparecen en los pedidos
        Map<Long, Integer> articulos = new HashMap<>();

        // Iterar sobre los DetallePedido y contar la cantidad de veces que aparece cada artículo
        while (detallePedidos.hasNext()) {
        	PedidoArticulo detallePedido = detallePedidos.next();
            Long codigoArticulo = detallePedido.getCodigo_articulo();

            // Si el artículo ya existe en el mapa, incrementar su contador; de lo contrario, agregarlo al mapa con un contador inicial de 1
            if (articulos.containsKey(codigoArticulo)) {
                int cantidad = articulos.get(codigoArticulo);
                articulos.put(codigoArticulo, cantidad + 1);
            } else {
                articulos.put(codigoArticulo, 1);
            }
        }

        // Imprimir el listado de artículos diferentes recibidos y la cantidad de veces que aparecen en los pedidos
        System.out.println("Listado de artículos diferentes recibidos:");
        for (Map.Entry<Long, Integer> entry : articulos.entrySet()) {
        	Long codigoArticulo = entry.getKey();
            int cantidadPedidos = entry.getValue();
            System.out.println("Artículo: " + codigoArticulo + ", Pedidos: " + cantidadPedidos);
        }
	}

	public void List_clientes_con_pedidos() {
		Objects<Pedido> pedidos = odb.getObjects(Pedido.class);

        // Crear un mapa para almacenar los clientes y la cantidad de pedidos que han enviado
        Map<Long, Integer> clientes = new HashMap<>();

        // Iterar sobre los pedidos y contar la cantidad de pedidos enviados por cada cliente
        while (pedidos.hasNext()) {
            Pedido pedido = pedidos.next();
            Long numeroCliente = pedido.getNumero_cliente();

            // Si el cliente ya existe en el mapa, incrementar su contador; de lo contrario, agregarlo al mapa con un contador inicial de 1
            if (clientes.containsKey(numeroCliente)) {
                int cantidadPedidos = clientes.get(numeroCliente);
                clientes.put(numeroCliente, cantidadPedidos + 1);
            } else {
                clientes.put(numeroCliente, 1);
            }
        }

        // Imprimir el listado de clientes que han enviado pedidos y la cantidad de pedidos enviados por cada cliente
        System.out.println("Listado de clientes que han enviado pedidos:");
        for (Map.Entry<Long, Integer> entry : clientes.entrySet()) {
        	Long numeroCliente = entry.getKey();
            int cantidadPedidos = entry.getValue();
            System.out.println("Cliente: " + numeroCliente + ", Pedidos: " + cantidadPedidos);
        }
	}

	public void List_cantidad_pedida_de_cada_articulo() {
		// Obtener todos los objetos de tipo DetallePedido
        Objects<PedidoArticulo> detallePedidos = odb.getObjects(PedidoArticulo.class);

        // Crear un mapa para almacenar las cantidades sumadas de cada artículo
        Map<Long, Integer> cantidadesPorArticulo = new HashMap<>();

        // Iterar sobre los DetallePedido y sumar las cantidades por cada artículo
        while (detallePedidos.hasNext()) {
        	PedidoArticulo detallePedido = detallePedidos.next();
            Long codigoArticulo = detallePedido.getCodigo_articulo();
            int cantidad = detallePedido.getCantidad();

            // Si el artículo ya existe en el mapa, sumar la cantidad; de lo contrario, agregarlo al mapa con la cantidad inicial
            if (cantidadesPorArticulo.containsKey(codigoArticulo)) {
                int cantidadExistente = cantidadesPorArticulo.get(codigoArticulo);
                cantidadesPorArticulo.put(codigoArticulo, cantidadExistente + cantidad);
            } else {
                cantidadesPorArticulo.put(codigoArticulo, cantidad);
            }
        }

        // Imprimir el listado de artículos con las cantidades sumadas de todos los pedidos
        System.out.println("Listado de artículos con las cantidades sumadas de todos los pedidos:");
        for (Map.Entry<Long, Integer> entry : cantidadesPorArticulo.entrySet()) {
        	Long codigoArticulo = entry.getKey();
            int cantidadTotal = entry.getValue();
            System.out.println("Artículo: " + codigoArticulo + ", Cantidad Total: " + cantidadTotal);
        }
	}

	public void List_unidades_pedidas_por_pedido() {
		// Obtener todos los objetos de tipo pedido_articulo
        Objects<PedidoArticulo> pedidoArticulos = odb.getObjects(PedidoArticulo.class);

        // Crear un mapa para almacenar las unidades pedidas por pedido
        Map<Long, Long> unidadesPorPedido = new HashMap<>();

        // Calcular las unidades pedidas por pedido
        while (pedidoArticulos.hasNext()) {
            PedidoArticulo pedidoArticulo = pedidoArticulos.next();
            Long numeroPedido = pedidoArticulo.getNumero_pedido();
            long cantidad = pedidoArticulo.getCantidad();

            // Si el pedido ya existe en el mapa, sumar la cantidad, de lo contrario, agregarlo al mapa
            if (unidadesPorPedido.containsKey(numeroPedido)) {
                long unidadesAnteriores = unidadesPorPedido.get(numeroPedido);
                unidadesPorPedido.put(numeroPedido, unidadesAnteriores + cantidad);
            } else {
                unidadesPorPedido.put(numeroPedido, cantidad);
            }
        }

        // Imprimir el listado de unidades pedidas por pedido
        System.out.println("\nListado de unidades pedidas por pedido:");
        int i = 1;
        for (Map.Entry<Long, Long> entry : unidadesPorPedido.entrySet()) {
            Long numeroPedido = entry.getKey();
            long unidadesPedidas = entry.getValue();
            System.out.println(i++ + "\tPedido: " + numeroPedido + ", Unidades Pedidas: " + unidadesPedidas);
        }

	}

	public void Media_articulos_por_pedido() {
		// Contar el número total de pedidos
        long numPedidos = odb.count(new CriteriaQuery(Pedido.class)).longValue();

        // Contar el número total de artículos en todos los pedidos
        long numArticulos = odb.count(new CriteriaQuery(PedidoArticulo.class)).longValue();

        // Calcular la media de artículos por pedido
        double mediaArticulosPorPedido = (double) numArticulos / numPedidos;
        
        // Aplicar formato al double, para qu tenga solo 4 decimales
        DecimalFormat formato = new DecimalFormat("#.##");
        
        // Imprimir el resultado
        System.out.println("Media de artículos por pedido recibidos: " + formato.format(mediaArticulosPorPedido));
	}
	
	public void Listado_pedidos() {
		Objects<Pedido> pedidos = odb.getObjects(Pedido.class);

        // Iterar sobre los pedidos
        while (pedidos.hasNext()) {
            Pedido pedido = pedidos.next();
            System.out.println("Nº Pedido\tCliente\t\tFecha");
            System.out.println(pedido.getNumero_pedido() + "\t\t" + pedido.getNumero_cliente() + "\t" + pedido.getFecha());

            // Obtener todos los objetos de tipo DetallePedido para este pedido
            Objects<PedidoArticulo> pedido_articulo = odb.getObjects(PedidoArticulo.class);

            // Iterar sobre los detalles del pedido
            System.out.println("\n\t\t\t\t\t\tArticulo  Cantidad");
            while (pedido_articulo.hasNext()) {
                PedidoArticulo pedidoArticulo = pedido_articulo.next();
                System.out.println("\t\t\t\t\t\t"+pedidoArticulo.getCodigo_articulo() + "\t\t" + pedidoArticulo.getCantidad());
            }
            System.out.println(); // Línea en blanco entre pedidos
	}
	}
}
