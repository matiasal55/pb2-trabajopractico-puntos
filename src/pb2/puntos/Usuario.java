package pb2.puntos;

public class Usuario extends Persona {
	private String mail;
	private String contrasenia;
	private Integer id;
	private Integer puntosAcumulados;
	private Double saldo;

	public Usuario(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido);
		this.mail = mail;
		this.contrasenia = contrasenia;
		this.id = 0;
		this.puntosAcumulados = 0;
		this.saldo = 0.0;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
