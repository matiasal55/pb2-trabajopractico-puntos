package pb2.puntos;

import java.util.ArrayList;

public class Usuario extends Persona {
	private String mail;
	private String contrasenia;
	private Integer puntosAcumulados;
	private Integer id;
	protected ArrayList<Compras> listaDeCompras;

	public Usuario(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido);
		this.mail = mail;
		this.contrasenia = contrasenia;
		this.puntosAcumulados = 0;
		this.id = 0;
		this.listaDeCompras = new ArrayList<>();
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
		return puntosAcumulados;
	}

	public void setPuntos(Integer puntos) {
		this.puntosAcumulados = puntos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Compras> getListaDeCompras() {
		return listaDeCompras;
	}

	public void setListaDeCompras(ArrayList<Compras> listaDeCompras) {
		this.listaDeCompras = listaDeCompras;
	}

	@Override
	public String toString() {
		return "Usuario [mail=" + mail + ", contrasenia=" + contrasenia + "]";
	}

}
