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
	
	public Boolean pagarConPuntos (Producto producto, Usuario usuario) throws PagoConPuntosFallido {
				for (Usuario u : listaDeUsuarios) {
					if(u.getId().equals(usuario.getId()) && u.getPuntosAcumulados()>= producto.getPrecioPuntos()) {
					Integer pagar = u.getPuntosAcumulados() - producto.getPrecioPuntos();
					u.setPuntosAcumulados(pagar);
					return true;
					}
				}
				throw new PagoConPuntosFallido();
		}
	
	public Boolean pagarConEfectivo (Producto producto, Usuario usuario) throws PagoConEfectivoFallido {
		for (Usuario u : listaDeUsuarios) {
			if(u.getId().equals(usuario.getId()) && u.getSaldo()>= producto.getPrecioReal()) {
				Double pagar = u.getSaldo() - producto.getPrecioReal();
				u.setSaldo(pagar);
				return true;
			}
		}
		throw new PagoConEfectivoFallido();
	}
	
	
}
