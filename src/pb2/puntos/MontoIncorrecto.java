package pb2.puntos;

public class MontoIncorrecto extends Exception {

	public MontoIncorrecto() {
		super("El monto debe ser superior a cero.");
	}
	
}
