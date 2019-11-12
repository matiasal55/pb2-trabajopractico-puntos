package pb2.puntos;

public class EfectivoInsuficienteException extends Exception {

	public EfectivoInsuficienteException() {
		super("El dinero en efectivo es insuficiente para realizar esta compra.");
	}

}
