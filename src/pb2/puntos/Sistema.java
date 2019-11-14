package pb2.puntos;

import java.net.IDN;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Sistema {
	private static Sistema instancia;
	private ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();
	private LinkedList<Usuario> listaDeUsuarios = new LinkedList<Usuario>();
	private Set<Ventas> listaDeVentas = new TreeSet<Ventas>();

//	public Sistema() {
//		this.listaDeProductos = new ArrayList<>();
//		this.listaDeVentas = new TreeSet<>();
//		this.listaDeUsuarios = new LinkedList<>();
//	}

	private Sistema() {
	}

//	public static Sistema getInstancia() {
//		if(this.instancia == null) {
//			this.instancia == new Sistema;
//		}
//		return this.instancia;
//	}

//----------------------------------------------------------------------------------------------------------------------------------

	public static Sistema getInstancia() {
		if (instancia == null) {
			instancia = new Sistema();
		}
		return instancia;
	}

	public Boolean registrarUsuario(Usuario usuario) throws EmailYaRegistrado {
		Iterator<Usuario> listaAux = listaDeUsuarios.iterator();
		while (listaAux.hasNext()) {
			Usuario usrAux = listaAux.next();
			if (usrAux.getEmail().equals(usuario.getEmail())) {
				throw new EmailYaRegistrado();
			}
		}
		usuario.setId(listaDeUsuarios.size() + 1);
		usuario.setPuntosAcumulados(500);
		listaDeUsuarios.add(usuario);
		return true;
	}

//----------------------------------------------------------------------------------------------------------------------------------

	public void eliminarUsuario(Usuario usr, Usuario usrAEliminar) {
		if (usr instanceof Administrador) {
			if (listaDeUsuarios.contains(usrAEliminar)) {
				listaDeUsuarios.remove(usrAEliminar);
			}
		}
	}

//----------------------------------------------------------------------------------------------------------------------------------

	public Boolean eliminarProducto(Usuario usr, Producto prod) {
		if (usr instanceof Administrador) {
			Iterator<Producto> listaAux = listaDeProductos.iterator();
			while (listaAux.hasNext()) {
				Producto aux = listaAux.next();
				if (aux.equals(prod)) {
					listaAux.remove();
					return true;
				}
			}
		}
		return false;
	}

//----------------------------------------------------------------------------------------------------------------------------------

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

//----------------------------------------------------------------------------------------------------------------------------------

	public Boolean agregarProducto(Usuario usr, Producto prod) throws NoEsAdminException {
		if (usr instanceof Administrador) {
			listaDeProductos.add(prod);
			return true;
		}
		throw new NoEsAdminException();
	}

//----------------------------------------------------------------------------------------------------------------------------------

//	public Integer autogeneracionDeId(Usuario usr) {
//		usr.setId(listaDeUsuarios.size() + 1);
//		return usr.getId();
//	}

	public Boolean realizarCompra(Ventas venta) throws VentaFallidaException, MetodoDePagoNoExistenteException,
			EmailYaRegistrado, ElUsuarioNoEstaRegistradoException {
		if (buscarUsuarioParaVerificarCompra(venta.getCliente()) == false) {
			throw new ElUsuarioNoEstaRegistradoException();
		}
//		if((loginUsuario(venta.getCliente().getEmail(),venta.getCliente().getContrasenia())) != true) {
//			throw new ElUsuarioNoEstaRegistradoException();
//		}
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
		if (registrarUsuario(venta.getCliente()) != true) {
			throw new ElUsuarioNoEstaRegistradoException();
		}
//		if (venta.getMedioDePago().equals("efectivo")) {
//			this.pagarEnEfectivo(venta);
//		} else if (venta.getMedioDePago().equals("puntos")) {
//			this.pagarConPuntos(venta);
//		} else if (venta.getMedioDePago() != "efectivo" || venta.getMedioDePago() != "puntos") {
//			throw new MetodoDePagoNoExistenteException();
//		}

		throw new VentaFallidaException();
	}

//----------------------------------------------------------------------------------------------------------------------------------

	public Boolean anularCompra(Ventas venta) {
		Iterator<Ventas> listaAux = listaDeVentas.iterator();
		while (listaAux.hasNext()) {
			Ventas aux = listaAux.next();
			if (aux.getIdVenta().equals(venta.getIdVenta())) {
//				aux.getCliente()
//						.setPuntosAcumulados(aux.getCliente().getPuntosAcumulados() - aux.getCantidadDePuntos());
				reembolso(aux, aux.getCliente());
				listaAux.remove();
				listaDeProductos.add(venta.getProducto());
				return true;
			}
		}
		return false;
	}

	public void reembolso(Ventas vent, Usuario usr) {
		for (Ventas v : listaDeVentas) {
			for (Usuario u : listaDeUsuarios) {
				if (v.getCliente().equals(u)) {
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

//----------------------------------------------------------------------------------------------------------------------------------

	public void pagarEnEfectivo(Ventas venta) throws EfectivoInsuficienteException {
		if (venta.getMedioDePago().equals("efectivo")
				&& venta.getCliente().getSaldo() < venta.getProducto().getPrecioReal()
				|| venta.getCliente().getSaldo() <= 0) {
			throw new EfectivoInsuficienteException();
		}
		if (venta.getMedioDePago().equals("efectivo")) {
			venta.getCliente().setSaldo(venta.getCliente().getSaldo() - venta.getProducto().getPrecioReal());
		}
	}

//----------------------------------------------------------------------------------------------------------------------------------

	public void pagarConPuntos(Ventas venta) {
		if (venta.getMedioDePago().equals("puntos")) {
			venta.getCliente().setPuntosAcumulados(
					venta.getCliente().getPuntosAcumulados() - venta.getProducto().getPrecioPuntos());
		}
	}

//----------------------------------------------------------------------------------------------------------------------------------

	public void obtenerComprasOrdenadasPorId() {
		for (Ventas lista : listaDeVentas) {
			System.out.println(lista);
		}
	}

//----------------------------------------------------------------------------------------------------------------------------------

	public void mostarListaUsuarios() {
		for (Usuario lista : listaDeUsuarios) {
			System.out.println(lista);
		}
	}
	
	//Ambos metodos son de auto-ayuda para verificar
	
	public void mostrarListaProductos() {
		for(Producto lista : listaDeProductos) {
			System.out.println(lista);
		}
	}

//----------------------------------------------------------------------------------------------------------------------------------

	public void recargarSaldo(Usuario usrARecargar, Double monto) {
		usrARecargar.setSaldo(usrARecargar.getSaldo() + monto);
	}

//----------------------------------------------------------------------------------------------------------------------------------

	public Boolean buscarUsuarioParaVerificarCompra(Usuario usr) {
		for (Usuario s : listaDeUsuarios) {
			if (usr.equals(s)) {
				return true;
			}
		}
		return false;
	}

//----------------------------------------------------------------------------------------------------------------------------------

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

	public Set<Ventas> getListaDeVentas() {
		return listaDeVentas;
	}

	public void setListaDeVentas(Set<Ventas> listaDeVentas) {
		this.listaDeVentas = listaDeVentas;
	}

	
}
