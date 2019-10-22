package pb2.puntos;

public abstract class Usuario extends Persona {
	private String mail;
	private String contrasenia;
	private Integer puntos;
	private Integer id;

	public Usuario(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido);
		this.mail = mail;
		this.contrasenia = contrasenia;
		this.puntos = 0;
		this.id = 0;
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

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public abstract Boolean comprarProducto(Producto producto);

	public abstract Boolean pagarProducto(Compras compra, String medioDePago, Usuario usuario);

}
