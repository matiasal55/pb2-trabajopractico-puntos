package pb2.puntos;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Sistema {
	private Set<Producto> listaDeProductos;
	private LinkedList<Usuario> listaDeUsuarios;
	private ArrayList<Ventas> listaDeVentas;

	public Sistema() {
		this.listaDeProductos = new TreeSet<>(); // treeset para que la lista de productos se ordene por codigo
		this.listaDeVentas = new ArrayList<>();
		this.listaDeUsuarios = new LinkedList<>();
	}

	public Boolean registrarUsuario(Usuario usuario) throws UsuarioYaRegistrado {
		Iterator<Usuario> listaAux = listaDeUsuarios.iterator();
		while (listaAux.hasNext()) {
			Usuario usrAux = listaAux.next();
			if (usrAux.getEmail().equals(usuario.getEmail())) { // verifica que no sea el mismo usuario comparando el
																// mail
				throw new UsuarioYaRegistrado();
			}
		}
		listaDeUsuarios.add(usuario);
		return true;
	}

	public Boolean loginUsuario(String email, String contrasenia) throws LoginFallidoException {
		for (Usuario listaAux : listaDeUsuarios) {
			if (listaAux.getEmail().equals(email) && listaAux.getContrasenia().equals(contrasenia)) { // verifica que el mail y la contraseñaesten bien ingresados
				return true;
			}
		}
		throw new LoginFallidoException();
	}

	public Boolean agregarProducto(Usuario usuario, Producto producto)
			throws ProductoRepetidoException, NoEsAdministradorException {
		if (usuario instanceof Administrador) { // solo el administrador puede agregar productos
			for (Producto p : listaDeProductos) {
				if (producto.getCodigo().equals(p.getCodigo())) { // verifica que no se ingresen productos repetidos
					throw new ProductoRepetidoException();
				}
				listaDeProductos.add(producto); // si no está repetido lo agrega
				return true;
			}
		}
		throw new NoEsAdministradorException();
	}

	public Boolean eliminarProducto(Usuario usuario, Producto producto)
			throws ProductoNoExisteException, NoEsAdministradorException {
		if (usuario instanceof Administrador) { // solo los administradores pueden eliminar productos
			Iterator<Producto> listaAux = listaDeProductos.iterator();
			while (listaAux.hasNext()) {
				Producto prodAux = listaAux.next();
				if (prodAux.equals(producto)) { // verifica que el producto ingresado este en la lista de productos
					listaDeProductos.remove(producto); // si está lo remueve
					return true;
				}
				throw new ProductoNoExisteException();
			}
		}
		throw new NoEsAdministradorException();
	}

	public Boolean modificarProducto(Usuario usuario, Producto pAModificar, Producto pNuevo)
			throws ProductoNoExisteException, NoEsAdministradorException {
		if (usuario instanceof Administrador) { // solo el administrador puede modificar productos
			for (Producto p : listaDeProductos) {
				if (p.equals(pAModificar)) { // verifica que el producto a modificar este en la lista
					pAModificar.setDescripcion(pNuevo.getDescripcion());
					pAModificar.setCodigo(pNuevo.getCodigo());
					pAModificar.setNombreProducto(pNuevo.getNombreProducto());
					pAModificar.setPrecioPuntos(pNuevo.getPrecioPuntos());
					pAModificar.setPrecioReal(pNuevo.getPrecioReal());
					return true;
				}
				throw new ProductoNoExisteException();
			}
		}
		throw new NoEsAdministradorException();
	}

	public DetalleDePago realizarCompra(Ventas venta) throws VentaFallidaException {
		Iterator<Producto> listaAux = listaDeProductos.iterator();
		while (listaAux.hasNext()) {
			Producto prodAux = listaAux.next();
			if (prodAux.equals(venta.getProducto())) { // verifica que el producto ingresado este en la lista de
														// productos
				listaDeProductos.remove(venta.getProducto()); // si está lo remueve
				listaDeVentas.add(venta); // y agrega una venta
				return venta.getDetalle(); // devuelve el detalle de pago
			}
		}
		throw new VentaFallidaException();
	}
	
	

}
