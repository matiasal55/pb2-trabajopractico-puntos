package pb2.puntos;

public class Ventas {
	private Integer idVenta;
	private Integer cantidad;
	private Producto producto;
	private Integer totalPuntos;
	private Double precioTotal;
	private String comprador;
	private Integer cantidadDePuntos;
	private String medioDePago;
	private String estadoDePago;

	public Ventas(Integer idVenta, Integer cantidad, Producto producto, Integer totalPuntos, Double precioTotal,
			String comprador, Integer cantidadDePuntos, String medioDePago, String estadoDePago) {
		this.idVenta = idVenta;
		this.cantidad = cantidad;
		this.producto = producto;
		this.totalPuntos = totalPuntos;
		this.precioTotal = precioTotal;
		this.comprador = comprador;
		this.cantidadDePuntos = cantidadDePuntos;
		this.medioDePago = medioDePago;
		this.estadoDePago = estadoDePago;
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

	public Integer getTotalPuntos() {
		return totalPuntos;
	}

	public void setTotalPuntos(Integer totalPuntos) {
		this.totalPuntos = totalPuntos;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
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

	public String getEstadoDePago() {
		return estadoDePago;
	}

	public void setEstadoDePago(String estadoDePago) {
		this.estadoDePago = estadoDePago;
	}

}
