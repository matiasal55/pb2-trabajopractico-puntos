package pb2.puntos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Sistema {
	private static Sistema instancia;
	private Set<Producto> listaDeProductos = new TreeSet<>();
	private Set<Ventas> listaDeVentas = new HashSet<>();
	private Set<Usuario> listaDeUsuarios = new HashSet<>();
	private Usuario usuarioLogueado;

	public static Sistema getInstancia() {
		if (instancia == null) {
			instancia = new Sistema();
		}

		return instancia;
	}

//_______________________________________________________________________________________________________________________________

	public Boolean registrarUsuario(Usuario nuevo) throws EmailYaRegistradoException {
		for (Usuario usr : listaDeUsuarios) {
			if (usr.getEmail().equals(nuevo.getEmail())) {
				throw new EmailYaRegistradoException();
			}
		}
		nuevo.setId(this.listaDeUsuarios.size() + 1);
		nuevo.setPuntosAcumulados(nuevo.getPuntosAcumulados() + 500);
		this.listaDeUsuarios.add(nuevo);
		return true;
	}

//_______________________________________________________________________________________________________________________________

	public Boolean loginUsuario(String email, String contrasenia)
			throws LoginFallidoException, EmailIncorrectoException, ContraseniaIncorrectaException {
		for (Usuario userAux : listaDeUsuarios) {
			if (userAux.getEmail().equals(email) && userAux.getContrasenia().equals(contrasenia)) {
				this.usuarioLogueado = userAux;
				return true;
			} else if (userAux.getEmail() != email && userAux.getContrasenia().equals(contrasenia)) {
				throw new EmailIncorrectoException();
			} else if (userAux.getEmail().equals(email) && userAux.getContrasenia() != contrasenia) {
				throw new ContraseniaIncorrectaException();
			}
		}
		throw new LoginFallidoException();
	}

//_______________________________________________________________________________________________________________________________

	public Boolean eliminarUsuario(String email) throws NoEsAdminException, UsuarioInexistenteException {
		if (usuarioLogueado instanceof Administrador) {
			Iterator<Usuario> it = this.listaDeUsuarios.iterator();
			while (it.hasNext()) {
				Usuario aux = it.next();
				if (aux.getEmail().equals(email)) {
					it.remove();
					return true;
				}
				throw new UsuarioInexistenteException();
			}
		}
		throw new NoEsAdminException();
	}

//_______________________________________________________________________________________________________________________________

	public Boolean modificarContrasenia(String email, String contraseniaAntigua, String nuevaContrasenia)
			throws UsuarioInexistenteException {
		for (Usuario userAux : listaDeUsuarios) {
			if (userAux.getEmail().equals(email) && userAux.getContrasenia().equals(contraseniaAntigua)) {
				userAux.setContrasenia(nuevaContrasenia);
				return true;
			}
		}
		throw new UsuarioInexistenteException();
	}

//_______________________________________________________________________________________________________________________________

	public Boolean agregarProducto(Producto nuevo) throws ProductoExistenteException, NoEsAdminException {
		if (usuarioLogueado instanceof Administrador) {
			if (this.listaDeProductos.contains(nuevo))
				throw new ProductoExistenteException();
			else
				this.listaDeProductos.add(nuevo);
			return true;
		}
		throw new NoEsAdminException();
	}

//_______________________________________________________________________________________________________________________________

	public Boolean eliminarProducto(Integer id) throws NoEsAdminException, ProductoInexistenteException {
		if (usuarioLogueado instanceof Administrador) {
			Iterator<Producto> it = this.listaDeProductos.iterator();
			while (it.hasNext()) {
				Producto aux = it.next();
				if (aux.getCodigo().equals(id)) {
					it.remove();
					return true;
				}
				throw new ProductoInexistenteException();
			}
		}
		throw new NoEsAdminException();
	}

//_______________________________________________________________________________________________________________________________

	public Boolean modificarProducto(Integer cod, Producto pNuevo)
			throws ProductoInexistenteException, NoEsAdminException {
		if (usuarioLogueado instanceof Administrador) {
			for (Producto p : listaDeProductos) {
				if (p.getCodigo().equals(cod)) {
					p.setDescripcion(pNuevo.getDescripcion());
					p.setCodigo(pNuevo.getCodigo());
					p.setNombre(pNuevo.getNombre());
					p.setPrecioPuntos(pNuevo.getPrecioPuntos());
					p.setPrecioReal(pNuevo.getPrecioReal());
					return true;
				}
				throw new ProductoInexistenteException();
			}
		}
		throw new NoEsAdminException();
	}

//_______________________________________________________________________________________________________________________________

	public DetallesDePago comprarProducto(Usuario comprador, Integer cantidad, Integer id, String medioDePago)
			throws CompraFallidaException {
		for (Producto prod : listaDeProductos) {
			if (prod.getCodigo().equals(id)) {
				Integer factorDePuntos = obtenerFactorPuntos(comprador);
				Integer cantidadDePuntos = (int) (cantidad * factorDePuntos);
				Ventas nuevaVenta = new Ventas(this.listaDeVentas.size() + 1, comprador, cantidad, prod, medioDePago,
						cantidadDePuntos);
				DetallesDePago nuevoDetalle = new DetallesDePago(nuevaVenta.getIdVenta(), nuevaVenta.getPrecioTotal(),
						nuevaVenta.getTotalPuntos());
				this.listaDeVentas.add(nuevaVenta);
				return nuevoDetalle;
			}
		}
		throw new CompraFallidaException();

	}

//_______________________________________________________________________________________________________________________________

	private Integer obtenerFactorPuntos(Usuario comprador) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.equals(comprador)) {
				if (lista instanceof Cliente)
					return ((Cliente) lista).getFactorDePuntos();
				else if (lista instanceof Administrador)
					return ((Administrador) lista).getFactorDePuntos();
			}
		}
		return 0;
	}

