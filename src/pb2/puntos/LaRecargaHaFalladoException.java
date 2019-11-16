package pb2.puntos;

public class LaRecargaHaFalladoException extends Exception {

	public LaRecargaHaFalladoException() {
		super("No se ha podido completar la recarga.");
	}
}
