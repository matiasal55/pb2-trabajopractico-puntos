package pb2.puntos;

import java.util.Iterator;
import java.util.LinkedList;

public class Administrador extends Usuario {
	LinkedList<Producto> productos = new LinkedList();
	LinkedList<Usuario> usuarios = new LinkedList();

	public boolean registrar(Usuario usuario) {
		for (Usuario users : usuarios) {
			if (users.getMail().equals(usuario.getMail())) {
				return false;
			}
		}
		usuarios.add(usuario);
		return true;
	}

	public void agregarProducto(Producto producto) {
		productos.add(producto);
	}

	public boolean eliminarUsuario(Usuario usuario) {
		Iterator<Usuario> users = usuarios.iterator();
		while (users.hasNext()) {
			Usuario aux = users.next();
			if (aux.equals(usuario)) {
				usuarios.remove(usuario);
				return true;
			}
		}
		return false;
	}

	public Boolean login(String email, String contrasenia) {
		Iterator<Usuario> users = usuarios.iterator();
		while (users.hasNext()) {
			Usuario aux = users.next();
			if (aux.getMail().equals(email) && aux.getContrasenia().equals(contrasenia)) {
				return true;
			}
		}
		return false;
	}
	
}
