package pb2.puntos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Sistema {
	private static Sistema instancia;
	private Set<Producto> listaDeProductos = new HashSet<>();
	private Set<Ventas> listaDeVentas = new TreeSet<>();
	private Set<Usuario> listaDeUsuarios = new HashSet<>();

	public static Sistema getInstancia() {
		if (instancia == null) {
			instancia = new Sistema();
		}

		return instancia;
	}

	// this.listaDeProductos = new TreeSet<>();
//		this.listaDeVentas = new LinkedList<>();
//		this.listaDeUsuarios = new HashSet<>();
//	}
//  
// // ___________________________________________________________________________________________
//
	public Boolean registrarUsuario(Usuario nuevo) throws EmailYaRegistradoException {
		for (Usuario usr : listaDeUsuarios) {
			if (usr.getEmail().equals(nuevo.getEmail())) {
				throw new EmailYaRegistradoException();
			}
		}
		nuevo.setId(this.listaDeUsuarios.size() + 1);
		this.listaDeUsuarios.add(nuevo);
		return true;
	}

//
//	public Boolean loginUsuario(String email, String contrasenia)
//			throws LoginFallidoException, ContraseniaInvalidaException {
//		for (Usuario usr : this.listaDeUsuarios) {
//			if (usr.getEmail().equals(email)) {
//				if (usr.getContrasenia().equals(contrasenia))
//					return true;
//				else if (usr.getContrasenia() != contrasenia) {
//					throw new ContraseniaInvalidaException();
//				}
//			}
//
//		}
//		throw new LoginFallidoException();
//	}

	public Boolean loginUsuario(String email, String contrasenia)
			throws LoginFallidoException, EmailIncorrectoException, ContraseniaIncorrectaException {
		for (Usuario userAux : listaDeUsuarios) {
			if (userAux.getEmail().equals(email) && userAux.getContrasenia().equals(contrasenia)) {
				return true;
			} else if (userAux.getEmail() != email && userAux.getContrasenia().equals(contrasenia)) {
				throw new EmailIncorrectoException();
			} else if (userAux.getEmail().equals(email) && userAux.getContrasenia() != contrasenia) {
				throw new ContraseniaIncorrectaException();
			}
		}
		throw new LoginFallidoException();
	}

//  
	public Boolean eliminarUsuario(Usuario usr, String email) throws NoEsAdminException {
		if (usr instanceof Administrador) {
			Iterator<Usuario> it = this.listaDeUsuarios.iterator();
			while (it.hasNext()) {
				Usuario aux = it.next();
				if (aux.getEmail().equals(email)) {
					it.remove();
					return true;
				}
			}
		}
		throw new NoEsAdminException();
	}
	// ___________________________________________________________________________________________

  public Boolean agregarProducto(Producto nuevo) throws productoExistenteException {
	  if(this.listaDeProductos.contains(nuevo))
		  throw new productoExistenteException();
	  this.listaDeProductos.add(nuevo);
	  return true;
  }
//
//	// ___________________________________________________________________________________________

//
	public Boolean eliminarProducto(Usuario usr, Integer id) throws NoEsAdminException {
		if (usr instanceof Administrador) {
			Iterator<Producto> it = this.listaDeProductos.iterator();
			while (it.hasNext()) {
				Producto aux = it.next();
				if (aux.getCodigo().equals(id)) {
					it.remove();
					return true;
				}
			}
		}
		throw new NoEsAdminException();
	}

//
//	// ___________________________________________________________________________________________
//
	public DetallesDePago comprarProducto(Usuario comprador, Integer cantidad, Producto producto, String medioDePago)
			throws CompraFallidaException {
		for (Producto prod : listaDeProductos) {
			if (prod.equals(producto)) {
				Integer factorDePuntos = obtenerFactorPuntos(comprador);
				Integer cantidadDePuntos = cantidad /* producto.getPrecioReal() */ * factorDePuntos;
				Ventas nuevaVenta = new Ventas(this.listaDeVentas.size() + 1, cantidad, producto, comprador,
				//Error --->		cantidadDePuntos, medioDePago);
				DetallesDePago nuevoDetalle = new DetallesDePago(nuevaVenta.getIdVenta(), nuevaVenta.getPrecioTotal(),
						nuevaVenta.getTotalPuntos());
				this.listaDeVentas.add(nuevaVenta);
				return nuevoDetalle;
			}
		}
		throw new CompraFallidaException();

	}

//
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

//
	public Boolean pagarConPuntos(Integer idPago, Integer puntos)
			throws SaldoInsuficienteException, VentaFallidaException {
		for (Ventas lista : this.listaDeVentas) {
			if (lista.getIdVenta().equals(idPago)) {
				for (Usuario usr : this.listaDeUsuarios) {
					if (usr.equals(lista.getComprador()) && usr.getPuntosAcumulados() >= puntos) {
						usr.setPuntosAcumulados(usr.getPuntosAcumulados() - puntos);
						lista.setEstadoDePago("Pagado");
						return true;
					} else {
						lista.setEstadoDePago("Puntos insuficientes");
						throw new SaldoInsuficienteException("Su saldo en puntos es insuficiente");
					}
				}
			}
		}
		throw new VentaFallidaException();
	}

//
	public Boolean pagarConSaldo(Integer id, Double monto)
			throws ProductoInexistenteException, SaldoInsuficienteException, VentaFallidaException {
		for (Ventas lista : this.listaDeVentas) {
			if (lista.getIdVenta().equals(id)) {
				for (Usuario lista2 : this.listaDeUsuarios) {
					if (lista2.equals(lista.getComprador())) {
						if (lista2.getSaldo() >= monto) {
							lista2.setPuntosAcumulados(lista2.getPuntosAcumulados() + lista.getCantidadDePuntos());
							lista2.setSaldo(lista2.getSaldo() - monto);
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

//
//	// ___________________________________________________________________________________________
//
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

//
//	// ___________________________________________________________________________________________
//
//	public Double buscarPrecioDeLaCompraEnEfectivo(Integer id) {
//		Double precio = 0.0;
//		for (Ventas lista : this.listaDeVentas) {
//			if (lista.getIdVenta().equals(id))
//				precio = lista.getPrecioTotal();
//		}
//		return precio;
//	}
//
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

//
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
//
//	private Boolean reintegro(Ventas aux) {
//		for (Usuario lista : this.listaDeUsuarios) {
//			if (lista.equals(aux.getComprador()) && aux.getEstadoDePago().equals("Pagado")) {
//				if (aux.getMedioDePago().equals("Puntos")) {
//					Integer puntosAReintegrar = aux.getCantidad() * aux.getProducto().getPrecioPuntos();
//					lista.setPuntosAcumulados(lista.getPuntosAcumulados() + puntosAReintegrar);
//				} else {
//					Double saldoAReintegrar = aux.getCantidad() * aux.getProducto().getPrecioReal();
//					Integer puntosARestar = aux.getCantidadDePuntos();
//					lista.setSaldo(lista.getSaldo() + saldoAReintegrar);
//					lista.setPuntosAcumulados(lista.getPuntosAcumulados() - puntosARestar);
//				}
//				return true;
//			}
//		}
//		return false;
//	}

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

	public void mostrarListaProductos() {
		for (Producto lista : listaDeProductos) {
			System.out.println(lista);
		}
	}

	public void mostarListaUsuarios() {
		for (Usuario lista : listaDeUsuarios) {
			System.out.println(lista);
		}
	}
//
//	// ___________________________________________________________________________________________
//
//	public Integer calcularPuntos(Usuario usuario) {
//		if (this.listaDeUsuarios.contains(usuario))
//			return usuario.getPuntosAcumulados();
//		return 0;
//  }

}