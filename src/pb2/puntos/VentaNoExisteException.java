package pb2.puntos;

public class VentaNoExisteException extends Exception {
	
	public VentaNoExisteException() {
		super("La venta ya ha sido anulada o no existe");
	}
}

