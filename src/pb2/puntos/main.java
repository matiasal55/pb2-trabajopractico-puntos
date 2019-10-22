package pb2.puntos;

import java.util.Random;

public class main {

	public static void main(String[] args) {
		Random generador = new Random(System.currentTimeMillis());
		Integer numeroDeOrden = generador.nextInt(100000);
		System.out.println(numeroDeOrden);
	}

}
