package pb2.puntos;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		char opcion = 0;

		Scanner teclado = new Scanner(System.in);

		Sistema s1 = Sistema.getInstancia();
		Usuario client = null;
		Usuario admin = null;
		Producto prod = null;
		Ventas vent = null;

		do {
			System.out.println("Elige: \n" + "1- Administrador \t" + "2- Cliente \t" + "3- Salir.");
			opcion = teclado.next().charAt(0);
			if (opcion == '1') {
				do {
					System.out.println("\n1- Crear admin. " + "\n2- Registrarse. " + "\n3- Loguearse."
							+ "\n4- Crear producto." + "\n5- Agregar producto a lista."
							+ "\n6- Eliminar producto de una lista." + "\n7- Mostrar lista productos."
							+ "\n8- Eliminar usuario." + "\n9- Mostrar lista de usuarios."
							+ "\n0- Volver a la seleccion de admin/cliente.");
					opcion = teclado.next().charAt(0);
					switch (opcion) {
					case '1':
						System.out.println("\nIngresar nombre: ");
						String nombre = teclado.next();
						System.out.println("Ingresar apellido: ");
						String apellido = teclado.next();
						System.out.println("Ingresar email: ");
						String mail = teclado.next();
						System.out.println("Ingresar contrasenia: ");
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
						System.out.println("\nIngresar email: ");
						String email = teclado.next();
						System.out.println("Ingresar contrasenia: ");
						String contra = teclado.next();
						try {
							s1.loginUsuario(email, contra);
						} catch (LoginFallidoException | EmailIncorrectoException | ContraseniaIncorrectaException e) {
							e.printStackTrace();
						}
						break;
					case '4':
						System.out.println("\nIngresar categoria: ");
						String descripcion = teclado.next();
						System.out.println("Ingresar codigo de producto: (Valores numericos)");
						Integer codigo = teclado.nextInt();
						System.out.println("Ingresar nombre del producto: ");
						String nombreProducto = teclado.next();
						System.out.println("Ingresar precio en puntos: ");
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
						System.out.println("\nIngresar id de producto a eliminar: ");
						Integer id1 = teclado.nextInt();
						s1.eliminarProducto(admin, id1);
						break;
					case '7':
						s1.mostrarListaProductos();
						break;
					case '8':
						System.out.println("\nIngresar id del cliente: ");
						Integer id2 = teclado.nextInt();
						try {
							s1.eliminarUsuario(admin, id2);
						} catch (NoEsAdminException e) {
							e.printStackTrace();
						}
						break;
					case '9':
						s1.mostarListaUsuarios();
						break;
					}
				} while (opcion != '0');

			} else if (opcion == '2') {
				do {
					System.out.println("\n1-Crear usuario." + "\n2- Registarse." + "\n3- Loguearse."
							+ "\n4- Realizar una compra." + "\n5- Anular compra." + "\n6- Recargar saldo."
							+ "0- Volver a la seleccion de admin/cliente.");
					opcion = teclado.next().charAt(0);
					switch (opcion) {
					case 0:

						break;
					case '1':
						System.out.println("\nCrear usuario: ");
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
						System.out.println("\nIngresar email: ");
						String email = teclado.next();
						System.out.println("Ingresar contrasenia: ");
						String contra = teclado.next();
						try {
							s1.loginUsuario(email, contra);
						} catch (LoginFallidoException | EmailIncorrectoException | ContraseniaIncorrectaException e) {
							e.printStackTrace();
						}
						break;
					case '4':
						char option = 0;
						do {
							System.out.println("\nBienvenido a la pantalla de compra: " + "\n1- Ver lista de productos."
									+ "\n2- Realizar una compra." + "\n0- Salir de la pantalla de compra.");
							option = teclado.next().charAt(0);
							if (option == '1') {
								s1.mostrarListaProductos();
							} else if (option == '2') {
								System.out.println("\nIngrese id del producto: ");
								Integer idVenta = teclado.nextInt();
								System.out.println("Ingrese la cantidad del producto: ");
								Integer cantidad = teclado.nextInt();
								System.out.println("Ingrese medio de pago: (efectivo o puntos)");
								String medioDePago = teclado.next();
								Integer cantidadDePuntos = 0; // VER
								Ventas venta = new Ventas(idVenta, (Cliente) client, cantidad, prod, medioDePago,
										cantidadDePuntos);
								vent = venta;
								try {
									s1.realizarCompra(vent);
								} catch (VentaFallidaException | MetodoDePagoNoExistenteException | EmailYaRegistrado
										| ElUsuarioNoEstaRegistradoException e) {
									e.printStackTrace();
								} catch (EfectivoInsuficienteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} while (option != '0');
						break;
					case '5':
						s1.anularCompra(vent);
						break;
					case '6':
						System.out.println("\nIngrese monto para la recarga: (Valor con coma)");
						Double monto = teclado.nextDouble();
						s1.recargarSaldo(client, monto);
					}
				} while (opcion != '0');
			}
		} while (opcion != '3');
		if (opcion == '3') {
			System.out.println("Usted ha salido.");
		}
	}
}