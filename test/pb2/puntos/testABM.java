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
	public void loginConUsuarioIncorrecto() throws LoginFallidoException {
		Boolean valor = miSistema.loginUsuario(nuevo.getMail(), nuevo.getContrasenia());
		Assert.assertFalse(valor);
	}

	@Test
	public void crearClienteYLoguearlo() throws LoginFallidoException {
		Assert.assertTrue(miSistema.registrarUsuario(nuevo));
		Assert.assertTrue(miSistema.loginUsuario(nuevo.getMail(), nuevo.getContrasenia()));
	}

	@Test
	public void usuarioExistente() throws LoginFallidoException {
		miSistema.registrarUsuario(nuevo);
		miSistema.loginUsuario(nuevo.getMail(), nuevo.getContrasenia());
		Assert.assertFalse(miSistema.registrarUsuario(nuevo));
	}
	
	@Test
	public void crearAdministradorYLoguearlo() throws LoginFallidoException {
		miSistema.registrarUsuario(admin);
		Assert.assertTrue(miSistema.loginUsuario(admin.getMail(), admin.getContrasenia()));
	}

	@Test
	public void crearProducto() {
		Assert.assertTrue(miSistema.agregarProducto(nuevoProducto));
	}
	
	@Test
	public void eliminarProducto() {
		miSistema.agregarProducto(nuevoProducto);
		Assert.assertTrue(miSistema.eliminarProducto(nuevoProducto.getCodigo()));
	}
}
