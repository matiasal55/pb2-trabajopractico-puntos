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
	
	public abstract Boolean comprarProducto(Producto producto);

	public abstract Boolean pagarProducto(Compras compra, String medioDePago);

}
