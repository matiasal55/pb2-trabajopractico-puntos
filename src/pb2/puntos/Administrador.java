package pb2.puntos;

public class Administrador extends Usuario {
	
	private Integer factorDePuntos = 60;

	public Administrador(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido, mail, contrasenia);
	}
	
	
}
