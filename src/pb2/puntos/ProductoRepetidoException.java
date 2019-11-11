package pb2.puntos;

public class ProductoRepetidoException extends Exception {
	
	public ProductoRepetidoException() {
		super("Este producto ya se encuentra en la lista");
	}
}
