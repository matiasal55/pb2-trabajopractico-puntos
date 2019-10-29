package pb2.puntos;

public class Administrador extends Usuario {
	private Integer factorDePuntos;

	public Administrador(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido, mail, contrasenia);
		this.factorDePuntos = 120;
	}

	public Integer getFactorDePuntos() {
		return factorDePuntos;
	}

	public void setFactorDePuntos(Integer factorDePuntos) {
		this.factorDePuntos = factorDePuntos;
	}

	public Boolean agregarProducto(Producto producto) {
		// for (Producto lista : Sistema.listaDeProductos) {
		// if (lista.equals(producto))
		// return false;
		// }
		// Sistema.listaDeProductos.add(producto);
		return true;
	}

	public Boolean eliminarUsuario(Integer id) {
		// Iterator<Usuario> it = Sistema.listaDeUsuarios.iterator();
		// while (it.hasNext()) {
		// Usuario aux = it.next();
		// if (aux.getId().equals(id)) {
		// it.remove();
		// return true;
		// }
		// }
		return false;
	}

	public Boolean anularCompra(Integer orden) {
		return true;
	}

	public Boolean eliminarUsuario(String mail) {
		// Iterator<Usuario> it = Sistema.listaDeUsuarios.iterator();
		// while (it.hasNext()) {
		// Usuario aux = it.next();
		// if (aux.getMail().equals(mail)) {
		// it.remove();
		// return true;
		// }
		// }
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
