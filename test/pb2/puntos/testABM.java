package pb2.puntos;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class testABM {

	Sistema sistema = new Sistema();
	Cliente u1 = new Cliente("Miguel", "Lopez", "mlopez@gmail.com", "12345");
	Cliente u2 = new Cliente("Ana", "Rosas", "anaro@gmail.com", "54321");
	Administrador a1 = new Administrador("Azul", "Paz", "azulp@gmail.com", "85200");
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

	@Test(expected = EmailYaRegistradoException.class)
	public void testRegistroFallido() throws EmailYaRegistradoException {
		sistema.registrarUsuario(u1);
		sistema.registrarUsuario(u1);
	}

	@Test
	public void testLoginExistoso() {
		try {
			sistema.registrarUsuario(u1);
			Boolean valorEsperado = sistema.loginUsuario(u1.getEmail(), u1.getContrasenia());
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

	@Test(expected = LoginFallidoException.class)
	public void testLoginFallido() throws LoginFallidoException, EmailIncorrectoException,
			ContraseniaIncorrectaException, EmailYaRegistradoException {
		sistema.registrarUsuario(u1);
		try {
			sistema.loginUsuario("mlopes@gmail.com", "12347");
		} catch (EmailIncorrectoException e) {
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = EmailIncorrectoException.class)
	public void testLoginFallidoEmailIncorrecto() throws LoginFallidoException, EmailIncorrectoException,
			ContraseniaIncorrectaException, EmailYaRegistradoException {
		sistema.registrarUsuario(u1);
		try {
			sistema.loginUsuario("mlopes@gmail.com", u1.getContrasenia());
		} catch (LoginFallidoException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = ContraseniaIncorrectaException.class)
	public void testLoginFallidoContrasenialIncorrecto() throws LoginFallidoException, EmailIncorrectoException,
			ContraseniaIncorrectaException, EmailYaRegistradoException {
		sistema.registrarUsuario(u1);
		try {
			sistema.loginUsuario(u1.getEmail(), "12334");
		} catch (LoginFallidoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void eliminarUsuarioExitoso() {
		try {
			sistema.registrarUsuario(u1);
			sistema.registrarUsuario(a1);
			sistema.loginUsuario(a1.getEmail(), a1.getContrasenia());
			Boolean valorEsperado = sistema.eliminarUsuario(u1.getEmail());
			Assert.assertTrue(valorEsperado);
		} catch (NoEsAdminException | UsuarioInexistenteException | EmailYaRegistradoException | LoginFallidoException
				| EmailIncorrectoException | ContraseniaIncorrectaException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = NoEsAdminException.class)
	public void eliminarUsuarioFallidoNoAdmin()
			throws EmailYaRegistradoException, NoEsAdminException, UsuarioInexistenteException {
		sistema.registrarUsuario(u1);
		sistema.eliminarUsuario(u1.getEmail());

	}

	@Test(expected = UsuarioInexistenteException.class)
	public void eliminarUsuarioFallidoDosUsuarioInexistente()
			throws EmailYaRegistradoException, NoEsAdminException, UsuarioInexistenteException, LoginFallidoException,
			EmailIncorrectoException, ContraseniaIncorrectaException {
		sistema.registrarUsuario(a1);
		sistema.loginUsuario(a1.getEmail(), a1.getContrasenia());
		sistema.eliminarUsuario(u1.getEmail());
	}

	@Test
	public void modificarContraseniaExistoso() {
		try {
			sistema.registrarUsuario(u1);
		} catch (EmailYaRegistradoException e) {
			e.printStackTrace();
		}
		Boolean valorEsperado;
		try {
			valorEsperado = sistema.modificarContrasenia(u1.getEmail(), "12345", "00000");
			Assert.assertTrue(valorEsperado);
		} catch (UsuarioInexistenteException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = UsuarioInexistenteException.class)
	public void modificarContraseniaInvalido() throws EmailYaRegistradoException, UsuarioInexistenteException {
		sistema.registrarUsuario(u2);
		sistema.modificarContrasenia(u1.getEmail(), "12345", "00000");
	}

	@Test
	public void agregarProductoExitoso() {
		try {
			sistema.registrarUsuario(a1);
			sistema.loginUsuario(a1.getEmail(), a1.getContrasenia());
			Boolean valorEsperado = sistema.agregarProducto(nuevoProducto);
			Assert.assertTrue(valorEsperado);
		} catch (NoEsAdminException | EmailYaRegistradoException | LoginFallidoException | EmailIncorrectoException
				| ContraseniaIncorrectaException | ProductoExistenteException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = NoEsAdminException.class)
	public void agregarProductoFallidoNoAdmin() throws NoEsAdminException, ProductoExistenteException {
		sistema.agregarProducto(nuevoProducto);
	}

	@Test(expected = ProductoExistenteException.class)
	public void agregarProductoFallidoProductoExistente()
			throws NoEsAdminException, ProductoExistenteException, EmailYaRegistradoException, LoginFallidoException,
			EmailIncorrectoException, ContraseniaIncorrectaException {
		sistema.registrarUsuario(a1);
		sistema.loginUsuario(a1.getEmail(), a1.getContrasenia());
		sistema.agregarProducto(nuevoProducto);
		sistema.agregarProducto(nuevoProducto);
	}

	@Test
	public void eliminarProductoExitoso() {
		try {
			sistema.registrarUsuario(a1);
			sistema.loginUsuario(a1.getEmail(), a1.getContrasenia());
			sistema.agregarProducto(nuevoProducto);
			Assert.assertTrue(sistema.eliminarProducto(nuevoProducto.getCodigo()));
		} catch (ProductoExistenteException e) {
			e.printStackTrace();
		} catch (NoEsAdminException e) {
			e.printStackTrace();
		} catch (ProductoInexistenteException e) {
			e.printStackTrace();
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

	@Test(expected = NoEsAdminException.class)
	public void eliminarProductoFallidoNoAdmin()
			throws NoEsAdminException, ProductoInexistenteException, ProductoExistenteException {
		try {
			sistema.agregarProducto(nuevoProducto);
		} catch (ProductoExistenteException e) {
			e.printStackTrace();
		}
		sistema.eliminarProducto(nuevoProducto.getCodigo());
	}

	@Test(expected = ProductoInexistenteException.class)
	public void eliminarProductoFallidoProductoInexistente() throws EmailYaRegistradoException, LoginFallidoException,
			EmailIncorrectoException, ContraseniaIncorrectaException, NoEsAdminException, ProductoInexistenteException,
			ProductoExistenteException {
		sistema.registrarUsuario(a1);
		sistema.loginUsuario(a1.getEmail(), a1.getContrasenia());
		sistema.agregarProducto(nuevoProducto);
		sistema.eliminarProducto(nuevoProducto2.getCodigo());
	}

	@Test
	public void modificarProductoExitoso() {
		try {
			sistema.registrarUsuario(a1);
			sistema.loginUsuario(a1.getEmail(), a1.getContrasenia());
			sistema.agregarProducto(nuevoProducto);
			Assert.assertTrue(sistema.modificarProducto(nuevoProducto, nuevoProducto2));
		} catch (NoEsAdminException e) {
			e.printStackTrace();
		} catch (EmailYaRegistradoException e) {
			e.printStackTrace();
		} catch (LoginFallidoException e) {
			e.printStackTrace();
		} catch (EmailIncorrectoException e) {
			e.printStackTrace();
		} catch (ContraseniaIncorrectaException e) {
			e.printStackTrace();
		} catch (ProductoExistenteException e) {
			e.printStackTrace();
		} catch (ProductoInexistenteException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = NoEsAdminException.class)
	public void modificarProductoFallidoNoAdmin() throws EmailYaRegistradoException, LoginFallidoException,
			EmailIncorrectoException, ContraseniaIncorrectaException, ProductoExistenteException, NoEsAdminException,
			ProductoInexistenteException {
		sistema.registrarUsuario(u1);
		sistema.loginUsuario(u1.getEmail(), u1.getContrasenia());
		sistema.agregarProducto(nuevoProducto);
		sistema.modificarProducto(nuevoProducto, nuevoProducto2);

	}

	@Test(expected = ProductoInexistenteException.class)
	public void modificarProductoFallidoProductoInexistente() throws ProductoExistenteException,
			ProductoInexistenteException, NoEsAdminException, EmailYaRegistradoException, LoginFallidoException,
			EmailIncorrectoException, ContraseniaIncorrectaException {
		sistema.registrarUsuario(a1);
		sistema.loginUsuario(a1.getEmail(), a1.getContrasenia());
		sistema.agregarProducto(nuevoProducto);
		sistema.modificarProducto(nuevoProducto2, nuevoProducto);
	}
}