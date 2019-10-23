package pb2.puntos;

import java.util.ArrayList;

public abstract class Usuario extends Persona {
	private String mail;
	private String contrasenia;
	private Integer puntosAcumulados;
	private Integer id;
	protected Integer factorDePuntos;
	protected ArrayList<Compras> listaDeCompras;

	public Usuario(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido);
		this.mail = mail;
		this.contrasenia = contrasenia;
		this.puntosAcumulados = 0;
		this.id = 0;
		this.listaDeCompras = new ArrayList<>();
		this.factorDePuntos=0;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Integer getPuntos() {
		return puntosAcumulados;
	}

	public void setPuntos(Integer puntos) {
		this.puntosAcumulados = puntos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Compras> getListaDeCompras() {
		return listaDeCompras;
	}

	public void setListaDeCompras(ArrayList<Compras> listaDeCompras) {
		this.listaDeCompras = listaDeCompras;
	}
	
	public Integer getFactorDePuntos() {
		return factorDePuntos;
	}

	public void setFactorDePuntos(Integer factorDePuntos) {
		this.factorDePuntos = factorDePuntos;
	}

	public abstract Boolean comprarProducto(Producto producto);
	
	public Boolean pagarProducto(Compras compra, String medioDePago, Usuario usuario) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.equals(usuario)) {
				Ventas nueva = new Ventas(usuario, compra);
				if (procesarVenta(nueva, medioDePago)) {
					this.listaDeVentas.add(nueva);
					usuario.setPuntos(getPuntos()+compra.getCantidadDePuntos());
					return true;
				}
			}
		}
		return false;
	}
	
	public Boolean procesarVenta(Ventas venta, String medioDePago) {
		if (medioDePago.equals("Puntos")) {
			Integer puntosAnteriores = venta.getUsuario().getPuntos();
			Integer puntosADescontar = venta.getCompra().getPrecioPuntos();
			if (puntosAnteriores >= puntosADescontar) {
				venta.getUsuario().setPuntos(puntosAnteriores - puntosADescontar);
				venta.getCompra().setPrecioReal(0.0);
				return true;
			} else
				return false;
		} else {
			venta.getCompra().setPrecioPuntos(0);
			return true;
		}
	}

}
