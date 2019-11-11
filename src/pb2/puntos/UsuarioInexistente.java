package pb2.puntos;

public class UsuarioInexistente extends Exception {

	public UsuarioInexistente() {
		super("El usuario no existe");
	}
}
