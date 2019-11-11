package pb2.puntos;

public class Producto {
	
	private String descripcion;
	private Integer codigo;
	private String nombreProducto;
	private Double precioReal;
	private Integer precioPuntos;

	public Producto(String descripcion, Integer codigo, String nombreProducto, Double precioReal, Integer precioPuntos) {
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.nombreProducto = nombreProducto;
		this.precioReal = precioReal;
		this.precioPuntos = precioPuntos;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getPrecioReal() {
		return precioReal;
	}

	public void setPrecioReal(Double precioReal) {
		this.precioReal = precioReal;
	}

	public Integer getPrecioPuntos() {
		return precioPuntos;
	}

	public void setPrecioPuntos(Integer precioPuntos) {
		this.precioPuntos = precioPuntos;
	}
	
	public int compareTo(Producto o) {
		return this.codigo.compareTo(o.getCodigo());
	}
}