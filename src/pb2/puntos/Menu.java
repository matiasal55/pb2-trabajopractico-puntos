package pb2.puntos;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args){
		char opcion = 0;

		Scanner teclado = new Scanner(System.in);

		Sistema s1 = Sistema.getInstancia();
		Usuario client = null;
		Usuario admin = null;
		Producto prod = null;

		do {
			System.out.println("Elige: \n" + "1- Administrador \t" + "2- Cliente");
			opcion = teclado.next().charAt(0);
			if (opcion == '1') {
				do {
					System.out.println("\n1- Crear admin. " + "\n2- Registrarse. " + "\n3- Loguearse."
							+ "\n4- Crear producto." + "\n5- Agregar producto a una lista." + "6- Eliminar producto de una lista: ");
					opcion = teclado.next().charAt(0);
					switch (opcion) {
					case '1':
						System.out.println("-Ingresar nombre: ");
						String nombre = teclado.next();
						System.out.println("-Ingresar apellido: ");
						String apellido = teclado.next();
						System.out.println("-Ingresar email: ");
						String mail = teclado.next();
						System.out.println("-Ingresar contrasenia: ");
						String contrasenia = teclado.next();
						Integer puntosAcumulados = 0;
						Integer id = 0;
						Double saldo = 0.0;
						Usuario ad = new Administrador(nombre, apellido, mail, contrasenia, id, puntosAcumulados,
								saldo);
						admin = ad;
						break;
					case '2':
						try {
							s1.registrarUsuario(admin);
						} catch (EmailYaRegistrado e1) {
							e1.printStackTrace();
							
						}
						break;
					case '3':
						System.out.println("Ingresar email: ");
						String email = teclado.next();
						System.out.println("Ingresar contraseña: ");
						String contra = teclado.next();
						try {
							s1.loginUsuario(email, contra);
						} catch (LoginFallidoException | EmailIncorrectoException | ContraseniaIncorrectaException e) {
							e.printStackTrace();
						}
						break;
					case '4':
						System.out.println("Ingresar categoria: ");
						String descripcion = teclado.next();
						System.out.println("Ingresar codigo de producto: (Valores numericos");
						Integer codigo = teclado.nextInt();
						System.out.println("Ingresar nombre del producto: ");
						String nombreProducto = teclado.next();
						System.out.println("Ingresar precion el punto: ");
						Integer precioPuntos = teclado.nextInt();
						System.out.println("Ingresar precio en efectivo: (Con coma)");
						Double precioReal = teclado.nextDouble();
						Producto p1 = new Producto(descripcion, codigo, nombreProducto, precioReal, precioPuntos);
						prod = p1;
						break;
					case '5':
						try {
							s1.agregarProducto(admin, prod);
						} catch (NoEsAdminException e) {
							e.printStackTrace();
						}
						break;
					case '6':
						
						break;
					}
				} while (opcion != '9');

			} else if (opcion == '2') {
				do {
					System.out.println("\n1-Crear usuario.\n" + "2- Registarse.\n" + "0- Salir.");
					opcion = teclado.next().charAt(0);
					switch (opcion) {
					case 0:

						break;
					case '1':
						System.out.println("Crear usuario: ");
						System.out.println("-Ingresar nombre: ");
						String nombre = teclado.next();
						System.out.println("-Ingresar apellido: ");
						String apellido = teclado.next();
						System.out.println("-Ingresar email: ");
						String mail = teclado.next();
						System.out.println("-Ingresar contrasenia: ");
						String contrasenia = teclado.next();

						Integer id = 0;
						Integer puntosAcumulados = 0;
						Double saldo = 0.0;
						Usuario cliente = new Cliente(nombre, apellido, mail, contrasenia, id, puntosAcumulados, saldo);
						client = cliente;
						break;
					case '2':
						try {
							s1.registrarUsuario(client);
						} catch (EmailYaRegistrado e) {
							e.printStackTrace();
						}
						System.out.println("De regalo 500 puntos por registrarse.\n");
						break;
					case '3':
						// s1.mostarListaUsuarios();
						break;
					}
				} while (opcion != '8');
			}
		} while (opcion != '0');
	}
}