//_______________________________________________________________________________________________________________________________

	public Boolean pagarConPuntos(Integer idPago, Integer puntos)
			throws SaldoInsuficienteException, VentaFallidaException {
		for (Ventas lista : this.listaDeVentas) {
			if (lista.getIdVenta().equals(idPago)) {
				for (Usuario usr : this.listaDeUsuarios) {
					if (usr.equals(lista.getComprador())) {
						if (usr.getPuntosAcumulados() >= (puntos)) {
							lista.getComprador()
									.setPuntosAcumulados(lista.getComprador().getPuntosAcumulados() - puntos);
							lista.setEstadoDePago("Pagado");
							return true;
						} else {
							lista.setEstadoDePago("Puntos insuficientes");
							throw new SaldoInsuficienteException("Su saldo en puntos es insuficiente");
						}
					}
				}
			}
		}
		throw new VentaFallidaException();
	}

//_______________________________________________________________________________________________________________________________

	public Boolean pagarConSaldo(Integer id, Double monto)
			throws ProductoInexistenteException, SaldoInsuficienteException, VentaFallidaException {
		for (Ventas lista : this.listaDeVentas) {
			if (lista.getIdVenta().equals(id)) {
				for (Usuario lista2 : this.listaDeUsuarios) {
					if (lista2.equals(lista.getComprador())) {
						if (lista2.getSaldo() >= monto) {
							lista.getComprador().setPuntosAcumulados(lista.getCantidad() * obtenerFactorPuntos(lista2));
							lista.getComprador().setSaldo(lista.getComprador().getSaldo() - monto);
							lista.setEstadoDePago("Pagado");
							return true;
						} else {
							lista.setEstadoDePago("Saldo insuficiente");
							throw new SaldoInsuficienteException("Su saldo es insuficiente");
						}

					}
				}
			}

		}
		throw new VentaFallidaException();
	}

//_______________________________________________________________________________________________________________________________

	public Boolean cargarSaldo(Usuario comprador, Double monto) throws LaRecargaHaFalladoException {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.equals(comprador)) {
				Double saldoAnterior = lista.getSaldo();
				Double saldoNuevo = saldoAnterior + monto;
				lista.setSaldo(saldoNuevo);
				return true;
			}
		}
		throw new LaRecargaHaFalladoException();
	}

//_______________________________________________________________________________________________________________________________

	public Boolean eliminarDeListaDeVentas(Integer id) {
		Iterator<Ventas> it = this.listaDeVentas.iterator();
		while (it.hasNext()) {
			Ventas aux = it.next();
			if (aux.getIdVenta().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

//_______________________________________________________________________________________________________________________________

	public Boolean anularCompra(Integer id) {
		for (Ventas lista : this.listaDeVentas) {
			if (lista.getIdVenta().equals(id)) {
				Ventas aux = lista;
				reintegro(id, aux.getComprador());
				eliminarDeListaDeVentas(id);
				return true;
			}
		}
		return false;
	}

//_______________________________________________________________________________________________________________________________

	public void reintegro(Integer id, Usuario usr) {
		for (Ventas v : listaDeVentas) {
			if (v.getIdVenta().equals(id)) {
				for (Usuario u : listaDeUsuarios) {
					if (v.getComprador().equals(u)) {
						if (v.getMedioDePago().equals("efectivo")) {
							u.setSaldo(u.getSaldo() - v.getProducto().getPrecioReal());
							u.setPuntosAcumulados(u.getPuntosAcumulados() - v.getCantidadDePuntos());
						} else if (v.getMedioDePago().equals("puntos")) {
							u.setPuntosAcumulados(u.getPuntosAcumulados() + v.getProducto().getPrecioPuntos());
							u.setPuntosAcumulados(u.getPuntosAcumulados() - v.getCantidadDePuntos());
						}
					}
				}
			}
		}
	}

//_______________________________________________________________________________________________________________________________

	public void mostrarListaProductos() {
		for (Producto lista : listaDeProductos) {
			System.out.println(lista.toString());
		}
	}

//_______________________________________________________________________________________________________________________________

	public void mostarListaUsuarios() {
		for (Usuario lista : listaDeUsuarios) {
			System.out.println(lista.toString());
		}
	}

}