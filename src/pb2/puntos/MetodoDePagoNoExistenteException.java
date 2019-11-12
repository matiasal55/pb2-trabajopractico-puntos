package pb2.puntos;

public class MetodoDePagoNoExistenteException extends Exception{

	public MetodoDePagoNoExistenteException() {
		super("Metodo de pago inexistene, solo se acepta pago con efectivo o puntos.");
	}
		
	
	
}
