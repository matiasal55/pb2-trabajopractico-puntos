package pb2.puntos;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class testABM {
	
	Sistema miSistema;
	Usuario nuevo;
	Usuario admin;
	Producto nuevoProducto;
	
	Sistema sistema = new Sistema();
	Usuario u1 = new Usuario ("Miguel", "Lopez", "mlopez@gmail.com", "12345");
	Usuario u2 = new Usuario ("Ana", "Rosas", "anaro@gmail.com", "54321");
	
	@Test
	public void testRegistroExitoso() throws UsuarioExistenteException {
		Boolean valorEsperado = sistema.registrarUsuario(u1);
		assertTrue(valorEsperado);
	}
	
	@Test (expected = UsuarioExistenteException.class)
	public void testRegistroFallido() throws UsuarioExistenteException {
		sistema.registrarUsuario(u1);
		sistema.registrarUsuario(u1);
	}
	
	@Test
	public void testLoginExistoso () throws UsuarioExistenteException, LoginFallidoException, ContraseniaInvalidaException {
		sistema.registrarUsuario(u1);
		Boolean valorEsperado = sistema.loginUsuario("mlopez@gmail.com", "12345");
		assertTrue(valorEsperado);
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
		
	@Test (expected = LoginFallidoException.class)
	public void testLoginFallido() throws LoginFallidoException, UsuarioExistenteException, ContraseniaInvalidaException {
		sistema.loginUsuario("slopez@gmail.com", "14345");
	}
	
	@Test (expected = ContraseniaInvalidaException.class)
	public void testLoginContraseniaInvalida () throws LoginFallidoException, UsuarioExistenteException, ContraseniaInvalidaException {
		sistema.registrarUsuario(u1);
		sistema.loginUsuario("mlopez@gmail.com", "12346");
	}
	
	
	
}
