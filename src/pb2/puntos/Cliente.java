package pb2.puntos;

import java.util.ArrayList;

public class Cliente extends Usuario{
	private ArrayList<Compras> listaDeCompras;
	private Integer factorDePuntos;
	public Cliente(String nombre, String apellido, String mail, String contrasenia, Integer factorDePuntos) {
		super(nombre, apellido, mail, contrasenia);
		this.factorDePuntos = factorDePuntos;
		this.listaDeCompras=new ArrayList<>();
	}
	
}
