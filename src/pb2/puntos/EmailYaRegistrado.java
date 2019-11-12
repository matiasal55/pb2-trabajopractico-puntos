package pb2.puntos;

public class EmailYaRegistrado extends Exception {

	public EmailYaRegistrado() {
		super("Este email ya esta en uso.");
	}
	
}
