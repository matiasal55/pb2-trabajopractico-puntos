package pb2.puntos;

import java.util.LinkedList;

public class Sistema {
	LinkedList<Cliente> clientes = new LinkedList();
	LinkedList<Compra> compras = new LinkedList();

	public Boolean registrar(Cliente cliente) {
		for (Cliente client : clientes) {
			if (client.getNombre().equals(cliente.getNombre()) && client.getApellido().equals(cliente.getApellido())) {
				return false;
			}
		}
		clientes.add(cliente);
		return true;
	}

}
