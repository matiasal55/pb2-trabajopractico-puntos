package pb2.puntos;

public abstract class Usuario extends Persona {
	private String mail;
	private String contrasenia;

	public Usuario(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido);
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

	public Boolean loginUsuario(String mail, String contrasenia) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(mail) && lista.getContrasenia().equals(contrasenia))
				return true;
		}
		return false;
	}
	
	public Boolean registrarUsuario(Usuario usuario) {
		for (Usuario lista : this.listaDeUsuarios) {
			if (lista.getMail().equals(mail))
				return false;
		}
		this.listaDeUsuarios.add(usuario);
		return true;
	}

}
