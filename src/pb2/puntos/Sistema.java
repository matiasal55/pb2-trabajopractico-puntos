package pb2.puntos;

import java.util.ArrayList;
import java.util.LinkedList;

public class Sistema {
	protected ArrayList<Producto> listaDeProductos;
	protected ArrayList<Ventas> listaDeVentas;
	protected LinkedList<Usuario> listaDeUsuarios;

	public Sistema() {
		this.listaDeProductos = new ArrayList<>();
		this.listaDeVentas = new ArrayList<>();
		this.listaDeUsuarios = new LinkedList<>();
	}

	public Boolean loginUsuario(String mail, String contrasenia) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(mail) && lista.getContrasenia().equals(contrasenia))
				return true;
		}
		return false;
	}

	public Boolean registrarUsuario(Usuario usuario) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(usuario.getMail()))
				return false;
		}
		this.listaDeUsuarios.add(usuario);
		return true;
	}

}
