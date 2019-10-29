package pb2.puntos;

public class Ventas {
	private Cliente cliente;
	private Producto producto;
	private Integer cantidad;
	private Integer numeroDeOrden;
	private Integer cantidadDePuntos;
	private String medioDePago;

	public Ventas(Cliente cliente, Producto producto, Integer cantidad, Integer numeroDeOrden, String medioDePago) {
		this.cliente = cliente;
		this.producto = producto;
		this.cantidad = cantidad;
		this.numeroDeOrden = numeroDeOrden;
		this.cantidadDePuntos = (int)(this.producto.getPrecioReal()*cliente.getFactorDePuntos());
		this.medioDePago = medioDePago;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getNumeroDeOrden() {
		return numeroDeOrden;
	}

	public void setNumeroDeOrden(Integer numeroDeOrden) {
		this.numeroDeOrden = numeroDeOrden;
	}

	public Integer getCantidadDePuntos() {
		return cantidadDePuntos;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
