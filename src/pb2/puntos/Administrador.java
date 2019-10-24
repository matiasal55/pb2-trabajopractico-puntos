package pb2.puntos;

import java.util.Iterator;

public class Administrador extends Usuario {

	public Administrador(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido, mail, contrasenia);
		this.factorDePuntos = 120;
	}

	public Boolean agregarProducto(Producto producto) {
		for (Producto lista : this.listaDeProductos) {
			if (lista.equals(producto))
				return false;
		}
		this.listaDeProductos.add(producto);
		return true;
	}

	public Boolean eliminarUsuario(Integer id) {
		Iterator<Usuario> it = this.listaDeUsuarios.iterator();
		while (it.hasNext()) {
			Usuario aux = it.next();
			if (aux.getId().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public Boolean anularCompra(Integer orden) {
		return true;
	}

	public Boolean eliminarUsuario(String mail) {
		Iterator<Usuario> it = this.listaDeUsuarios.iterator();
		while (it.hasNext()) {
			Usuario aux = it.next();
			if (aux.getMail().equals(mail)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	// public void modificarFactorDePuntos(Integer nuevoFactor, String tipo) {
	// LinkedList<Usuario> lista=this.listaDeUsuarios;
	// for(Usuario modificar: lista) {
	// if(modificar.getClass().getSimpleName().equals(tipo))
	// modificar.setFactorDePuntos(nuevoFactor);
	// }
	//
	// }
}
