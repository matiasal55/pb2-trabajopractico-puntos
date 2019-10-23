package pb2.puntos;

public class Ventas {
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

}
