package pb2.puntos;

public class contraseniaInvalidaException extends Exception {
	public contraseniaInvalidaException() {
		super("La contraseña es incorrecta");
	}
}
