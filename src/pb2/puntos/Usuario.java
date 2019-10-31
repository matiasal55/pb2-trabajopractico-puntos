package pb2.puntos;

import java.util.ArrayList;

public abstract class Usuario extends Persona{
	
	private String mail;
	private String contrasenia;
	private Integer id = 0;
	private Integer puntosAcumulados = 0;
	
	private ArrayList <Ventas> listaDeCompras = new ArrayList <Ventas>();
	

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
}
