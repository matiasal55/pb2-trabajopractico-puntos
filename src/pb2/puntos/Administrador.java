package pb2.puntos;

import java.util.Iterator;
import java.util.LinkedList;

public class Administrador extends Usuario {
	private Integer factorPuntos = 120;
  
  public Administrador(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido, mail, contrasenia);
	}

	public Integer getFactorPuntos() {
		return factorPuntos;
	}

	public void setFactorPuntos(Integer factorPuntos) {
		this.factorPuntos = factorPuntos;
	}
}
