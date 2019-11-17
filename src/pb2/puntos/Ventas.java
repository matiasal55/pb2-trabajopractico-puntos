package pb2.puntos;

public class Ventas {
	private Integer idVenta;
	private Integer cantidad;
	private Producto producto;
	private Integer totalPuntos;
	private Double precioTotal;
	private Usuario comprador;
	private Integer cantidadDePuntos;
	private String medioDePago;
	private String estadoDePago;

	public Ventas(Integer idVenta, Usuario comprador, Integer cantidad, Producto producto, String medioDePago,
			Integer cantidadDePuntos) {
		this.idVenta = idVenta;
		this.cantidad = cantidad;
		this.producto = producto;
		this.totalPuntos = cantidad * producto.getPrecioPuntos(); // precio total en puntos
		this.precioTotal = cantidad * producto.getPrecioReal(); // precio total en saldo
		this.setComprador(comprador);
		this.cantidadDePuntos = cantidadDePuntos; // puntos que suma el usuario
		this.medioDePago = medioDePago;
		this.estadoDePago = "Pagar";
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

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

}
