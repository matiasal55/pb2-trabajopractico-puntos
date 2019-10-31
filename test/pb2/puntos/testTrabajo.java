package pb2.puntos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class testTrabajo {

	Sistema miSistema;
	Usuario nuevo;
	Usuario admin;
	Producto nuevoProducto;

	@Before
	public void before() {
		miSistema = new Sistema();
		nuevo = new Cliente("Cosme", "Fulanito", "hotmail.com", "1234A");
		admin = new Administrador("Matias", "Alarcon", "gmail.com", "1234A");
		nuevoProducto = new Producto("Chocolate", 123, "Blanco", 21.0, 100);
		miSistema.registrarUsuario(nuevo);
		miSistema.registrarUsuario(admin);
		miSistema.agregarProducto(nuevoProducto);
	}

	@Test
	public void comprarYpagar() {
		miSistema.cargarSaldo(nuevo, 200.0);
		Assert.assertTrue(miSistema.comprarProducto(nuevo, 1, nuevoProducto, "Saldo"));
	}

	@Test
	public void cargarSaldo() {
		Assert.assertTrue(miSistema.cargarSaldo(nuevo, 200.0));
	}

	@Test
	public void anularCompraPorID() {
		miSistema.cargarSaldo(nuevo, 200.0);
		miSistema.comprarProducto(nuevo, 1, nuevoProducto, "Saldo");
		Assert.assertTrue(miSistema.anularCompra(1));
	}

}
