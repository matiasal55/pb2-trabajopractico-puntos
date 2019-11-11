package pb2.puntos;

public class DetallesDePago {
	private Integer idPago;
	private Double precioSaldo;
	private Integer precioPuntos;
	public DetallesDePago(Integer idPago, Double precioSaldo, Integer precioPuntos) {
		this.idPago = idPago;
		this.precioSaldo = precioSaldo;
		this.precioPuntos = precioPuntos;
	}
	public Integer getIdPago() {
		return idPago;
	}
	public Double getPrecioSaldo() {
		return precioSaldo;
	}
	public Integer getPrecioPuntos() {
		return precioPuntos;
	}
	
	
}
