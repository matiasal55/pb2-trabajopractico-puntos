package pb2.puntos;

public class Usuario {
	private String mail;
	private String contrasenia;

	public Usuario(String mail, String contrasenia) {
		this.mail = mail;
		this.contrasenia = contrasenia;
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

}
