package pb2.puntos;

public class PagoConPuntosFallido extends Exception {
	
	public PagoConPuntosFallido() {
		super("Puntos insuficientes");
	}
}
