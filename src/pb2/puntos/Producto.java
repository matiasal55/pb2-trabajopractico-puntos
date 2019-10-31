package pb2.puntos;

public class Producto extends Categoria {

	private Integer codigo;
	private String nombreProducto;
	private Double precioReal;
	private Double precioPuntos;

	public Producto(String descripcion, Integer codigo, String nombreProducto, Double precioReal, Double precioPuntos) {
		super(descripcion);
		this.codigo = codigo;
		this.nombreProducto = nombreProducto;
		this.precioReal = precioReal;
		this.precioPuntos = precioPuntos;
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

	public Double getPrecioPuntos() {
		return precioPuntos;
	}

	public void setPrecioPuntos(Double precioPuntos) {
		this.precioPuntos = precioPuntos;
	}
  
}
