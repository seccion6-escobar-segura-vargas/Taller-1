package modelo;

public interface Producto {
	
	abstract public int getPrecio();
	abstract public String getNombre();
	abstract public String generarTextoFactura();
	abstract public int getCalorias();

}
