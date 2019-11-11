package pb2.puntos;

public class Ventas {
	
	private Usuario usuario;
	private Integer cantidad;
	private Producto producto;
	private DetalleDePago detalle;
	private Integer cantidadDePuntos;
	private Boolean estado;

	public Ventas(Cliente cliente, Integer cantidad, Producto producto, DetalleDePago detalle,
			Integer cantidadDePuntos) {
		this.usuario = usuario;
		this.cantidad = cantidad;
		this.producto = producto;
		this.detalle = detalle;
		this.cantidadDePuntos = cantidadDePuntos;
		this.estado = estado;
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

	public DetalleDePago getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleDePago detalle) {
		this.detalle = detalle;
	}

	public Integer getCantidadDePuntos() {
		return cantidadDePuntos;
	}

	public void setCantidadDePuntos(Integer cantidadDePuntos) {
		this.cantidadDePuntos = cantidadDePuntos;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
