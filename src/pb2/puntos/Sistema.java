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
		for (Usuario u : listaDeUsuarios) {
			if (u.getEmail().equals(usuario.getEmail())) { // verifica que no sea el mismo usuario comparando el
															// mail
				throw new UsuarioYaRegistradoException();
			}
		}
		listaDeUsuarios.add(usuario);
		return true;
	}

	public Boolean loginUsuario(String email, String contrasenia) throws LoginFallidoException {
		for (Usuario u : listaDeUsuarios) {
			if (u.getEmail().equals(email) && u.getContrasenia().equals(contrasenia)) { // verifica que el
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
				Usuario u = listaAux.next();
				if (u.getEmail().equals(eliminar.getEmail())) { // verifica que el usuario exista por mail
					listaAux.remove(); // si está lo remueve
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
				if (producto.getCodigo().equals(p.getCodigo())) { // verifica que no se ingresen productos repetidos por
																	// codigo
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
				Producto p = listaAux.next();
				if (p.equals(producto)) { // verifica que el producto ingresado este en la lista de productos
					listaAux.remove(); // si está lo remueve
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
					p.setDescripcion(pNuevo.getDescripcion());
					p.setCodigo(pNuevo.getCodigo());
					p.setNombreProducto(pNuevo.getNombreProducto());
					p.setPrecioPuntos(pNuevo.getPrecioPuntos());
					p.setPrecioReal(pNuevo.getPrecioReal());
					return true;
				}
				throw new ProductoNoExisteException();
			}
		}
		throw new NoEsAdministradorException();
	}

	public Boolean recargarSaldo(Usuario usuario, Double monto) throws UsuarioInexistenteException, MontoIncorrecto {
		for (Usuario u : listaDeUsuarios) {
			if (usuario.getEmail().equals(u.getEmail())) { // si el usuario existe
				if (monto > 0) { // si el monto ingresado es mas de 0
					usuario.setSaldo(monto);
					return true;
				}
				throw new MontoIncorrecto();
			}
		}
		throw new UsuarioInexistenteException();
	}

	private Integer obtenerFactorPuntos(Usuario usuario) throws UsuarioInexistenteException { // Porque el administrador
																								// obtiene más puntos
																								// por compra
		for (Usuario u : this.listaDeUsuarios) {
			if (u.getEmail().equals(usuario.getEmail())) { // si existe el usuario
				if (u instanceof Cliente) // si es cliente
					return ((Cliente) u).getFactorPuntos(); // casteo, devuelve el factor de puntos
				else if (u instanceof Administrador) // si es administrador
					return ((Administrador) u).getFactorPuntos();
			}
			throw new UsuarioInexistenteException();
		}
		return 0;
	}

	public DetalleDePago comprarProducto(Usuario usuario, Integer cantidad, Producto producto, String medioDePago)
			throws ProductoNoExisteException, UsuarioInexistenteException {
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

	public Boolean pagarConSaldo(Integer idVenta, Double monto)
			throws SaldoInsuficienteException, VentaNoExisteException, UsuarioInexistenteException {
		for (Ventas v : this.listaDeVentas) { // Si existe la venta, se fija por id
			if (v.getIdVenta().equals(idVenta)) {
				for (Usuario v2 : this.listaDeUsuarios) { // si existe el usuario
					if (v2.getEmail().equals(v.getUsuario().getEmail())) {
						if (v2.getSaldo() >= monto) { // si le alcanza el saldo para pagar
							v2.setPuntosAcumulados(v2.getPuntosAcumulados() + v.getCantidadDePuntos()); // le agrega los
																										// puntos
																										// ganados por
																										// la compra
							v2.setSaldo(v2.getSaldo() - monto); // le resta el monto del salgo
							v.setEstadoDePago("Pagado"); // el estado cambia a "pagado" siendo antes "pagar"
							return true;
						} else {
							v.setEstadoDePago("Saldo insuficiente");
							throw new SaldoInsuficienteException();
						}

					}
					throw new UsuarioInexistenteException();
				}
			}
			throw new VentaNoExisteException();
		}
		return false;
	}

	public Boolean pagarConPuntos(Integer idVenta, Integer puntos)
			throws VentaNoExisteException, PuntosInsuficientesException, UsuarioInexistenteException {
		for (Ventas v : this.listaDeVentas) {
			if (v.getIdVenta().equals(idVenta)) { // si existe la venta
				for (Usuario v2 : this.listaDeUsuarios) {
					if (v2.getEmail().equals(v.getUsuario().getEmail())) { // si existe el usuario
						if (v2.getPuntosAcumulados() >= puntos) { // si le alcanzan los puntos
							v2.setPuntosAcumulados(v2.getPuntosAcumulados() - puntos); // si le alcanzan, se restan
							v.setEstadoDePago("Pagado"); // el estado cambia a "pagado" siendo antes "pagar"
							return true;
						}
						throw new PuntosInsuficientesException();
					}
					throw new UsuarioInexistenteException();
				}
			}
			throw new VentaNoExisteException();
		}
		return false;
	}

	private Boolean reintegro(Ventas venta) throws UsuarioInexistenteException {
		for (Usuario u : this.listaDeUsuarios) {
			if (u.getEmail().equals(venta.getUsuario().getEmail())) { // si existe el usuario
				if (venta.getEstadoDePago().equals("Pagado")) { // si la venta está pagada
					if (venta.getMedioDePago().equals("Puntos")) { // Si pagó con puntos
						Integer puntosAReintegrar = venta.getCantidad() * venta.getProducto().getPrecioPuntos(); // calcula
																													// los
																													// puntos
																													// a
																													// reintegrar
						u.setPuntosAcumulados(u.getPuntosAcumulados() + puntosAReintegrar); // reintegra los puntos al
																							// usuario
					} else { // si pagó con saldo
						Double saldoAReintegrar = venta.getCantidad() * venta.getProducto().getPrecioReal(); // calcula
																												// el
																												// saldo
																												// a
																												// reintegrar
						Integer puntosARestar = venta.getCantidadDePuntos(); // y los puntos que se le tienen que restar
																				// por la compra
						u.setSaldo(u.getSaldo() + saldoAReintegrar); // reintegra el saldo
						u.setPuntosAcumulados(u.getPuntosAcumulados() - puntosARestar); // saca los puntos
					}
				}
				return true;
			}
			throw new UsuarioInexistenteException();
		}
		return false;
	}

	public Boolean anularCompra(Integer idVenta) throws UsuarioInexistenteException, VentaNoExisteException {
		for (Ventas v : this.listaDeVentas) {
			if (v.getIdVenta().equals(idVenta)) { //si la venta existe
				Ventas v2 = v; // guarda la venta
				reintegro(v2); // hace el reintegro
				eliminarUnaVentaDeLaLista(idVenta); // elimina la venta de la lista
				return true;
			}
			throw new VentaNoExisteException();
		}
		return false;
	}

	public Boolean eliminarUnaVentaDeLaLista(Integer idVenta) throws VentaNoExisteException {
		Iterator<Ventas> listaAux = listaDeVentas.iterator();
		while (listaAux.hasNext()) {
			Ventas v = listaAux.next();
			if (v.getIdVenta().equals(idVenta)) { // verifica que la venta exista, por id
				listaAux.remove(); // si está lo remueve
				return true;
			}
			throw new VentaNoExisteException();
		}
		return false;
	}
	
}
