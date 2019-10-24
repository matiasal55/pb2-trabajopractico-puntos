package pb2.puntos;

import java.util.ArrayList;

public class Ventas {
	private Cliente cliente;
	private Producto producto;
	private Integer cantidad;
	private Integer numeroDeOrden;
	private Integer cantidadDePuntos;
	private MediosDePagos medio;

	public Ventas(Cliente cliente, Producto producto, Integer cantidad, Integer numeroDeOrden, Integer cantidadDePuntos,
			MediosDePagos medio) {
		this.cliente = cliente;
		this.producto = producto;
		this.cantidad = cantidad;
		this.numeroDeOrden = numeroDeOrden;
		this.cantidadDePuntos = cantidadDePuntos;
		this.medio = medio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getNumeroDeOrden() {
		return numeroDeOrden;
	}

	public void setNumeroDeOrden(Integer numeroDeOrden) {
		this.numeroDeOrden = numeroDeOrden;
	}

	public Integer getCantidadDePuntos() {
		return cantidadDePuntos;
	}

	public void setCantidadDePuntos(Integer cantidadDePuntos) {
		this.cantidadDePuntos = cantidadDePuntos;
	}

	public MediosDePagos getMedio() {
		return medio;
	}

	public void setMedio(MediosDePagos medio) {
		this.medio = medio;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
