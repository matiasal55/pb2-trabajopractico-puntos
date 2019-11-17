package pb2.puntos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class testABM {
	
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
	}
	
	@Test (expected= LoginFallidoException.class)
	public void loginConUsuarioIncorrecto() throws LoginFallidoException, contraseniaInvalidaException {
		Boolean valor = miSistema.loginUsuario(nuevo.getEmail(), nuevo.getContrasenia());
		Assert.assertFalse(valor);
	}

	@Test
	public void crearClienteYLoguearlo() throws LoginFallidoException, usuarioExistenteException, contraseniaInvalidaException {
		miSistema.registrarUsuario(nuevo);
		Assert.assertTrue(miSistema.loginUsuario(nuevo.getEmail(), nuevo.getContrasenia()));
	}

	@Test (expected=usuarioExistenteException.class)
	public void usuarioExistente() throws LoginFallidoException, usuarioExistenteException, contraseniaInvalidaException {
		miSistema.registrarUsuario(nuevo);
		miSistema.registrarUsuario(nuevo);
	}
	
	@Test
	public void crearAdministradorYLoguearlo() throws LoginFallidoException, usuarioExistenteException, contraseniaInvalidaException {
		miSistema.registrarUsuario(admin);
		Assert.assertTrue(miSistema.loginUsuario(admin.getEmail(), admin.getContrasenia()));
	}

	@Test
	public void crearProducto() {
		try {
			Assert.assertTrue(miSistema.agregarProducto(nuevoProducto));
		} catch (productoExistenteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void eliminarProducto() {
		try {
			miSistema.agregarProducto(nuevoProducto);
			Assert.assertTrue(miSistema.eliminarProducto(nuevoProducto.getCodigo()));
		} catch (productoExistenteException e) {
			e.printStackTrace();
		}
		
	}
}
