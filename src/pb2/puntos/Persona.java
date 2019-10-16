package pb2.puntos;

public class Persona extends Usuario{
	private String nombre;
	private String apellido;
	public Persona(String mail, String contrasenia, String nombre, String apellido) {
		super(mail, contrasenia);
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
}
