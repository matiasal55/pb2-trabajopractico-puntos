package pb2.puntos;

public class Cliente extends Usuario {
	private Integer factorPuntos = 60;

	public Cliente(String nombre, String apellido, String email, String contrasenia, Integer id, Integer puntos, Integer factorPuntos) {
		super(nombre, apellido, email, contrasenia, id,puntos);
		this.factorPuntos = factorPuntos;
	}

	public Integer getFactorPuntos() {
		return factorPuntos;
	}

	public void setFactorPuntos(Integer factorPuntos) {
		this.factorPuntos = factorPuntos;
	}

}
