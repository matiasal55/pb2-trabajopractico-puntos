package pb2.puntos;

public class Usuario extends Persona {

	private String email;
	private String contrasenia;
	private Integer id;
	private Integer puntosAcumulados = 0;
	private Double saldo;

	public Usuario(String nombre, String apellido, String email, String contrasenia) {
		super(nombre, apellido);
		this.email = email;
		this.contrasenia = contrasenia;
		this.id = 0;
		this.puntosAcumulados = 0;
		this.saldo = 0.0;
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
}
