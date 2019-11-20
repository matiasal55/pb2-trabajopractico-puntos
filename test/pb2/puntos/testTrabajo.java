package pb2.puntos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class testTrabajo {

	Sistema miSistema;
	Usuario nuevo;
	Usuario admin;
	Producto nuevoProducto;

	@Before
	public void before() throws EmailYaRegistradoException, ProductoExistenteException, NoEsAdminException,
			LoginFallidoException, EmailIncorrectoException, ContraseniaIncorrectaException {
		miSistema = new Sistema();
		nuevo = new Cliente("Cosme", "Fulanito", "hotmail.com", "1234A");
		admin = new Administrador("Matias", "Alarcon", "gmail.com", "1234A");
		nuevoProducto = new Producto("Chocolate", 123, "Blanco", 21.0, 100);
		miSistema.registrarUsuario(admin);
		miSistema.loginUsuario(admin.getEmail(), admin.getContrasenia());
		miSistema.agregarProducto(nuevoProducto);
		miSistema.registrarUsuario(nuevo);
	}

	@Test
	public void comprarYpagar() throws VentaFallidaException {
		try {
			miSistema.cargarSaldo(nuevo, 200.0);
			DetallesDePago nuevoDetalle = miSistema.comprarProducto(nuevo, 1, 123, "Saldo");
			assertTrue(miSistema.pagarConSaldo(nuevoDetalle.getIdPago(), nuevoDetalle.getPrecioSaldo()));
		} catch (ProductoInexistenteException | SaldoInsuficienteException e) {
			e.printStackTrace();
		} catch (CompraFallidaException e) {
			e.printStackTrace();
		} catch (LaRecargaHaFalladoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cargarSaldo() {
		try {
			Assert.assertTrue(miSistema.cargarSaldo(nuevo, 200.0));
		} catch (LaRecargaHaFalladoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void anularCompraPorID() {

		try {
			miSistema.cargarSaldo(nuevo, 200.0);
			DetallesDePago nuevoDetalle = miSistema.comprarProducto(nuevo, 1, 123, "Saldo");
			miSistema.pagarConSaldo(nuevoDetalle.getIdPago(), nuevoDetalle.getPrecioSaldo());
			Assert.assertTrue(miSistema.anularCompra(1));
		} catch (ProductoInexistenteException e) {
			e.printStackTrace();
		} catch (SaldoInsuficienteException e) {
			e.printStackTrace();
		} catch (VentaFallidaException e) {
			e.printStackTrace();
		} catch (LaRecargaHaFalladoException e) {
			e.printStackTrace();
		} catch (CompraFallidaException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void calcularPuntos() {

		DetallesDePago nuevoDetalle;
		try {
			miSistema.cargarSaldo(nuevo, 100.0);
			nuevoDetalle = miSistema.comprarProducto(nuevo, 1, 123, "Saldo");
			miSistema.pagarConSaldo(nuevoDetalle.getIdPago(), nuevoDetalle.getPrecioSaldo());
		} catch (ProductoInexistenteException | SaldoInsuficienteException e) {
			e.printStackTrace();
		} catch (VentaFallidaException e) {
			e.printStackTrace();
		} catch (LaRecargaHaFalladoException e) {
			e.printStackTrace();
		} catch (CompraFallidaException e) {
			e.printStackTrace();
		}
		Integer valorEsperado = 60;
		Integer valorObtenido = nuevo.getPuntosAcumulados();
		assertEquals(valorEsperado, valorObtenido);
	}

	@Test(expected = SaldoInsuficienteException.class)
	public void testQueAnuleCompraSinReintegro()
			throws ProductoInexistenteException, SaldoInsuficienteException, VentaFallidaException {
		DetallesDePago nuevoDetalle;
		try {
			nuevoDetalle = miSistema.comprarProducto(nuevo, 1, 123, "Saldo");
			miSistema.pagarConSaldo(nuevoDetalle.getIdPago(), nuevoDetalle.getPrecioSaldo());
			assertFalse(miSistema.anularCompra(nuevoDetalle.getIdPago()));
		} catch (CompraFallidaException e) {
			e.printStackTrace();
		}
	}
}
