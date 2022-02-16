package procesamiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Bebida;
import modelo.Combo;
import modelo.Ingrediente;
import modelo.ProductoMenu;

import modelo.Restaurante;


public class CargadorDatos { 
	
	
	public static Restaurante cargarArchivos() throws FileNotFoundException, IOException
	{
		
		String nombreArchivo1 = "data/menu.txt";
		String nombreArchivo4 = "data/bebidas.txt";
		String nombreArchivo2 = "data/combos.txt";
		String nombreArchivo3 = "data/ingredientes.txt";
		
		Map<String, ProductoMenu> menu = new HashMap<String, ProductoMenu>();
		Map<String, Combo> combos = new HashMap<String, Combo>();
		Map<String, Ingrediente> ingredientes = new HashMap<String, Ingrediente>();
		
		Map<String, Bebida> bebidas = new HashMap<String, Bebida>();
		
	
		
		
		
		BufferedReader br1 = new BufferedReader(new FileReader(nombreArchivo1));
		
		String linea1 = br1.readLine();
		while (linea1 != null) 
		{
			
			String[] partes = linea1.split(";");
			String nombreProducto = partes[0];
			int valorProducto = Integer.parseInt(partes[1]);
			int caloriasProducto = Integer.parseInt(partes[2]);
			
			ProductoMenu producto = new ProductoMenu(nombreProducto, valorProducto, caloriasProducto);
			
			menu.put(nombreProducto, producto);
			

			linea1 = br1.readLine();
		}
//////////////////////////////////////////////////////////////////////////////////////
//cambio de archivo
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////	
		
BufferedReader br4 = new BufferedReader(new FileReader(nombreArchivo4));
		
		String linea4 = br4.readLine();
		while (linea4 != null) 
		{
			
			String[] partes = linea4.split(";");
			String nombreProducto = partes[0];
			int valorProducto = Integer.parseInt(partes[1]);
			int caloriasProducto = Integer.parseInt(partes[2]);
			
			Bebida bebida = new Bebida(nombreProducto, valorProducto, caloriasProducto);
			
			bebidas.put(nombreProducto, bebida);
			

			linea4 = br4.readLine();
		}
//////////////////////////////////////////////////////////////////////////////////////
		//cambio de archivo
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
		
		
		BufferedReader br2 = new BufferedReader(new FileReader(nombreArchivo2));
		
		String linea2 = br2.readLine(); 
		while (linea2 != null) 
		{
			
			
			String[] partes = linea2.split(";");
			String nombreCombo = partes[0];
			double descuento = Integer.parseInt(partes[1].replace("%", ""));
			descuento = descuento/100;
			
			// hay que pasar descuento a DOUBLE, por ahora es un int
			// creemos que ahi ya quedo double
			
			
			String nombreitem1 = partes[2];
			ProductoMenu item1 = menu.get(nombreitem1);
			
			
			String nombreitem2 = partes[3];
			ProductoMenu item2 = menu.get(nombreitem2);
			
			String nombrebebida = partes[4];
			Bebida bebida = bebidas.get(nombrebebida);
			
			
			
			
			
			ProductoMenu[] itemsCombo = new ProductoMenu[] {item1, item2};
			
			
			
			Combo combo = new Combo(descuento,nombreCombo, itemsCombo, bebida);
			
			combos.put(nombreCombo, combo);
			

			linea2 = br2.readLine(); 
		}

		
		

		
		
//////////////////////////////////////////////////////////////////////////////////////
//cambio de archivo
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////



		
		BufferedReader br3 = new BufferedReader(new FileReader(nombreArchivo3));

		String linea3 = br3.readLine(); 

		while (linea3 != null) 
		{

			
			
			String[] partes = linea3.split(";");
			String nombreIngrediente = partes[0];
			int costoIngrediente = Integer.parseInt(partes[1]);
			int caloriasIngrediente = Integer.parseInt(partes[2]);
			
			Ingrediente ingrediente = new Ingrediente(nombreIngrediente, costoIngrediente, caloriasIngrediente);
			
			ingredientes.put(nombreIngrediente, ingrediente);
			

			linea3 = br3.readLine(); 
			}
			
		
		
			br1.close();
			br2.close();
			br3.close();
			br4.close();
			
			
			

		Restaurante restaurante = new Restaurante(menu, combos, ingredientes, bebidas);
		return restaurante;
	}
    
	
	
}


	
	
	

