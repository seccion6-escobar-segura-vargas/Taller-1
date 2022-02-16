package modelo;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Pedido {

	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private List<Producto> itemsPedido;
	
	private static Integer contadorPedidos = 0;
	
	private static void actualizarCantidadPedidos()
	{
		contadorPedidos = contadorPedidos + 1;
	}
	
	
	
	
	public Pedido(String nombreCliente, String direccionCliente) 
	{
		
		actualizarCantidadPedidos();
		this.idPedido = contadorPedidos;
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<Producto>();
		
	}
	
	public Integer getId()
	{
		return this.idPedido;
	}
	
	public String getNombreCliente()
	{
		return this.nombreCliente;
	}
	


	public List<Producto> getItemsPedido() 
	{
		return this.itemsPedido;
	}
	
	public void agregarItem(Producto producto)
	{
		itemsPedido.add(producto);
	}
	
	public void generarFactura()
	{
		try 
		{
			String nombreArchivo = getId().toString() +"-"+ getNombreCliente();
			PrintWriter writer = new PrintWriter("data/facturas/"+nombreArchivo+".txt", "UTF-8");
			
			
			for (Producto producto: getItemsPedido())
			{
				String facturacionItem = producto.generarTextoFactura();
				
				///escritura sobre .txt
				writer.println(facturacionItem);
			}
			
            
            writer.close();
        
		} 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
		
		
	}
	
	public String generarFacturaConsola()
	{
		String facturacionPedido = "";
		for (Producto producto: getItemsPedido())
		{
			facturacionPedido = facturacionPedido + "\n-> " + producto.generarTextoFactura();
			
		}
		
		return facturacionPedido;
	}

	
	public boolean equals(Pedido pedido2)
	{
		boolean iguales = false;
		if (pedido2.getClass() == this.getClass())
		{
			
		iguales = (pedido2.llenarMapaContadores().equals(this.llenarMapaContadores()));
			
		}
		
		return iguales;
	}
	
	public Map<String, Integer> llenarMapaContadores()
	{
	Map<String, Integer> mapaContadores = new HashMap<String, Integer>();
	
	for (Producto prod: this.getItemsPedido())
	{
		String nombre = prod.getNombre();
		if(mapaContadores.containsKey(nombre))
		{
			
			Integer nuevoValor = mapaContadores.get(nombre) + 1;
			mapaContadores.put(nombre, nuevoValor);
		}
		else 
		{
			mapaContadores.put(nombre, 1);
		}
	}
	return mapaContadores;
	}
	
	
	

}
