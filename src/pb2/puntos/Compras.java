package pb2.puntos;

public class Compras extends Producto {
	private Integer numeroDeOrden;
	private Integer cantidadDePuntos;

	public Compras(String descripcion, Integer codigo, String nombre, Double precioReal, Integer precioPuntos,
			Integer numeroDeOrden, Integer cantidadDePuntos) {
		super(descripcion, codigo, nombre, precioReal, precioPuntos);
		this.numeroDeOrden = numeroDeOrden;
		this.cantidadDePuntos = cantidadDePuntos;
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

	public void setCantidadDePuntos(Integer cantidadDePuntos) {
		this.cantidadDePuntos = cantidadDePuntos;
	}

}
