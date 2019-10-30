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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nombreProducto == null) ? 0 : nombreProducto.hashCode());
		result = prime * result + ((precioPuntos == null) ? 0 : precioPuntos.hashCode());
		result = prime * result + ((precioReal == null) ? 0 : precioReal.hashCode());
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
		if (nombreProducto == null) {
			if (other.nombreProducto != null)
				return false;
		} else if (!nombreProducto.equals(other.nombreProducto))
			return false;
		if (precioPuntos == null) {
			if (other.precioPuntos != null)
				return false;
		} else if (!precioPuntos.equals(other.precioPuntos))
			return false;
		if (precioReal == null) {
			if (other.precioReal != null)
				return false;
		} else if (!precioReal.equals(other.precioReal))
			return false;
		return true;
	}

}
