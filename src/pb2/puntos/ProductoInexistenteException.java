package pb2.puntos;

public class ProductoInexistenteException extends Exception {
	public ProductoInexistenteException() {
		super("El producto no existe");
	}
}
