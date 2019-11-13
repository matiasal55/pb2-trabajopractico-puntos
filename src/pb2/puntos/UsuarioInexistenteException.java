package pb2.puntos;

public class UsuarioInexistenteException extends Exception {

	public UsuarioInexistenteException() {
		super("El usuario no existe");
	}
}
