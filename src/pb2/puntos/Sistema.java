package pb2.puntos;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Sistema {
	protected ArrayList<Producto> listaDeProductos;
	protected ArrayList<Ventas> listaDeVentas;
	protected LinkedList<Usuario> listaDeUsuarios;
	public Sistema() {
		this.listaDeProductos=new ArrayList<>();
		this.listaDeVentas=new ArrayList<>();
		this.listaDeUsuarios=new LinkedList<>();
	}
	public abstract Boolean loginUsuario(String email, String contrasenia);
	public abstract Boolean registrarUsuario(Usuario usuario);
	public abstract Boolean comprarProducto();
	public abstract Boolean pagarProducto();
	
}
