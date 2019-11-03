package pb2.puntos;

public class Ventas {
	private Integer idVenta;
	private Usuario usuario;
	private Integer cantidad;
	private Producto producto;
	private String medioDePago;
	private Integer cantidadDePuntos;

	public Ventas(Integer idVenta, Cliente cliente, Integer cantidad, Producto producto, String medioDePago,
			Integer cantidadDePuntos) {
		this.idVenta = idVenta;
		this.usuario = usuario;
		this.cantidad = cantidad;
		this.producto = producto;
		this.medioDePago = medioDePago;
		this.cantidadDePuntos = cantidadDePuntos;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	public Integer getCantidadDePuntos() {
		return cantidadDePuntos;
	}

	public void setCantidadDePuntos(Integer cantidadDePuntos) {
		this.cantidadDePuntos = cantidadDePuntos;
	}

}
