package pb2.puntos;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class testABM {

	Usuario nuevo;
	Usuario admin;
	
	Sistema sistema = new Sistema();
	Administrador a1 = new Administrador ("Azul", "Paez", "azulp@gmail.com", "85200");
	Cliente u1 = new Cliente ("Miguel", "Lopez", "mlopez@gmail.com", "12345");
	Cliente u2 = new Cliente ("Ana", "Rosas", "anaro@gmail.com", "54321");
	Producto nuevoProducto = new Producto("Chocolate", 123, "Blanco", 21.0, 100);
	Producto nuevoProducto2 = new Producto("Chocolate", 124, "Negro", 21.0, 100);
	
	@Test
	public void testRegistroExitoso() {
		Boolean valorEsperado;
		try {
			valorEsperado = sistema.registrarUsuario(u1);
			Assert.assertTrue(valorEsperado);
		} catch (EmailYaRegistradoException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = EmailYaRegistradoException.class)
	public void testRegistroFallido() throws EmailYaRegistradoException {
		sistema.registrarUsuario(u1);
		sistema.registrarUsuario(u1);
	}
	
	@Test
	public void testLoginExistoso () {
		try {
			sistema.registrarUsuario(u1);
			Boolean valorEsperado = sistema.loginUsuario("mlopez@gmail.com", "12345");
			Assert.assertTrue(valorEsperado);
		} catch (EmailYaRegistradoException e) {
			e.printStackTrace();
		} catch (LoginFallidoException e) {
			e.printStackTrace();
		} catch (EmailIncorrectoException e) {
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = LoginFallidoException.class)
	public void testLoginFallido() throws LoginFallidoException, EmailIncorrectoException, ContraseniaIncorrectaException, EmailYaRegistradoException {
		sistema.registrarUsuario(u1);
		try {
			sistema.loginUsuario("mlopes@gmail.com", "12347");
		} catch (EmailIncorrectoException e) {
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void eliminarUsuarioExitoso() throws EmailYaRegistradoException, NoEsAdminException, UsuarioInexistenteException {
		sistema.registrarUsuario(u1);
		try { 
			Boolean valorEsperado = sistema.eliminarUsuario("mlopez@gmail.com");
			Assert.assertTrue(valorEsperado);
		} catch (NoEsAdminException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NoEsAdminException.class)
	public void eliminarUsuarioFallido() throws EmailYaRegistradoException, NoEsAdminException, UsuarioInexistenteException {
		sistema.registrarUsuario(u1);
		sistema.eliminarUsuario("mlopez@gmail.com");
	}
	
	@Test
	public void agregarProductoExitoso() throws NoEsAdminException, ProductoExistenteException {
		try {
			Boolean valorEsperado = sistema.agregarProducto(nuevoProducto);
			Assert.assertTrue(valorEsperado);
			sistema.agregarProducto(nuevoProducto);
		} catch (NoEsAdminException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NoEsAdminException.class)
	public void agregarProductoFallido() throws NoEsAdminException, ProductoExistenteException {
		sistema.agregarProducto(nuevoProducto);
	}
	
	@Test
	public void eliminarProductoExitoso() throws ProductoInexistenteException {
		try {
			sistema.agregarProducto(nuevoProducto);
			Assert.assertTrue(sistema.eliminarProducto(nuevoProducto.getCodigo()));
		} catch (ProductoExistenteException e) {
			e.printStackTrace();
		} catch (NoEsAdminException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NoEsAdminException.class)
	public void eliminarProductoFallido() throws NoEsAdminException, ProductoExistenteException {
		sistema.agregarProducto(nuevoProducto);
	}
	
	@Test
	public void modificarProductoExitoso() throws ProductoInexistenteException, ProductoExistenteException {
		try {
			sistema.agregarProducto(nuevoProducto);
			sistema.modificarProducto(nuevoProducto, nuevoProducto2);
			Assert.assertTrue(sistema.eliminarProducto(nuevoProducto.getCodigo()));
		} catch (NoEsAdminException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NoEsAdminException.class)
	public void modificarProductoFallido() throws NoEsAdminException, ProductoExistenteException, ProductoInexistenteException {
		try {
			sistema.agregarProducto(nuevoProducto);
		} catch (NoEsAdminException e) {
			e.printStackTrace();
		sistema.modificarProducto(nuevoProducto, nuevoProducto2);
		}
	}
}