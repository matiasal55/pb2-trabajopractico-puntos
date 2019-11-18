package pb2.puntos;

public class Cliente extends Usuario {
	private Integer factorDePuntos = 60;

	public Cliente(String nombre, String apellido, String mail, String contrasenia, Integer id,
			Integer puntosAcumulados, Double saldo) {
		super(nombre, apellido, mail, contrasenia, id, puntosAcumulados, saldo);
	}

	public Integer getFactorDePuntos() {
		return factorDePuntos;
	}

	public void setFactorDePuntos(Integer factorDePuntos) {
		this.factorDePuntos = factorDePuntos;
	}

}
