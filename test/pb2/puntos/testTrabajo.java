package pb2.puntos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class testTrabajo {
	
	Sistema sistema = new Sistema();
	Usuario u1 = new Usuario ("Miguel", "Lopez", "mlopez@gmail.com", "12345");
	
	@Test
	public void testRegistroExitoso() throws UsuarioYaRegistrado {
		Boolean valorEsperado = sistema.registrarUsuario(u1);
		assertTrue(valorEsperado);
	}
	
	@Test (expected = UsuarioYaRegistrado.class)
	public void testRegistroFallido() throws UsuarioYaRegistrado {
		sistema.registrarUsuario(u1);
		sistema.registrarUsuario(u1);
	}
	
	@Test
	public void testLoginExistoso () throws UsuarioYaRegistrado, LoginFallidoException {
		sistema.registrarUsuario(u1);
		Boolean valorEsperado = sistema.loginUsuario("mlopez@gmail.com", "12345");
		assertTrue(valorEsperado);
	}
	

	@Test (expected = LoginFallidoException.class)
	public void testLoginFallido() throws UsuarioYaRegistrado, LoginFallidoException {
		sistema.registrarUsuario(u1);
		sistema.loginUsuario("slopez@gmail.com", "14345");
	}

}
