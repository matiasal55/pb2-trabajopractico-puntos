package pb2.puntos;

public class LoginFallidoException extends Exception {
	public LoginFallidoException() {
		super("Email y/o contrasenia invalida.");
	}
}
