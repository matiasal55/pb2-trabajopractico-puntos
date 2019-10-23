package pb2.puntos;

import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.commons.codec.digest.DigestUtils;

public class Sistema {
	protected ArrayList<Producto> listaDeProductos;
	protected ArrayList<Ventas> listaDeVentas;
	protected LinkedList<Usuario> listaDeUsuarios;

	public Sistema() {
		this.listaDeProductos = new ArrayList<>();
		this.listaDeVentas = new ArrayList<>();
		this.listaDeUsuarios = new LinkedList<>();
	}

	public ArrayList<Producto> getListaDeProductos() {
		return listaDeProductos;
	}

	public void setListaDeProductos(ArrayList<Producto> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
	}

	public ArrayList<Ventas> getListaDeVentas() {
		return listaDeVentas;
	}

	public void setListaDeVentas(ArrayList<Ventas> listaDeVentas) {
		this.listaDeVentas = listaDeVentas;
	}

	public LinkedList<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(LinkedList<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}
	
	public Boolean loginUsuario(String mail, String contrasenia) {
		String encriptada=DigestUtils.sha1Hex(contrasenia);
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(mail) && lista.getContrasenia().equals(encriptada))
				return true;
		}
		return false;
	}

	public Boolean registrarUsuario(Usuario usuario) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(usuario.getMail()))
				return false;
		}
		usuario.setContrasenia(DigestUtils.sha1Hex(usuario.getContrasenia()));
		this.listaDeUsuarios.add(usuario);
		usuario.setId(this.listaDeUsuarios.size());
		return true;
	}

}
