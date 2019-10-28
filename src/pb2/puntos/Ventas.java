package pb2.puntos;

import java.util.ArrayList;

public class Ventas {
	
	private Cliente cliente;
	private Integer cantidad;
	private Integer numeroDeOrden;
	private Integer cantidadDePuntos;
	private MediosDePago mediosDePago;
	
	private ArrayList <Producto> listaDeProductosVendidos =new ArrayList <Producto>();
	
	public Ventas(Cliente cliente, Integer cantidad, Integer numeroDeOrden, Integer cantidadDePuntos,
			MediosDePago mediosDePago) {
		this.cliente = cliente;
		this.cantidad = cantidad;
		this.numeroDeOrden = numeroDeOrden;
		this.cantidadDePuntos = cantidadDePuntos;
		this.mediosDePago = mediosDePago;
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

	public MediosDePago getMediosDePago() {
		return mediosDePago;
	}

	public void setMediosDePago(MediosDePago mediosDePago) {
		this.mediosDePago = mediosDePago;
	}
	
}
