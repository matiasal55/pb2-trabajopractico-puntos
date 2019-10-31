package pb2.puntos;

public class UsuarioYaRegistrado extends Exception {

	public UsuarioYaRegistrado() {
		super("El usuario ya existe.");
	}
	
}
