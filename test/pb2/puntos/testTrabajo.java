package pb2.puntos;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;

public class testTrabajo {

	Usuario u1 = new Cliente("nombreCliente", "apCliente", "cliente@gmail.com", "contraCliente", 2, 10, 50.0);
	Usuario a1 = new Administrador("nombreAdmin", "apAdmin", "admin@gmail.com", "contraAdmin", 1, 50, 500.0);
	Producto p1 = new Producto("lacteos", 11012, "leche", 50.0, 250);
	Producto p2 = new Producto("lacteos", 11013, "queso", 75.0, 310);
	Producto p3 = new Producto("lacteos", 11014, "pan", 150.0, 500);
	Sistema s1 = Sistema.getInstancia();

	@Test
	public void realizarCompra() throws NoEsAdminException, VentaFallidaException, MetodoDePagoNoExistenteException,
			EmailYaRegistrado, ElUsuarioNoEstaRegistradoException, EfectivoInsuficienteException {
		Ventas v1 = new Ventas(01, (Cliente) u1, 5, p1, "efectivo", 50);
		s1.agregarProducto(a1, p1);
		assertTrue(s1.agregarProducto(a1, p1));
		s1.registrarUsuario(u1);
		s1.mostarListaUsuarios();
		s1.realizarCompra(v1);
		s1.obtenerComprasOrdenadasPorId();
		s1.pagarEnEfectivo(v1);
		//s1.eliminarUsuario(a1, u1);
		s1.mostarListaUsuarios();
	}

}