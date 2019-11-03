package pb2.puntos;

public class PagoConEfectivoFallido extends Exception {
	
	public PagoConEfectivoFallido() {
		super("Saldo insuficiente");
	}
}
