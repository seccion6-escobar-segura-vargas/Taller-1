package modelo;

public class Combo implements Producto{
	private double descuento;
	private String nombreCombo;
	private Producto[] itemsCombo;
	private Bebida bebida;
	

	public Combo(double descuento, String nombreCombo, Producto[] itemsCombo, Bebida bebida) 
	{
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		this.itemsCombo = itemsCombo;
		this.bebida = bebida;
	}
	
	public double getDescuento()
	{
		return this.descuento;
	}
	
	public Producto[] getitemsCombo()
	{
		return this.itemsCombo;
	}
	
	public Bebida getBebida()
	{
		return this.bebida;
	}
	
	
	
	@Override
	public int getPrecio() 
	{
		int suma = getBebida().getPrecio();
		for (Producto prod : getitemsCombo())
		{
			
			suma = suma + prod.getPrecio();
		}
		return suma;
		
	}
	
	
	@Override
	public String getNombre() 
	{
		
		return this.nombreCombo;
	}
	
	
	@Override
	public String generarTextoFactura() 
	{
		double precio = getPrecio();
		Double Dprecio = precio;
		String Sprecio = Dprecio.toString();
		Double Diva = precio*0.19;
		String Siva = Diva.toString();
		
		
		Double Ddesc = getDescuento();
		String Sdesc = Ddesc.toString();
		
		Double Dtotal = precio*(1-Ddesc)*1.19;
		String Stotal = Dtotal.toString();
		
		
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
		int suma = getBebida().getCalorias();
		for (Producto prod : getitemsCombo())
		{
			
			suma = suma + prod.getCalorias();
		}
		return suma;
	}

}
