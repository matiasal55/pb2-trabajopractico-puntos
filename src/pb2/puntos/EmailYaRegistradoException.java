package pb2.puntos;

public class EmailYaRegistradoException extends Exception {

	public EmailYaRegistradoException() {
		super("El email ya esta en uso.");
	}
	
}
