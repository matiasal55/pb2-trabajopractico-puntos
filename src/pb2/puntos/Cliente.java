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
			if (lista.equals(producto)) {
				Random generador = new Random(System.currentTimeMillis());
				Integer numeroDeOrden = generador.nextInt(1000);
				Integer cantidadDePuntos = (int) (producto.getPrecioReal() * this.factorDePuntos);
				Compras nuevaCompra = new Compras(producto.getDescripcion(), producto.getCodigo(), producto.getNombre(),
						producto.getPrecioReal(), producto.getPrecioPuntos(), numeroDeOrden, cantidadDePuntos);
				return true;
			}
		}
		return false;
	}
	
	public Boolean pagarProducto (Compras compra, String medioDePago, Usuario usuario) {
		for(Usuario lista: this.listaDeUsuarios) {
			if(lista.equals(usuario)) {
				Ventas nueva=new Ventas(usuario, compra);
				if(nueva.procesarVenta(nueva,medioDePago)) {
					this.listaDeCompras.add(compra);
					this.listaDeVentas.add(nueva);
					return true;					
				}
			}
		}
		return false;
	}

}
