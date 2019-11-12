package pb2.puntos;

public class ElUsuarioNoEstaRegistradoException extends Exception {
	
	public ElUsuarioNoEstaRegistradoException() {
		super("El usuario no se encuentra registrado para realizar las ventas.");
	}
	
}
