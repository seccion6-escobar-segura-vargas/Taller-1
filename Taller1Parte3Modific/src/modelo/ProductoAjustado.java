package modelo;

import java.util.List;

public class ProductoAjustado implements Producto{
	
	private ProductoMenu base;
	private List<Ingrediente> agregados;
	private List<Ingrediente> eliminados;
	
	
	public ProductoAjustado(ProductoMenu base, List<Ingrediente> agregados, List<Ingrediente> eliminados) 
	{
		this.base = base;
		this.agregados = agregados;
		this.eliminados = eliminados;
	}
	
	public ProductoMenu getProductoBase()
	{
		return this.base;
	}
	
	public List<Ingrediente> getAgregados()
	{
		return this.agregados;
		
	}
	
	public List<Ingrediente> getEliminados()
	{
		return this.eliminados;
		
	}
	
	
	@Override
	public int getPrecio() 
	{
		int precio = getProductoBase().getPrecio();
		for (Ingrediente ing: getAgregados())
		{
			precio = precio + ing.getCostoAdicional();
		}
		
		return precio;
	}
	
	
	@Override
	public String getNombre() 
	{
		String nombre = this.base.getNombre();
		for (Ingrediente ing: getAgregados())
		{
			
			nombre = nombre + " + " +ing.getNombre();
		}
		for (Ingrediente ing: getEliminados())
		{
			nombre = nombre + " - " +ing.getNombre();
		}
		
		return nombre;
	}
	
	
	
	@Override
	public String generarTextoFactura() 
	{
		double precio = getPrecio();
		Double Dprecio = precio;
		String Sprecio = Dprecio.toString();
		Double Diva = precio*0.19;
		String Siva = Diva.toString();
		Double Dtotal = precio*1.19;
		String Stotal = Dtotal.toString();
		
		String Sdesc = "0";
		
		
		Integer Icalorias = getCalorias();
		String Scalorias = Icalorias.toString();
		
		String texto = getNombre()+","+Sprecio+","+Siva+","+Sdesc+","+Stotal+","+Scalorias;
		
		
		
		/*
		 * producto.generarTextoFactura():
		 * (nombre,valor,IVA,descuento,valor total)
		 */
		
		return texto;
	}

	
	
	@Override
	
	public int getCalorias() 
	{
		int calorias = getProductoBase().getCalorias();
		for (Ingrediente ing: getAgregados())
		{
			calorias = calorias + ing.getCalorias();
		}
		for (Ingrediente ing: getEliminados())
		{
			calorias = calorias - ing.getCalorias();
		}
		
		return calorias;
	}

}
