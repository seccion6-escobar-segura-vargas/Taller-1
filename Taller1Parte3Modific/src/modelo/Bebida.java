package modelo;

public class Bebida implements Producto
{	private String nombre;
	private int precio;
	private int calorias;
	
	public Bebida(String nombre, int precioBase, int calorias) 
	{
		this.nombre = nombre;
		this.precio = precioBase;
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
		
		return this.precio;
	}

	
	
	@Override
	public String getNombre() 
	{
		// TODO Auto-generated method stub
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
