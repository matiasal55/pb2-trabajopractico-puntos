package pb2.puntos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Sistema {
	private Set<Producto> listaDeProductos;
	private LinkedList<Ventas> listaDeVentas;
	private Set<Usuario> listaDeUsuarios;

	public Sistema() {
		this.listaDeProductos = new TreeSet<>();
		this.listaDeVentas = new LinkedList<>();
		this.listaDeUsuarios = new HashSet<>();
	}

	// ___________________________________________________________________________________________

	public Boolean registrarUsuario(Usuario nuevo) throws usuarioExistenteException {
		if (this.listaDeUsuarios.contains(nuevo))
			throw new usuarioExistenteException("El nombre de usuario ya existe");
		nuevo.setId(this.listaDeUsuarios.size() + 1);
		this.listaDeUsuarios.add(nuevo);
		return true;
	}

	public Boolean loginUsuario(String email, String contrasenia)
			throws LoginFallidoException, contraseniaInvalidaException {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(email)) {
				if (lista.getContrasenia().equals(contrasenia))
					return true;
				throw new contraseniaInvalidaException();
			}

		}
		throw new LoginFallidoException();
	}

	public Boolean eliminarUsuario(String email) {
		Iterator<Usuario> it = this.listaDeUsuarios.iterator();
		while (it.hasNext()) {
			Usuario aux = it.next();
			if (aux.getMail().equals(email)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	// ___________________________________________________________________________________________

	public Boolean agregarProducto(Producto nuevo) {
		this.listaDeProductos.add(nuevo);
		return true;
	}

	public Boolean eliminarProducto(Integer id) {
		Iterator<Producto> it = this.listaDeProductos.iterator();
		while (it.hasNext()) {
			Producto aux = it.next();
			if (aux.getCodigo().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	// ___________________________________________________________________________________________

	public DetallesDePago comprarProducto(Usuario comprador, Integer cantidad, Producto producto, String medioDePago) {
		if (this.listaDeProductos.contains(producto)) {
			Integer factorDePuntos = obtenerFactorPuntos(comprador);
			Integer cantidadDePuntos = (int) (cantidad * producto.getPrecioReal() * factorDePuntos);
			Ventas nuevaVenta = new Ventas(this.listaDeVentas.size() + 1, cantidad, producto, comprador,
					cantidadDePuntos, medioDePago);
			DetallesDePago nuevoDetalle = new DetallesDePago(nuevaVenta.getIdVenta(), nuevaVenta.getPrecioTotal(),
					nuevaVenta.getTotalPuntos());
			this.listaDeVentas.add(nuevaVenta);
			return nuevoDetalle;
		}
		return null;

	}

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

	public Boolean pagarConPuntos(Integer idPago, Integer puntos) throws saldoInsuficienteException {
		for (Ventas lista : this.listaDeVentas) {
			if (lista.getIdVenta().equals(idPago)) {
				for (Usuario lista2 : this.listaDeUsuarios) {
					if (lista2.equals(lista.getComprador()) && lista2.getPuntosAcumulados() >= puntos) {
						lista2.setPuntosAcumulados(lista2.getPuntosAcumulados() - puntos);
						lista.setEstadoDePago("Pagado");
						return true;
					} else {
						lista.setEstadoDePago("Puntos insuficientes");
						throw new saldoInsuficienteException("Su saldo en puntos es insuficiente");
					}
				}
			}
		}
		return false;
	}

	public Boolean pagarConSaldo(Integer id, Double monto)
			throws productoInexistenteException, saldoInsuficienteException {
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
							throw new saldoInsuficienteException("Su saldo es insuficiente");
						}

					}
				}
			}
			
		}
			throw new productoInexistenteException();
	}

	// ___________________________________________________________________________________________

	public Boolean cargarSaldo(Usuario comprador, Double monto) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.equals(comprador)) {
				Double saldoAnterior = lista.getSaldo();
				Double saldoNuevo = saldoAnterior + monto;
				lista.setSaldo(saldoNuevo);
				return true;
			}
		}
		return false;
	}

	// ___________________________________________________________________________________________

	public Double buscarPrecioDeLaCompraEnEfectivo(Integer id) {
		Double precio = 0.0;
		for (Ventas lista : this.listaDeVentas) {
			if (lista.getIdVenta().equals(id))
				precio = lista.getPrecioTotal();
		}
		return precio;
	}

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

	public Boolean anularCompra(Integer id) {
		for (Ventas lista : this.listaDeVentas) {
			if (lista.getIdVenta().equals(id)) {
				Ventas aux = lista;
				reintegro(aux);
				eliminarDeListaDeVentas(id);
				return true;
			}
		}
		return false;
	}

	private Boolean reintegro(Ventas aux) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.equals(aux.getComprador()) && aux.getEstadoDePago().equals("Pagado")) {
				if (aux.getMedioDePago().equals("Puntos")) {
					Integer puntosAReintegrar = aux.getCantidad() * aux.getProducto().getPrecioPuntos();
					lista.setPuntosAcumulados(lista.getPuntosAcumulados() + puntosAReintegrar);
				} else {
					Double saldoAReintegrar = aux.getCantidad() * aux.getProducto().getPrecioReal();
					Integer puntosARestar = aux.getCantidadDePuntos();
					lista.setSaldo(lista.getSaldo() + saldoAReintegrar);
					lista.setPuntosAcumulados(lista.getPuntosAcumulados() - puntosARestar);
				}
				return true;
			}
		}
		return false;
	}

	// ___________________________________________________________________________________________

	public Integer calcularPuntos(Usuario usuario) {
		if (this.listaDeUsuarios.contains(usuario))
			return usuario.getPuntosAcumulados();
		return 0;
	}

}
