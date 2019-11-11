package pb2.puntos;

public class DetalleDePago {
	
	private Integer id;
	private Double totalEnEfectivo;
	private Integer totalEnPuntos;
	
	public DetalleDePago(Integer id, Double totalEnEfectivo, Integer totalEnPuntos) {
		this.id = id;
		this.totalEnEfectivo = totalEnEfectivo;
		this.totalEnPuntos = totalEnPuntos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalEnEfectivo() {
		return totalEnEfectivo;
	}

	public void setTotalEnEfectivo(Double totalEnEfectivo) {
		this.totalEnEfectivo = totalEnEfectivo;
	}

	public Integer getTotalEnPuntos() {
		return totalEnPuntos;
	}

	public void setTotalEnPuntos(Integer totalEnPuntos) {
		this.totalEnPuntos = totalEnPuntos;
	}

}

