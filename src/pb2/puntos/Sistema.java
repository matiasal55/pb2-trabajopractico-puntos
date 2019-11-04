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
	
	public Integer realizarCompra(Ventas venta) throws VentaFallidaException {
		Iterator<Producto> listaAux = listaDeProductos.iterator();
		while(listaAux.hasNext()) {
			Producto prodAux = listaAux.next();
			if(prodAux.equals(venta.getProducto())){
				listaDeProductos.remove(venta.getProducto());
				listaDeVentas.add(venta);
				return venta.getIdVenta();
			}
		}
		throw new VentaFallidaException();
	}
	
	public Boolean anularVenta (Integer idVenta) throws VentaYaAnuladaException {
		Iterator<Ventas> listaAux = listaDeVentas.iterator();
		while(listaAux.hasNext()) {
			Ventas v = listaAux.next();
			if(v.getIdVenta().equals(idVenta)){
				listaDeVentas.remove(v);
				return true;
			}
		}
		throw new VentaYaAnuladaException();
	}
	
	public Boolean pagarConPuntos (Producto producto, Usuario usuario, Integer IdVenta) throws VentaYaAnuladaException, PagoConPuntosFallido {
			Integer pagar = 0;
				for (Usuario u : listaDeUsuarios) {
					for(Ventas v : listaDeVentas) {
						if(u.getId().equals(usuario.getId()) && u.getPuntosAcumulados()>= producto.getPrecioPuntos()) {
						pagar = u.getPuntosAcumulados() - producto.getPrecioPuntos() * v.getCantidad();
						u.setPuntosAcumulados(pagar);
						return true;
					}
						if (v.getIdVenta().equals(IdVenta)) {
							this.anularVenta(IdVenta);
							pagar = u.getPuntosAcumulados() + producto.getPrecioPuntos() * v.getCantidad();
							u.setPuntosAcumulados(pagar);
					}
				}
			}
			throw new PagoConPuntosFallido();
		}
	
	public Boolean pagarConEfectivo (Producto producto, Usuario usuario, Integer IdVenta) throws VentaYaAnuladaException, PagoConEfectivoFallido {
		Double pagar = 0.0;
		for (Usuario u : listaDeUsuarios) {
			for (Ventas v : listaDeVentas) {
			if(u.getId().equals(usuario.getId()) && u.getSaldo()>= producto.getPrecioReal()) {
				pagar = u.getSaldo() - producto.getPrecioReal() * v.getCantidad();
				u.setSaldo(pagar);
				return true;
				}
			if (v.getIdVenta().equals(IdVenta)) {
				this.anularVenta(IdVenta);
				pagar = u.getSaldo() + producto.getPrecioReal() * v.getCantidad();
				u.setSaldo(pagar);
				}
			}
		}
		throw new PagoConEfectivoFallido();
	}
	
}
