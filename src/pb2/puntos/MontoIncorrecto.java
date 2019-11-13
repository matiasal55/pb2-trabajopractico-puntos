package pb2.puntos;

public class MontoIncorrecto extends Exception {

	public MontoIncorrecto() {
		super("El monto a agregar debe ser mayor a 0");
	}
}
