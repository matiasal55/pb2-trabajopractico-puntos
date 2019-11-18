package pb2.puntos;

public class PuntosInsuficienteException extends Exception {

	public PuntosInsuficienteException() {
		super("Los puntos acumulados son insuficientes para efectuar la compra.");
	}
	
}
