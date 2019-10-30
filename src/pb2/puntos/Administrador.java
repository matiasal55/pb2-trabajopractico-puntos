package pb2.puntos;

import java.util.Iterator;
import java.util.LinkedList;

public class Administrador extends Usuario {
	private Integer factorPuntos = 120;

	public Administrador(String nombre, String apellido, String email, String contrasenia, Integer id, Integer puntos, Integer factorPuntos) {
		super(nombre, apellido, email, contrasenia, id, puntos);
		this.factorPuntos = factorPuntos;
	}

	public Integer getFactorPuntos() {
		return factorPuntos;
	}

	public void setFactorPuntos(Integer factorPuntos) {
		this.factorPuntos = factorPuntos;
	}
	
	
		
}
