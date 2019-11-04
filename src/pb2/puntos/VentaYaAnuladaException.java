package pb2.puntos;

public class VentaYaAnuladaException extends Exception {
	
	public VentaYaAnuladaException() {
		super("La venta ya ha sido anulada o no existe");
	}
}

