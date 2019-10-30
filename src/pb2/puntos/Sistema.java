package pb2.puntos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.commons.codec.digest.DigestUtils;

public class Sistema {
	private ArrayList<Producto> listaDeProductos;
	protected ArrayList<Ventas> listaDeVentas;
	private LinkedList<Usuario> listaDeUsuarios;

	public Sistema() {
		this.listaDeProductos = new ArrayList<>();
		this.listaDeVentas = new ArrayList<>();
		this.listaDeUsuarios = new LinkedList<>();
	}
	
	public Boolean registrarUsuario(Usuario usuario) {
		return true;
	}
	
	public Boolean loginUsuario(String email, String contrasenia) {
		return false;
	}
	
	public Boolean comprarProducto(Usuario comprador, Integer cantidad, Producto producto, String medioDePago) {
		return false;
	}
	
	public Boolean pagarCompra() {
		return false;
	}
	
	public Boolean cargarSaldo(Usuario comprador, Double monto) {
			for(Usuario lista: this.listaDeUsuarios) {
				if(lista.equals(comprador)) {
					Double saldoAnterior=lista.getSaldo();
					Double saldoNuevo=saldoAnterior+monto;
					lista.setSaldo(saldoNuevo);
					return true;
				}
			}
		return false;
	}
	
	public Boolean agregarProducto(Producto nuevo) {
		for(Producto lista: this.listaDeProductos) {
			if(lista.equals(nuevo))
				return false;
		}
		this.listaDeProductos.add(nuevo);
		return true;
	}
	
	public Boolean anularCompra(Integer id) {
		Iterator<Ventas> it=this.listaDeVentas.iterator();
		while(it.hasNext()) {
			Ventas aux=it.next();
			if(aux.getIdVenta().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

}
