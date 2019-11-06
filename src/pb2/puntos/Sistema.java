package pb2.puntos;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedList;

import javax.security.auth.login.LoginException;

public class Sistema {
	private ArrayList<Producto> listaDeProductos;
	private LinkedList<Usuario> listaDeUsuarios;
	private ArrayList<Ventas> listaDeVentas;

	public Sistema() {
		this.listaDeProductos = new ArrayList<>();
		this.listaDeVentas = new ArrayList<>();
		this.listaDeUsuarios = new LinkedList<>();
	}

	public Boolean registrarUsuario(Usuario usuario) throws UsuarioYaRegistrado {
		Iterator<Usuario> listaAux = listaDeUsuarios.iterator();
		while (listaAux.hasNext()) {
			Usuario usrAux = listaAux.next();
			if (usrAux.getEmail().equals(usuario.getEmail())) {
				throw new UsuarioYaRegistrado();
			}
		}
		listaDeUsuarios.add(usuario);
		return true;
	}

	public Boolean loginUsuario(String email, String contrasenia) throws LoginFallidoException {
		for (Usuario listaAux : listaDeUsuarios) {
			if (listaAux.getEmail().equals(email) && listaAux.getContrasenia().equals(contrasenia)) {
				return true;
			}
		}
		throw new LoginFallidoException();
	}

	public Boolean agregarProducto(Usuario usr, Producto prod) throws NoEsAdminException {
		if (usr instanceof Administrador) {
			listaDeProductos.add(prod);
			return true;
		}
		throw new NoEsAdminException();
	}

	public Boolean realizarCompra(Ventas venta) throws VentaFallidaException {
		Iterator<Producto> listaAux = listaDeProductos.iterator();
		while (listaAux.hasNext()) {
			Producto prodAux = listaAux.next();
			if (prodAux.equals(venta.getProducto())) {
				listaDeProductos.remove(prodAux);
				listaDeVentas.add(venta);
				venta.getCliente()
						.setPuntosAcumulados(venta.getCliente().getPuntosAcumulados() + venta.getCantidadDePuntos());
				return true;
			}
		}
		if (venta.getMedioDePago().equals("efectivo")) {
			venta.getCliente().setSaldo(venta.getCliente().getSaldo() - venta.getProducto().getPrecioReal());
		}
		if (venta.getMedioDePago().equals("puntos")) {
			venta.getCliente().setPuntosAcumulados(
					venta.getCliente().getPuntosAcumulados() - venta.getProducto().getPrecioPuntos());
		}
		if (venta.getMedioDePago() != "efectivo" || venta.getMedioDePago() != "puntos") {
			throw new VentaFallidaException();
		}

		throw new VentaFallidaException();
	}

	public void obtenerComprasOrdenadasPorLetra() {

	}

	public void recargarSaldo(Usuario usrARecargar, Integer monto) {
		usrARecargar.setSaldo(usrARecargar.getSaldo() + monto);
	}

	public ArrayList<Producto> getListaDeProductos() {
		return listaDeProductos;
	}

	public void setListaDeProductos(ArrayList<Producto> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
	}

	public LinkedList<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(LinkedList<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	public ArrayList<Ventas> getListaDeVentas() {
		return listaDeVentas;
	}

	public void setListaDeVentas(ArrayList<Ventas> listaDeVentas) {
		this.listaDeVentas = listaDeVentas;
	}

}