package pb2.puntos;

public class productoInexistenteException extends Exception {
	public productoInexistenteException() {
		super("El producto no existe");
	}
}
