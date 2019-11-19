package pb2.puntos;

public class Producto extends Categoria implements Comparable<Producto> {
	private Integer codigo;
	private String nombre;
	private Double precioReal;
	private Integer precioPuntos;

	public Producto(String descripcion, Integer codigo, String nombre, Double precioReal, Integer precioPuntos) {
		super(descripcion);
		this.codigo = codigo;
		this.nombre = nombre;
		this.precioReal = precioReal;
		this.precioPuntos = precioPuntos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@Override
	public int compareTo(Producto o) {
		return this.codigo.compareTo(o.getCodigo());
	}

	@Override
	public String toString() {
		return "Descripcion: " + getDescripcion() +", codigo: " + codigo + ", nombre: " + nombre + ", precio saldo: " + precioReal + ", precio en puntos: "
				+ precioPuntos;
	}

}
