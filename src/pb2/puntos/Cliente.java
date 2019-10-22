package pb2.puntos;

public class Cliente extends Persona {
	private Integer puntosAcumulados;

	public Cliente(String nombre, String apellido, String email, String password, Integer puntosAcumulados) {
		super();
		this.puntosAcumulados = puntosAcumulados;
		}

	public Integer getPuntosAcumulados() {
		return puntosAcumulados;
	}

	public void setPuntosAcumulados(Integer puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}
	
	

}
