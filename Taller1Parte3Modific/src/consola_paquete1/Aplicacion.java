package consola_paquete1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


import modelo.Restaurante;
import modelo.ProductoMenu;
import modelo.Ingrediente;
import modelo.Bebida;
import modelo.Combo;
import modelo.ProductoAjustado;
import modelo.Producto;
import modelo.Pedido;

import procesamiento.CargadorDatos;


public class Aplicacion 
{
	
	private Restaurante restaurante;

	
	public void ejecutarAplicacion()
	{
		
			System.out.println("Bienvenido al Corral de los Andes");

			boolean continuar = true;
			ejecutarCargar();
			while (continuar)
			{	
				try
				{	
					mostrarMenu();
					int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
					
					if (opcion_seleccionada == 1)
						ejecutarAgregarPedido();
					else if (opcion_seleccionada == 2 && restaurante != null)
						ejecutarAgregarProductoaPedido();
					else if (opcion_seleccionada == 3 && restaurante != null)
						ejecutarCerrarPedidoYGuardarFactura();
					else if (opcion_seleccionada == 4 && restaurante != null)
						ejecutarConsultarPedido();
					
					else if (opcion_seleccionada == 5)
					{
						System.out.println("Saliendo de la aplicacion ...");
						continuar = false;
					}
					else if (restaurante == null)
					{
						System.out.println("Para poder ejecutar esta opcion primero debe cargar un archivo de atletas.");
					}
					else
					{
						System.out.println("Por favor seleccione una opcion valida.");
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Debe seleccionar uno de los numeros de las opciones.");
				}
			}
		
	}
	
	private void ejecutarCargar()
	{
		System.out.println("\n" + "Cargando datos, iniciando aplicacion..." + "\n");

		try
		{
			restaurante = CargadorDatos.cargarArchivos();
			System.out.println("Datos cargados correctamente.");
			/*
			Collection<String> eventos = calculadora.darNombresDeportes();
			System.out.println("Los deportes para los que se tiene informaciÃ³n son:");
			for (String dep : eventos)
			{
				System.out.println(" - " + dep);
			}*/
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: los archivos  no se han encontrado.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo los archivos.");
			System.out.println(e.getMessage());
		}

	}
	
	private void ejecutarAgregarPedido() 
	{
		System.out.println("\n Escogió la opción de Iniciar pedido \n");
		
		String nombreCliente = input("Por favor escriba su nombre");
		
		String direccionCliente = input("Por favor escriba su direccion");
		
		String idPedido = restaurante.AgregarPedido(nombreCliente, direccionCliente);
		
		System.out.println("\nSe ha iniciado el pedido con ID "+idPedido+".");
		
		
		
	}
	
	private void ejecutarAgregarProductoaPedido() 
	{	if (!(restaurante.getPedidoencurso() == null))
		{
			System.out.println("\n Escogió la opción de agregar elemento a pedido \n");
			
			System.out.println("\n Deseas agregar");
			System.out.println("\n 1. Un producto basico del menu");
			System.out.println("\n 2. Un combo");
			System.out.println("\n 3. Una bebida");
			
			int opcion_seleccionada = Integer.parseInt(input("\n Por favor seleccione una opcion"));
			
			if (opcion_seleccionada == 1)
				
			{
				
				List<ProductoMenu> productos = restaurante.getProductosMenu();
				List<Ingrediente> ingredientes = restaurante.getIngredientes();
				
				List<Ingrediente> ingredientes_eliminados = new ArrayList<Ingrediente>();
				List<Ingrediente> ingredientes_agregados = new ArrayList<Ingrediente>();
				
				
				
				for (int i = 0; i < productos.size(); i = i + 1)
				{
					Integer pos = i + 1;
					String nombreProducto = productos.get(i).getNombre();
					System.out.println(pos.toString()+":"+nombreProducto);
				}
				
				int opcion_seleccionada2 = Integer.parseInt(input("\n Por favor seleccione un producto"));
				
				ProductoMenu productoEscogido = productos.get(opcion_seleccionada2-1);
				String nombreProducto = productoEscogido.getNombre();
				
				
				System.out.println("\n Antes de confirmar tu "+ nombreProducto
						+ " ¿deseas agregarle o eliminarle ingredientes?");
				
				for (int i = 0; i < ingredientes.size(); i = i + 1)
				{
					Integer pos = i + 1;
					String nombreIngrediente = ingredientes.get(i).getNombre();
					System.out.println(pos.toString()+":"+nombreIngrediente);
				}
				int opcion_seleccionada3 = 0;
				while (!(opcion_seleccionada3 == 3))
				{
				System.out.println(" ");
				System.out.println("1. Agregar");
				System.out.println("2. Eliminar");
				System.out.println("3. Confirmar " + nombreProducto);
				opcion_seleccionada3 = Integer.parseInt(input(""));
				
				
				if (opcion_seleccionada3 == 1)
				{
					int posIngredienteAgregar = Integer.parseInt(input("\n Por favor seleccione el ingrediente a agregar"));
					Ingrediente ingredienteAgregar = ingredientes.get(posIngredienteAgregar-1);
					ingredientes_agregados.add(ingredienteAgregar);
					
				}
					
				else if (opcion_seleccionada3 == 2)
				{
					int posIngredienteEliminar = Integer.parseInt(input("\n Por favor seleccione el ingrediente a eliminar"));
					Ingrediente ingredienteEliminar = ingredientes.get(posIngredienteEliminar-1);
					ingredientes_eliminados.add(ingredienteEliminar);
				}
				}
				
				ProductoAjustado productoAjustado = new ProductoAjustado(productoEscogido, ingredientes_agregados, ingredientes_eliminados);
				
				restaurante.AgregarProductoaPedido(productoAjustado);
				
				
				
				System.out.println(nombreProducto+" se ha confirmado.");
			}
			
			else if (opcion_seleccionada == 2)
			{
				List<Combo> combos = restaurante.getCombos();
				
				for (int i = 0; i < combos.size(); i = i + 1)
				{
					Integer pos = i + 1;
					String nombreCombo = combos.get(i).getNombre();
					System.out.println(pos.toString()+":"+nombreCombo);
				}
				
				int opcion_seleccionada2 = Integer.parseInt(input("\n Por favor seleccione un combo"));
				
				Combo comboEscogido = combos.get(opcion_seleccionada2-1);
				String nombreCombo = comboEscogido.getNombre();
				restaurante.AgregarProductoaPedido(comboEscogido);
				
				System.out.println("Se ha confirmado tu "+nombreCombo+".");
				
			}
			else if (opcion_seleccionada == 3)
			{
				List<Bebida> bebidas = restaurante.getBebidas();
				
				for (int i = 0; i < bebidas.size(); i = i + 1)
				{
					Integer pos = i + 1;
					String nombreBebida = bebidas.get(i).getNombre();
					System.out.println(pos.toString()+":"+nombreBebida);
				}
				
				int opcion_seleccionada2 = Integer.parseInt(input("\n Por favor seleccione una bebida"));
				
				Bebida bebidaEscogido = bebidas.get(opcion_seleccionada2-1);
				String nombreBebida = bebidaEscogido.getNombre();
				restaurante.AgregarProductoaPedido(bebidaEscogido);
				
				System.out.println("Se ha confirmado tu "+nombreBebida+".");
				
			}
				
		
		}
	
	else 
	{
		System.out.println("\nInicie primero un pedido por la opcion 1.");
	}
				
	
	}
	
	private void ejecutarCerrarPedidoYGuardarFactura() 
	{
		Pedido pedidoEnCurso = restaurante.getPedidoencurso();
		if (!(pedidoEnCurso == null))
		{
			pedidoEnCurso.generarFactura();
			
			
			Integer iguales = restaurante.buscarIguales(pedidoEnCurso);
			
			String idPedido = pedidoEnCurso.getId().toString();
			System.out.println("\nSe ha generado la factura del pedido "+idPedido+".");
			restaurante.setPedidoencurso(null);
			System.out.println("\nSe ha cerrado el pedido "+idPedido+".");
			
			System.out.println("\nHay "+iguales+" pedidos iguales.");
		}
		else
		{
			System.out.println("\nInicie primero un pedido por la opcion 1.");
		}
	}
	
	private void ejecutarConsultarPedido() 
	{
		System.out.println("\nHas escogido la opcion de consultar pedido.");
		
		Integer idRevisar = Integer.parseInt(input("\n Por favor ingrese el ID del pedido a consultar"));
		
		Pedido pedido = restaurante.consultarPedido(idRevisar);
		
		if (pedido == null)
		{
			System.out.println("No existen pedidos con este ID.");
		}
		else 
		{
			System.out.println("La información del pedido "+ idRevisar.toString() +"es:");
			System.out.println(pedido.generarFacturaConsola());
		}
		
	}
	
	
	public void mostrarMenu()
	{
		System.out.println("\n Opciones de la aplicacion");
		System.out.println("1. Iniciar pedido");
		System.out.println("2. Agregar un elemento a un pedido");
		System.out.println("3. Cerrar un pedido y guardar la factura");
		System.out.println("4. Consultar informacion de un pedido dado su ID");
		
		System.out.println("5. Salir de la aplicacion");
	}
	
	
	public static void main(String[] args) 
	{
		
		
		
		Aplicacion app = new Aplicacion();
		app.ejecutarAplicacion();
		/*  pruebas
		System.out.println(Integer.parseInt("10".replace("1", "8")));
		int a = 1;
		int b = 2;
		int c = 3;
		int[] arreglo = new int[] {};
		
		System.out.println();
		*/
		
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
}