package pb2.puntos;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
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
		Boolean valorEsperado;
		try {
			valorEsperado = sistema.registrarUsuario(u1);
			assertTrue(valorEsperado);
		} catch (EmailYaRegistradoException e) {
			// TODO Auto-generated catch block
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
			assertTrue(valorEsperado);
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
		} catch (NoEsAdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test (expected = LoginFallidoException.class)
	public void testLoginFallido() throws LoginFallidoException, EmailIncorrectoException, ContraseniaIncorrectaException {
		sistema.loginUsuario("slopez@gmail.com", "14345");
	}
	
	@Test (expected = ContraseniaIncorrectaException.class)
	public void testLoginContraseniaInvalida () throws EmailYaRegistradoException, LoginFallidoException, EmailIncorrectoException, ContraseniaIncorrectaException  {
		sistema.registrarUsuario(u1);
		sistema.loginUsuario("mlopez@gmail.com", "12346");
	}
	
	
	
}
