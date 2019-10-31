package pb2.puntos;

public class Administrador extends Usuario {
	private Integer factorPuntos = 120;
  
  public Administrador(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido, mail, contrasenia);
	}

	public Integer getFactorPuntos() {
		return factorPuntos;
	}

	public void setFactorPuntos(Integer factorPuntos) {
		this.factorPuntos = factorPuntos;
	}
}
