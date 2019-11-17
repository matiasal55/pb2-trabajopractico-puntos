package pb2.puntos;

public class ContraseniaInvalidaException extends Exception {
	public ContraseniaInvalidaException() {
		super("La contraseña es incorrecta");
	}
}
