package pb2.puntos;

public class Ventas implements Comparable<Ventas> {
	private Integer idVenta;
	private Cliente cliente;
	private Integer cantidad;
	private Producto producto;
	private String medioDePago;
	private Integer cantidadDePuntos;

	public Ventas(Integer idVenta, Cliente cliente, Integer cantidad, Producto producto, String medioDePago,
			Integer cantidadDePuntos) {
		this.idVenta = idVenta;
		this.cliente = cliente;
		this.cantidad = cantidad;
		this.producto = producto;
		this.medioDePago = medioDePago;
		this.cantidadDePuntos = cantidadDePuntos;
	}

	@Override
	public String toString() {
		return "idVenta=" + idVenta + ", cliente=" + cliente + ", cantidad=" + cantidad + ", producto="
				+ producto + ", medioDePago=" + medioDePago + ", cantidadDePuntos=" + cantidadDePuntos + "]";
	}

	public int compareTo(Ventas arg0) {
		return this.idVenta.compareTo(arg0.getIdVenta());
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVenta == null) ? 0 : idVenta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ventas other = (Ventas) obj;
		if (idVenta == null) {
			if (other.idVenta != null)
				return false;
		} else if (!idVenta.equals(other.idVenta))
			return false;
		return true;
	}

}
