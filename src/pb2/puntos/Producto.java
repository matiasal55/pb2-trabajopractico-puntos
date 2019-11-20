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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Producto other = (Producto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
	

}
