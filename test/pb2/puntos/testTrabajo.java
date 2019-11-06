package pb2.puntos;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;

public class testTrabajo {

	Usuario u1 = new Cliente("nombreCliente", "apCliente", "cliente@gmail.com", "contraCliente", 2, 10, 200.0);
	Usuario a1 = new Administrador("nombreAdmin", "apAdmin", "admin@gmail.com", "contraAdmin", 1, 50, 500.0);
	Producto p1 = new Producto("lacteos", 11012, "leche", null, null);
	Sistema s1 = new Sistema();

	@Test
	public void realizarCompra() throws NoEsAdminException {
		Ventas v1 = new Ventas(01, (Cliente) u1, 5, p1, "efectivo", 50);
		s1.agregarProducto(a1, p1);
		assertTrue(s1.agregarProducto(a1, p1));
	}

}