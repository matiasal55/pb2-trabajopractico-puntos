package pb2.puntos;

public class productoExistenteException extends Exception {
	public productoExistenteException() {
		super("El producto ingresado ya existe.");
	}
}
