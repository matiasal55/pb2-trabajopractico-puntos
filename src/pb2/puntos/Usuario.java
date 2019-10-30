package pb2.puntos;

public abstract class Usuario extends Persona {

	private String email;
	private String contrasenia;
	private Integer id;
	private Integer puntos = 0;
	
	public Usuario(String nombre, String apellido, String email, String contrasenia, Integer id, Integer puntos2) {
		super(nombre, apellido);
		this.email = email;
		this.contrasenia = contrasenia;
		this.id = id;
		this.puntos = puntos2;
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

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

}
