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

	public Boolean registrarUsuario(Usuario usuario) throws UsuarioYaRegistradoException {
		Iterator<Usuario> listaAux = listaDeUsuarios.iterator();
		while (listaAux.hasNext()) {
			Usuario usrAux = listaAux.next();
			if (usrAux.getEmail().equals(usuario.getEmail())) { // verifica que no sea el mismo usuario comparando el
																// mail
				throw new UsuarioYaRegistradoException();
			}
		}
		listaDeUsuarios.add(usuario);
		return true;
	}

	public Boolean loginUsuario(String email, String contrasenia) throws LoginFallidoException {
		for (Usuario listaAux : listaDeUsuarios) {
			if (listaAux.getEmail().equals(email) && listaAux.getContrasenia().equals(contrasenia)) { // verifica que el
																										// mail y la
																										// contraseñaesten
																										// bien
																										// ingresados
				return true;
			}
		}
		throw new LoginFallidoException();
	}

	public Boolean eliminarUsuario(Usuario usuario, Usuario eliminar)
			throws UsuarioInexistenteException, NoEsAdministradorException {
		if (usuario instanceof Administrador) { // solo el administrador puede eliminar usuarios
			Iterator<Usuario> listaAux = listaDeUsuarios.iterator();
			while (listaAux.hasNext()) {
				Usuario UsAux = listaAux.next();
				if (UsAux.equals(eliminar)) { // verifica que el usuario exista
					listaDeUsuarios.remove(); // si está lo remueve
					return true;
				}
				throw new UsuarioInexistenteException();
			}
		}
		throw new NoEsAdministradorException();
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

	public Boolean recargarSaldo(Usuario usuario, Double monto) throws UsuarioInexistenteException, MontoInsuficiente {
		for (Usuario u : listaDeUsuarios) {
			if (usuario.equals(u)) { // si el usuario se encuentra en la lista
				if (monto > 0) { // si el monto ingresado es mas de 0
					usuario.setSaldo(monto);
					return true;
				}
				throw new MontoInsuficiente();
			}
		}
		throw new UsuarioInexistenteException();
	}

	private Integer obtenerFactorPuntos(Usuario usuario) throws UsuarioInexistenteException { // Porque el administrador obtiene más puntos por compra
		for (Usuario u : this.listaDeUsuarios) {
			if (u.equals(usuario)) {
				if (u instanceof Cliente) // si es cliente
					return ((Cliente) u).getFactorPuntos(); // casteo, devuelve el factor de puntos
				else if (u instanceof Administrador) // si es administrador
					return ((Administrador) u).getFactorPuntos();
			}
			throw new UsuarioInexistenteException();
		}
		return 0;
	}

	public DetalleDePago comprarProducto(Usuario usuario, Integer cantidad, Producto producto, String medioDePago) throws ProductoNoExisteException, UsuarioInexistenteException {
		if (this.listaDeProductos.contains(producto)) { // si está el producto que se quiere comprar
			Integer factorDePuntos = obtenerFactorPuntos(usuario); // obtener el factor de puntos por si es admin o no
			Integer cantidadDePuntos = (int) (cantidad * producto.getPrecioReal() * factorDePuntos); // la cantidad de
																										// puntos que da
																										// comprar el
																										// producto
			Ventas nuevaVenta = new Ventas(this.listaDeVentas.size() + 1, cantidad, producto, usuario, cantidadDePuntos,
					medioDePago); // crea una nueva venta con los datos
			DetalleDePago nuevoDetalle = new DetalleDePago(nuevaVenta.getIdVenta(), nuevaVenta.getPrecioTotal(),
					nuevaVenta.getTotalPuntos()); // crea el detalle de pago para devolver
			this.listaDeVentas.add(nuevaVenta); // agrega la venta
			return nuevoDetalle; // devuelve el detalle para que después a base se eso se pague
		}
		throw new ProductoNoExisteException();

	}
	
	
	
	

}
