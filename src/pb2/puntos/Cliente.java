package pb2.puntos;

public class Cliente extends Usuario {

	public Cliente(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido, mail, contrasenia);
		this.factorDePuntos = 60;
	}

}
