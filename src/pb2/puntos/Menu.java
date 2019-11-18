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
							+ "\n4- Opciones de producto." + "\n5- Opciones de usuario."
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
						char option = 0;
						do {
							System.out.println("\n Bienvenido a la pantalla de productos: \n1- Crear producto."
									+ "\n2- Agregar producto a lista." + "\n3- Eliminar producto de una lista."
									+ "\n4- Mostrar lista productos." + "\n5- Modificar producto."
									+ "\n0- Salir de la pantalla de productos.");
							option = teclado.next().charAt(0);
							if (option == '1') {
								System.out.println("\nIngresar categoria: ");
								String descripcion = teclado.next();
								Integer codigo = 0;
								System.out.println("Ingresar nombre del producto: ");
								String nombreProducto = teclado.next();
								System.out.println("Ingresar precio en puntos: ");
								Integer precioPuntos = teclado.nextInt();
								System.out.println("Ingresar precio en efectivo: (Con coma)");
								Double precioReal = teclado.nextDouble();
								Producto p1 = new Producto(descripcion, codigo, nombreProducto, precioReal,
										precioPuntos);
								prod = p1;
							} else if (option == '2') {
								try {
									s1.agregarProducto(admin, prod);
								} catch (NoEsAdminException | productoExistenteException e) {
									e.printStackTrace();
								}
							} else if (option == '3') {
								System.out.println("\nIngresar id de producto a eliminar: ");
								Integer id1 = teclado.nextInt();
								s1.eliminarProducto(admin, id1);
							} else if (option == '4') {
								s1.mostrarListaProductos();
							} else if (option == '5') {
								System.out.println("\nIngresar codigo del producto: ");
								Integer codigoP = teclado.nextInt();
								System.out.println("Ingresar nueva descripcion: ");
								String descripcion = teclado.next();
								System.out.println("Ingresar nuevo nombre del producto: ");
								String nombreProducto = teclado.next();
								System.out.println("Ingresar nuevo precio en efectivo: ");
								Double precioReal = teclado.nextDouble();
								System.out.println("Ingresar nuevo precio en puntos: ");
								Integer precioPuntos = teclado.nextInt();
								Producto pNuevo = new Producto(descripcion , codigoP, nombreProducto, precioReal,
								precioPuntos);
								try {
									s1.modificarProducto(admin, codigoP, pNuevo);
								} catch (ProductoNoExisteException | NoEsAdminException e) {
									e.printStackTrace();
								}
				
							}
						} while (option != '0');
						break;
					case '5':
						char option1 = 0;
						do {
							System.out.println("Bienvenido a la pantalla de usuario: " + "\n1- Eliminar usuario."
									+ "\n2- Mostrar lista de usuarios." + "\n0- Salir de la pantalla de usuario.");
							option1 = teclado.next().charAt(0);
							if (option1 == '1') {
								System.out.println("\nIngresar id del cliente a eliminar: ");
								Integer id2 = teclado.nextInt();
								try {
									s1.eliminarUsuario(admin, id2);
								} catch (NoEsAdminException e) {
									e.printStackTrace();
								}
							} else if (option1 == '2') {
								s1.mostarListaUsuarios();
							}
						} while (option1 != '0');
						break;
					}
				} while (opcion != '0');
			} else if (opcion == '2') {
				do {
					System.out.println(
							"\n1-Crear usuario." + "\n2- Registarse." + "\n3- Loguearse." + "\n4- Opciones de compra."
									+ "\n5- Recargar saldo." + "0- Volver a la seleccion de admin/cliente.");
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
									+ "\n2- Realizar una compra." + "\n3- Anular compra."
									+ "\n0- Salir de la pantalla de compra.");
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
								Integer cantidadDePuntos = /* factor de puntos * cantidad */0;
								Ventas venta = new Ventas(idVenta, (Cliente) client, cantidad, prod, medioDePago,
										cantidadDePuntos);
								vent = venta;
								try {
									s1.realizarCompra(vent);
								} catch (VentaFallidaException | MetodoDePagoNoExistenteException | EmailYaRegistrado
										| ElUsuarioNoEstaRegistradoException | EfectivoInsuficienteException
										| PuntosInsuficienteException e) {
									e.printStackTrace();
								}
							} else if (option == '3') {
								s1.anularCompra(vent);
							}
						} while (option != '0');
						break;
					case '5':
						s1.anularCompra(vent);
						break;
					case '6':
						System.out.println("Ingrese email del cliente a recargar: ");
						String emailx3 = teclado.next();
						System.out.println("\nIngrese monto para la recarga: (Valor con coma)");
						Double monto = teclado.nextDouble();
						try {
							s1.recargarSaldo(emailx3, monto);
						} catch (MontoIncorrecto | UsuarioInexistenteException e) {
							e.printStackTrace();
						}
					}
				} while (opcion != '0');
			}
		} while (opcion != '3');
		if (opcion == '3')

		{
			System.out.println("Usted ha salido.");
		}
	}
}