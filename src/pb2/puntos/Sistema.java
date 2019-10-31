package pb2.puntos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.commons.codec.digest.DigestUtils;

public class Sistema {
	private ArrayList<Producto> listaDeProductos;
	protected ArrayList<Ventas> listaDeVentas;
	private LinkedList<Usuario> listaDeUsuarios;

	public Sistema() {
		this.listaDeProductos = new ArrayList<>();
		this.listaDeVentas = new ArrayList<>();
		this.listaDeUsuarios = new LinkedList<>();
	}

	public Boolean registrarUsuario(Usuario nuevo) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.equals(nuevo))
				return false;
		}
		nuevo.setId(this.listaDeUsuarios.size() + 1);
		this.listaDeUsuarios.add(nuevo);
		return true;
	}

	public Boolean loginUsuario(String email, String contrasenia) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(email) && lista.getContrasenia().equals(contrasenia))
				return true;
		}
		return false;
	}

	public Boolean comprarProducto(Usuario comprador, Integer cantidad, Producto producto, String medioDePago) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.equals(comprador)) {
				for (Producto listaProd : this.listaDeProductos) {
					if (listaProd.equals(producto) && cantidad > 0) {
						Integer cantidadDePuntos = (int) (producto.getPrecioReal() * obtenerFactorPuntos(comprador));
						Ventas nueva = new Ventas(this.listaDeVentas.size() + 1, cantidad, producto, comprador,
								cantidadDePuntos, medioDePago);
						pagarCompra(nueva);
						return true;
					}
				}
			}
		}
		return false;
	}

	private Integer obtenerFactorPuntos(Usuario comprador) {
		if (comprador instanceof Cliente)
			return ((Cliente) comprador).getFactorDePuntos();
		else
			return ((Administrador) comprador).getFactorDePuntos();
	}

	public Boolean pagarCompra(Ventas nueva) {
		if (nueva.getMedioDePago().equals("Puntos")) {
			if (pagarConPuntos(nueva)) {
				this.listaDeVentas.add(nueva);
				return true;
			}

		} else {
			if (pagarConSaldo(nueva)) {
				this.listaDeVentas.add(nueva);
				return true;
			}
		}
		return false;
	}

	public Boolean pagarConPuntos(Ventas nueva) {
		if (nueva.getComprador().getPuntosAcumulados() >= nueva.getCantidadDePuntos()) {
			for (Usuario lista2 : this.listaDeUsuarios) {
				if (lista2.equals(nueva.getComprador())) {
					lista2.setPuntosAcumulados(lista2.getPuntosAcumulados() - nueva.getCantidadDePuntos());
					return true;
				}
			}
		}
		return false;
	}

	public Boolean pagarConSaldo(Ventas nueva) {
		Double precioTotal = nueva.getCantidad() * nueva.getProducto().getPrecioReal();
		if (nueva.getComprador().getSaldo() >= precioTotal) {
			for (Usuario lista2 : this.listaDeUsuarios) {
				if (lista2.equals(nueva.getComprador())) {
					lista2.setSaldo(lista2.getSaldo() - precioTotal);
					lista2.setPuntosAcumulados(lista2.getPuntosAcumulados() + nueva.getCantidadDePuntos());
					return true;
				}
			}
		}
		return false;
	}

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

	public Boolean agregarProducto(Producto nuevo) {
		for (Producto lista : this.listaDeProductos) {
			if (lista.equals(nuevo))
				return false;
		}
		this.listaDeProductos.add(nuevo);
		return true;
	}

	public Boolean anularCompra(Integer id) {
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
	
	public Integer calcularPuntos(Usuario usuario) {
		for(Usuario lista: this.listaDeUsuarios) {
			if(lista.equals(usuario))
				return lista.getPuntosAcumulados();
		}
		return 0;
	}

}
