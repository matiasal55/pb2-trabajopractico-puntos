package pb2.puntos;

import java.util.Iterator;
import java.util.LinkedList;
import org.apache.commons.codec.digest.DigestUtils;

public class Sistema {
	private LinkedList<Producto> listaDeProductos;
	private LinkedList<Ventas> listaDeVentas;
	private LinkedList<Usuario> listaDeUsuarios;

	public Sistema() {
		this.listaDeProductos = new LinkedList<>();
		this.listaDeVentas = new LinkedList<>();
		this.listaDeUsuarios = new LinkedList<>();
	}

	public Boolean registrarUsuario(Usuario nuevo) {
		if (this.listaDeUsuarios.contains(nuevo))
			return false;
		nuevo.setId(this.listaDeUsuarios.size() + 1);
		this.listaDeUsuarios.add(nuevo);
		return true;
	}

	public Boolean loginUsuario(String email, String contrasenia) throws LoginFallidoException {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(email) && lista.getContrasenia().equals(contrasenia))
				return true;
		}
		throw new LoginFallidoException();
	}
	
	public Boolean eliminarUsuario(String email) {
		Iterator<Usuario> it=this.listaDeUsuarios.iterator();
		while(it.hasNext()) {
			Usuario aux=it.next();
			if(aux.getMail().equals(email)) {
				it.remove();
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
	
	public Boolean eliminarProducto(Integer id) {
		Iterator<Producto> it=this.listaDeProductos.iterator();
		while(it.hasNext()) {
			Producto aux=it.next();
			if(aux.getCodigo().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public Boolean comprarProducto(Usuario comprador, Integer cantidad, Producto producto, String medioDePago) {
		if (this.listaDeUsuarios.contains(comprador) && this.listaDeProductos.contains(producto) && cantidad > 0) {
			Integer cantidadDePuntos = (int) (producto.getPrecioReal() * obtenerFactorPuntos(comprador));
			Ventas nueva = new Ventas(this.listaDeVentas.size() + 1, cantidad, producto, comprador, cantidadDePuntos,
					medioDePago);
			if (pagarCompra(nueva))
				return true;
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
		Integer totalPuntos = nueva.getCantidad() * nueva.getProducto().getPrecioPuntos();
		if (nueva.getComprador().getPuntosAcumulados() >= totalPuntos) {
			for (Usuario lista2 : this.listaDeUsuarios) {
				if (lista2.equals(nueva.getComprador())) {
					lista2.setPuntosAcumulados(lista2.getPuntosAcumulados() - totalPuntos);
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

	public Boolean anularCompra(Integer id) {
		Iterator<Ventas> it = this.listaDeVentas.iterator();
		while (it.hasNext()) {
			Ventas aux = it.next();
			if (aux.getIdVenta().equals(id)) {
				reintegro(aux);
				it.remove();
				return true;
			}
		}
		return false;
	}

	private Boolean reintegro(Ventas aux) {
		for(Usuario lista:this.listaDeUsuarios) {
			if(lista.equals(aux.getComprador())) {
				if(aux.getMedioDePago().equals("Puntos")){
					Integer puntosAReintegrar=aux.getCantidad()*aux.getProducto().getPrecioPuntos();
					lista.setPuntosAcumulados(lista.getPuntosAcumulados()+puntosAReintegrar);
				}
				else {
					Double saldoAReintegrar=aux.getCantidad()*aux.getProducto().getPrecioReal();
					Integer puntosARestar=aux.getCantidadDePuntos();
					lista.setSaldo(lista.getSaldo()+saldoAReintegrar);
					lista.setPuntosAcumulados(lista.getPuntosAcumulados()-puntosARestar);
				}
				return true;
			}
		}
		return false;
	}

	public Integer calcularPuntos(Usuario usuario) {
		if (this.listaDeUsuarios.contains(usuario))
			return usuario.getPuntosAcumulados();
		return 0;
	}

}
