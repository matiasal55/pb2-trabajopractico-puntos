package pb2.puntos;

public abstract class Usuario extends Persona {

	private String email;
	private String contrasenia;
	private Integer id;
	private Integer puntosAcumulados = 0;
	private Double saldo = 0.0;

	public Usuario(String nombre, String apellido, String email, String contrasenia, Integer id,
			Integer puntosAcumulados, Double saldo) {
		super(nombre, apellido);
		this.email = email;
		this.contrasenia = contrasenia;
		this.id = id;
		this.puntosAcumulados = 0;
		this.saldo = saldo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPuntosAcumulados() {
		return puntosAcumulados;
	}

	public void setPuntosAcumulados(Integer puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", contrasenia=" + contrasenia + ", id=" + id + ", puntosAcumulados="
				+ puntosAcumulados + ", saldo=" + saldo + "]";
	}

}
