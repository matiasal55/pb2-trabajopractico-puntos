package pb2.puntos;

public class SaldoInsuficienteException extends Exception {
	
	public SaldoInsuficienteException() {
		super("Su saldo es insuficiente");
	}
}
