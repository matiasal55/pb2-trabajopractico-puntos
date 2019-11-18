package pb2.puntos;

public class ProductoExistenteException extends Exception {
	public ProductoExistenteException() {
		super("El producto ingresado ya existe.");
	}
}
