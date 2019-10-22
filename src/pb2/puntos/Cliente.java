package pb2.puntos;

import java.util.ArrayList;
import java.util.Random;

public class Cliente extends Usuario {
	private ArrayList<Compras> listaDeCompras;
	private Integer factorDePuntos;

	public Cliente(String nombre, String apellido, String mail, String contrasenia, Integer factorDePuntos) {
		super(nombre, apellido, mail, contrasenia);
		this.factorDePuntos = factorDePuntos;
		this.listaDeCompras = new ArrayList<>();
	}

	public Boolean comprarProducto(Producto producto) {
		for (Producto lista : this.listaDeProductos) {
			Random generador = new Random(System.currentTimeMillis());
			Integer numeroDeOrden = generador.nextInt(1000);
			Integer cantidadDePuntos = (int) (producto.getPrecioReal() * this.factorDePuntos);
			Compras nueva = new Compras(producto.getDescripcion(), producto.getCodigo(), producto.getNombre(),
					producto.getPrecioReal(), producto.getPrecioPuntos(), numeroDeOrden, cantidadDePuntos);
		}
		return false;
	}

}
