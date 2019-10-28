package pb2.puntos;

import java.util.ArrayList;
import java.util.LinkedList;

public class Sistema {
	
	protected ArrayList <Producto> listaDeProductos = new ArrayList <Producto> ();
	protected LinkedList <Usuario> listaDeUsuarios = new LinkedList <Usuario> ();
	protected ArrayList <Ventas> listaDeVentas = new ArrayList <Ventas> ();
	
	public Boolean registrarUsuario (Usuario usuario) {
			for (Usuario usuarios : listaDeUsuarios) {
				if (usuarios.getMail().equals(usuarios.getMail())) {
					return false;
				}
			}
			this.listaDeUsuarios.add(usuario);
			return true;
		}
	
	public Boolean loginUsuario (String mail, String contrasenia) {
		for (Usuario usuarios : listaDeUsuarios) {
			if (usuarios.getMail().equals(mail) && usuarios.getContrasenia().equals(contrasenia)) {
				return true;
			}
		}
		return false;
	}
	
	
}
