package pb2.puntos;

public class UsuarioYaRegistradoException extends Exception {

	public UsuarioYaRegistradoException() {
		super("El usuario ya existe.");
	}
	
}
