package pb2.puntos;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) throws EmailYaRegistrado {
		char opcion = 0;

		Scanner teclado = new Scanner(System.in);
		
		Sistema s = new Sistema();
		Usuario client = null;
		
		do {
			System.out.println("1-Crear usuario.\n"+
								"2- Registarse.\n"
								+ "0- Salir.");
			opcion = teclado.next().charAt(0);
			switch(opcion) {
			case 0:
				
				break;
			case '1':
				System.out.println("Crear usuario: \n");
				System.out.println("Ingresar nombre: ");
				String nombre = teclado.next();
				System.out.println("Ingresar apellido: ");
				String apellido = teclado.next();
				System.out.println("Ingresar email: ");
				String mail = teclado.next();
				System.out.println("Ingresar contrasenia: ");
				String contrasenia = teclado.next();
				
				//VER
				Integer id = s.autogeneracionDeId(client);
//				Integer id = teclado.nextInt();
//				Integer puntosAcumulados = teclado.nextInt();
				Integer puntosAcumulados = 0;
//				Double saldo = teclado.nextDouble();
				Double saldo = 0.0;
				Usuario cliente = new Cliente(nombre, apellido, mail, contrasenia, id, puntosAcumulados, saldo);
				client = cliente;
				break;
			case '2':
				s.registrarUsuario(client);
				break;
			case '3':
				s.mostarListaUsuarios();
				break;
			}
		}
		while(opcion !='0');

	}

}
