package pb2.puntos;

public class Cliente extends Usuario {

	public Cliente(String nombre, String apellido, String mail, String contrasenia) {
		super(nombre, apellido, mail, contrasenia);
		this.factorDePuntos=60;
	}
	
	public Boolean comprarProducto(Producto producto) {
		for (Producto lista : this.listaDeProductos) {
			if (lista.equals(producto)) {
				Integer cantidadDePuntos = (int) (producto.getPrecioReal() * this.factorDePuntos);
				Integer numeroDeOrden = this.listaDeCompras.size() + 1;
				Compras nuevaCompra = new Compras(producto.getDescripcion(), producto.getCodigo(), producto.getNombre(),
						producto.getPrecioReal(), producto.getPrecioPuntos(), numeroDeOrden, cantidadDePuntos);
				this.listaDeCompras.add(nuevaCompra);
				return true;
			}
		}
		return false;
	}

}
