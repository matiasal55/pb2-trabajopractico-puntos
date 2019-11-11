package pb2.puntos;

public class MontoInsuficiente extends Exception {

	public MontoInsuficiente() {
		super("El monto a agregar debe ser mayor a 0");
	}
}
