package pb2.puntos;

public abstract class Usuario extends Persona {

	private String email;
	private String contrasenia;
	private Integer id;
	private Integer puntosAcumulados = 0;

	public Usuario(String nombre, String apellido, String email, String contrasenia, Integer id,
			Integer puntosAcumulados) {
		super(nombre, apellido);
		this.email = email;
		this.contrasenia = contrasenia;
		this.id = id;
		this.puntosAcumulados = 0;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((contrasenia == null) ? 0 : contrasenia.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((puntosAcumulados == null) ? 0 : puntosAcumulados.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (contrasenia == null) {
			if (other.contrasenia != null)
				return false;
		} else if (!contrasenia.equals(other.contrasenia))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (puntosAcumulados == null) {
			if (other.puntosAcumulados != null)
				return false;
		} else if (!puntosAcumulados.equals(other.puntosAcumulados))
			return false;
		return true;
	}

}
