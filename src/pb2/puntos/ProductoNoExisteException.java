package pb2.puntos;

public class ProductoNoExisteException extends Exception {
	
	public ProductoNoExisteException() {
		super("El producto no existe o ya fue eliminadp");
	}

}
