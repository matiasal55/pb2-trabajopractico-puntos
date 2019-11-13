package pb2.puntos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class testTrabajo {
	
	Sistema sistema = new Sistema();
	Usuario u1 = new Usuario ("Miguel", "Lopez", "mlopez@gmail.com", "12345");
	
	@Test
	public void testRegistroExitoso() throws UsuarioYaRegistradoException {
		Boolean valorEsperado = sistema.registrarUsuario(u1);
		assertTrue(valorEsperado);
	}
	
	@Test (expected = UsuarioYaRegistradoException.class)
	public void testRegistroFallido() throws UsuarioYaRegistradoException {
		sistema.registrarUsuario(u1);
		sistema.registrarUsuario(u1);
	}
	
	@Test
	public void testLoginExistoso () throws UsuarioYaRegistradoException, LoginFallidoException {
		sistema.registrarUsuario(u1);
		Boolean valorEsperado = sistema.loginUsuario("mlopez@gmail.com", "12345");
		assertTrue(valorEsperado);
	}
	

	@Test (expected = LoginFallidoException.class)
	public void testLoginFallido() throws UsuarioYaRegistradoException, LoginFallidoException {
		sistema.registrarUsuario(u1);
		sistema.loginUsuario("slopez@gmail.com", "14345");
	}

}
