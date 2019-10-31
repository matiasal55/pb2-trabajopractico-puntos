package pb2.puntos;

public class Cliente extends Usuario {
	private Integer factorPuntos = 60;

public Cliente(String nombre, String apellido, String mail, String contrasenia, Integer factorDePuntos) {
		super(nombre, apellido, mail, contrasenia);
	}

	public Integer getFactorPuntos() {
		return factorPuntos;
	}

	public void setFactorPuntos(Integer factorPuntos) {
		this.factorPuntos = factorPuntos;
	}

}
