package pb2.puntos;

public class Ventas {
	private Integer idVenta;
	private Integer cantidad;
	private Producto producto;
	private Usuario comprador;
	private Integer cantidadDePuntos;
	private String medioDePago;

	public Ventas(Integer idVenta, Integer cantidad, Producto producto, Usuario comprador, Integer cantidadDePuntos,
			String medioDePago) {
		this.idVenta = idVenta;
		this.cantidad = cantidad;
		this.producto = producto;
		this.comprador = comprador;
		this.cantidadDePuntos = cantidadDePuntos;
		this.medioDePago = medioDePago;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
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

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public Integer getCantidadDePuntos() {
		return cantidadDePuntos;
	}

	public void setCantidadDePuntos(Integer cantidadDePuntos) {
		this.cantidadDePuntos = cantidadDePuntos;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

}
