package pb2.puntos;

import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.commons.codec.digest.DigestUtils;

public class Sistema {
	protected static ArrayList<Producto> listaDeProductos;
	protected ArrayList<Ventas> listaDeProductosVendidos;
	protected static LinkedList<Usuario> listaDeUsuarios;

	public Sistema() {
		this.listaDeProductos = new ArrayList<>();
		this.listaDeProductosVendidos = new ArrayList<>();
		this.listaDeUsuarios = new LinkedList<>();
	}

	public ArrayList<Producto> getListaDeProductos() {
		return listaDeProductos;
	}

	public void setListaDeProductos(ArrayList<Producto> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
	}

	public ArrayList<Ventas> getListaDeProductosVendidos() {
		return listaDeProductosVendidos;
	}

	public void setListaDeProductosVendidos(ArrayList<Ventas> listaDeProductosVendidos) {
		this.listaDeProductosVendidos = listaDeProductosVendidos;
	}

	public LinkedList<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(LinkedList<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	public Boolean loginUsuario(String mail, String contrasenia) {
		// String encriptada = DigestUtils.sha1Hex(contrasenia);
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(mail) && lista.getContrasenia().equals(contrasenia))
				return true;
		}
		return false;
	}

	public Boolean registrarUsuario(Usuario usuario) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(usuario.getMail()))
				return false;
		}
//		usuario.setContrasenia(DigestUtils.sha1Hex(usuario.getContrasenia()));
		if(usuario.getContrasenia().matches(".*[A-Z].*")) {
		this.listaDeUsuarios.add(usuario);
		usuario.setId(this.listaDeUsuarios.indexOf(usuario));
		return true;
		}
		return false;
	}

	public Boolean comprarProducto(Producto producto, Integer cantidad) {
		for (Producto lista : this.listaDeProductos) {
			if (lista.equals(producto)) {
				// Integer cantidadDePuntos = (int) (producto.getPrecioReal() *
				// this.factorDePuntos);
				// Integer numeroDeOrden = this.listaDeCompras.size() + 1;
				// Ventas nueva=new Ventas(cliente, producto, cantidad, numeroDeOrden,
				// cantidadDePuntos, medio)
				// this.listaDeCompras.add(nuevaCompra);
				// System.out.println("Compra realizada");
				return true;
			}
		}
//		System.out.println("Compra rechazada");
		return false;
	}

	public Boolean pagarProducto(Compras compra, String medioDePago, Usuario usuario) {
		// for (Usuario lista : this.listaDeUsuarios) {
		// if (lista.equals(usuario)) {
		// Ventas nueva = new Ventas(usuario, compra);
		// if (procesarVenta(nueva, medioDePago)) {
		// this.listaDeVentas.add(nueva);
		// usuario.setPuntos(getPuntos()+compra.getCantidadDePuntos());
		// return true;
		// }
		// }
		// }
		return false;
	}

	public Boolean procesarVenta(Ventas venta, String medioDePago) {
		// if (medioDePago.equals("Puntos")) {
		// Integer puntosAnteriores = venta.getUsuario().getPuntos();
		// Integer puntosADescontar = venta.getCompra().getPrecioPuntos();
		// if (puntosAnteriores >= puntosADescontar) {
		// venta.getUsuario().setPuntos(puntosAnteriores - puntosADescontar);
		// venta.getCompra().setPrecioReal(0.0);
		// return true;
		// } else
		// return false;
		// } else {
		// venta.getCompra().setPrecioPuntos(0);
		return true;
		// }
	}

}
