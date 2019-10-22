package pb2.puntos;

public class Persona extends Sistema{
	private String nombre;
	private String apellido;
	private Sistema miSistema;

	public Persona(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.miSistema=new Sistema();
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
