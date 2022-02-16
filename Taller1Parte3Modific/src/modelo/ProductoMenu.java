package modelo;

public class ProductoMenu implements Producto{
	
	private String nombre;
	private int precioBase;
	private int calorias;
	
	
	
	public ProductoMenu(String nombre, int precioBase, int calorias) 
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}
	
	
	@Override
	public int getCalorias() 
	{
		
		return this.calorias;
	}
	
	@Override
	public int getPrecio() 
	{
		
		return this.precioBase;
	}
	
	
	@Override
	public String getNombre() 
	{

		return this.nombre;
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
	

}
