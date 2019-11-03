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

	public void agregarProducto(Producto prod) {
		listaDeProductos.add(prod);
	}
	
	public Boolean realizarCompra(Ventas venta) throws VentaFallidaException {
		Iterator<Producto> listaAux = listaDeProductos.iterator();
		while(listaAux.hasNext()) {
			Producto prodAux = listaAux.next();
			if(prodAux.equals(venta.getProducto())){
				listaDeProductos.remove(venta.getProducto());
				listaDeVentas.add(venta);
				return true;
			}
		}
		throw new VentaFallidaException();
	}
	
	public void pagarConPuntos (Producto producto, Usuario usuario) {
		for (Producto p : listaDeProductos) {
			if (p.getCodigo().equals(producto.getCodigo())){
				for (Usuario u : listaDeUsuarios) {
					if(u.getId().equals(usuario.getId())) {
					Integer pagar = u.getPuntosAcumulados() - p.getPrecioPuntos();
					u.setPuntosAcumulados(pagar);
					}
				}
			}
		}
	}
	
	public void pagarConEfectivo (Producto producto, Usuario usuario) {
		for (Producto p : listaDeProductos) {
			if (p.getCodigo().equals(producto.getCodigo())){
				for (Usuario u : listaDeUsuarios) {
					if(u.getId().equals(usuario.getId())) {
					Double pagar = u.getSaldo() - p.getPrecioReal();
					u.setSaldo(pagar);
					}
				}
			}
		}
	}
}
