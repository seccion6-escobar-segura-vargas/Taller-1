package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import modelo.Combo;
import modelo.Ingrediente;
import modelo.ProductoMenu;
import modelo.Pedido;
import modelo.Bebida;

public class Restaurante {
	
	private Map<String, Pedido> pedidos;
	private Pedido pedidoEnCurso;
	
	
	// Una lista con todos productos del menu
	
		private List<ProductoMenu> productosMenu;
		
		// Una lista con todos los combos. 
		 
		private List<Combo> combos;
		
		// Una lista con todos los ingredientes.
		 
		private List<Ingrediente> ingredientes;
		
		
		// Una lista con todos los bebidas.
		 
		private List<Bebida> bebidas;
	
	
	
	
	public Restaurante(Map<String, ProductoMenu> productosMenu, Map<String, Combo> combos,
			Map<String, Ingrediente> ingredientes, Map<String, Bebida> bebidas)
	{
		
		this.productosMenu = new ArrayList<ProductoMenu>(productosMenu.values());
		this.combos = new ArrayList<Combo>(combos.values());
		this.ingredientes = new ArrayList<Ingrediente>(ingredientes.values());
		this.pedidoEnCurso = null;
		this.pedidos = new HashMap<String, Pedido>();
		this.bebidas = new ArrayList<Bebida>(bebidas.values());
		
	}
	
	public String AgregarPedido(String nombreCliente, String direccionCliente)
	{
		Pedido pedido = new Pedido(nombreCliente, direccionCliente);
		String idDelPedido = pedido.getId().toString();
		pedidos.put(idDelPedido, pedido);
		setPedidoencurso(pedido);
		
		return idDelPedido;
		
	}

	public List<ProductoMenu> getProductosMenu()
	{
		return this.productosMenu;
	}

	public List<Combo> getCombos() 
	{
		return this.combos;
	}

	public List<Ingrediente> getIngredientes() 
	{
		return this.ingredientes;
	}
	
	public List<Bebida> getBebidas() 
	{
		return this.bebidas;
	}
	
	public void setPedidoencurso(Pedido pedido)
	{
		this.pedidoEnCurso = pedido;
	}
	
	public Pedido getPedidoencurso()
	{
		return this.pedidoEnCurso;
	}
	
	public Map<String, Pedido> getPedidos()
	{
		return this.pedidos;
	}

	public void AgregarProductoaPedido(Producto producto)
	{
		this.pedidoEnCurso.agregarItem(producto);
	}
	
	public Pedido consultarPedido(Integer id)
	{
		Pedido resultado = getPedidos().get(id.toString());
		
		return resultado;
	}

	public int buscarIguales(Pedido pedidoEnCurso) 
	{
		int contador = -1;
		for (Pedido pedido: pedidos.values())
		{
			if (pedido.equals(pedidoEnCurso))
			{
				contador = contador +1;
			}
		}
		
		
		
		return contador; 
	}
	
	
	
	
}
