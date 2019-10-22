package pb2.puntos;

public class Ventas{
	private Usuario usuario;
	private Compras compra;

	public Ventas(Usuario usuario, Compras compra) {
		this.usuario = usuario;
		this.compra = compra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Compras getCompra() {
		return compra;
	}

	public void setCompra(Compras compra) {
		this.compra = compra;
	}

	public Boolean procesarVenta(Ventas venta, String medioDePago) {
		if(medioDePago.equals("Puntos")) {
			Integer puntosAnteriores=venta.getUsuario().getPuntos();
			Integer puntosADescontar=venta.getCompra().getPrecioPuntos();
			if(puntosAnteriores>=puntosADescontar) {
				venta.getUsuario().setPuntos(puntosAnteriores-puntosADescontar);
				venta.getCompra().setPrecioReal(0.0);
				return true;
			}
			else
				return false;
		}
		else {
			venta.getCompra().setPrecioPuntos(0);
			return true;
		}
	}
}
