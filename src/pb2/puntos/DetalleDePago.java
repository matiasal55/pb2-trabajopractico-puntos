package pb2.puntos;

public class DetalleDePago {
	
	private Integer id;
	private Double totalEnSaldo;
	private Integer totalEnPuntos;
	
	public DetalleDePago(Integer id, Double totalEnSaldo, Integer totalEnPuntos) {
		this.id = id;
		this.totalEnSaldo = totalEnSaldo;
		this.totalEnPuntos = totalEnPuntos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalEnSaldo() {
		return totalEnSaldo;
	}

	public void setTotalEnSaldo(Double totalEnSaldo) {
		this.totalEnSaldo = totalEnSaldo;
	}

	public Integer getTotalEnPuntos() {
		return totalEnPuntos;
	}

	public void setTotalEnPuntos(Integer totalEnPuntos) {
		this.totalEnPuntos = totalEnPuntos;
	}

}

