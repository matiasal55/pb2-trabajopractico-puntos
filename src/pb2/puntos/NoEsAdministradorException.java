package pb2.puntos;

public class NoEsAdministradorException extends Exception {

	public NoEsAdministradorException() {
		super("Solo los usuarios tipo administrador pueden modificar la lista de productos");
	}
}
