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
	public void before() throws usuarioExistenteException {
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
		DetallesDePago nuevoDetalle=miSistema.comprarProducto(nuevo.getMail(), 1, nuevoProducto, "Saldo");
		try {
			assertTrue(miSistema.pagarConSaldo(nuevoDetalle.getIdPago(), nuevoDetalle.getPrecioSaldo()));
		} catch (productoInexistenteException | saldoInsuficienteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cargarSaldo() {
		Assert.assertTrue(miSistema.cargarSaldo(nuevo, 200.0));
	}

	@Test
	public void anularCompraPorID() {
		miSistema.cargarSaldo(nuevo, 200.0);
		DetallesDePago nuevoDetalle=miSistema.comprarProducto(nuevo.getMail(), 1, nuevoProducto, "Saldo");
		try {
			miSistema.pagarConSaldo(nuevoDetalle.getIdPago(), nuevoDetalle.getPrecioSaldo());
			Assert.assertTrue(miSistema.anularCompra(1));
		} catch (productoInexistenteException e) {
			e.printStackTrace();
		} catch (saldoInsuficienteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void calcularPuntos() {
		miSistema.cargarSaldo(nuevo, 100.0);
		DetallesDePago nuevoDetalle=miSistema.comprarProducto(nuevo.getMail(), 1, nuevoProducto, "Saldo");
		try {
			miSistema.pagarConSaldo(nuevoDetalle.getIdPago(), nuevoDetalle.getPrecioSaldo());
			Integer valorEsperado=1260;
			Integer valorObtenido=nuevo.getPuntosAcumulados();
			assertEquals(valorEsperado, valorObtenido);
		} catch (productoInexistenteException | saldoInsuficienteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pagarConPuntosAcumulados() {
		miSistema.cargarSaldo(nuevo, 200.0);
		DetallesDePago nuevoDetalle=miSistema.comprarProducto(nuevo.getMail(), 1, nuevoProducto, "Saldo");
		try {
			miSistema.pagarConSaldo(nuevoDetalle.getIdPago(), nuevoDetalle.getPrecioSaldo());
			DetallesDePago nuevoDetalle2=miSistema.comprarProducto(nuevo.getMail(), 1, nuevoProducto, "Puntos");	
			assertTrue(miSistema.pagarConPuntos(nuevoDetalle2.getIdPago(), nuevoDetalle2.getPrecioPuntos()));
		} catch (productoInexistenteException | saldoInsuficienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test (expected=saldoInsuficienteException.class)
	public void testQueAnuleCompraSinReintegro() throws productoInexistenteException, saldoInsuficienteException {
		DetallesDePago nuevoDetalle=miSistema.comprarProducto(nuevo.getMail(), 1, nuevoProducto, "Saldo");
		miSistema.pagarConSaldo(nuevoDetalle.getIdPago(), nuevoDetalle.getPrecioSaldo());
		assertFalse(miSistema.anularCompra(nuevoDetalle.getIdPago()));
	}

}
