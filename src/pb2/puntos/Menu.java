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
		DetallesDePago recibo = null;

		Producto nuevoProd = new Producto("Chocolate", 123, "Blanco", 21.0, 100);
		Producto nuevoProd2 = new Producto("Chocolate", 456, "Negro", 42.0, 200);
		Producto nuevoProd3 = new Producto("Chocolate", 789, "Puro", 63.0, 250);

		do {
			System.out.println("Elige ser: \n" + "1- Administrador \t" + "2- Cliente \t" + "3- Salir.");
			opcion = teclado.next().charAt(0);
			if (opcion == '1') {
				do {
					System.out.println("\n1- Crear admin. " + "\n2- Registrarse. " + "\n3- Loguearse."
							+ "\n4- Opciones de producto." + "\n5- Opciones de usuario." + "\n6- Cambiar contrasenia."
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
						Usuario ad = new Administrador(nombre, apellido, mail, contrasenia);
						admin = ad;
						break;
					case '2':
						try {
							s1.registrarUsuario(admin);
						} catch (EmailYaRegistradoException e1) {
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
						try {
							s1.agregarProducto(nuevoProd);
							s1.agregarProducto(nuevoProd2);
							s1.agregarProducto(nuevoProd3);
						} catch (ProductoExistenteException | NoEsAdminException e1) {
							e1.printStackTrace();
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
								System.out.println("Ingresar codigo del producto: ");
								Integer codigo = teclado.nextInt();
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
									s1.agregarProducto(prod);
								} catch (ProductoExistenteException | NoEsAdminException e) {
									e.printStackTrace();
								}
							} else if (option == '3') {
								System.out.println("\nIngresar id de producto a eliminar: ");
								Integer id1 = teclado.nextInt();
								try {
									s1.eliminarProducto(id1);
								} catch (NoEsAdminException | ProductoInexistenteException e) {
									e.printStackTrace();
								}
							} else if (option == '4') {
								s1.mostrarListaProductos();
							} else if (option == '5') {
//								System.out.println("\nIngresar datos del producto a modificar: ");
//								System.out.println("Ingresar descripcion: ");
//								String descripcionp = teclado.next();
//								System.out.println("Ingresar codigo del producto: ");
//								Integer codigop = teclado.nextInt();
//								System.out.println("Ingresar nombre del producto: ");
//								String nombrep = teclado.next();
//								System.out.println("Ingresar precio en puntos: ");
//								Integer precioPuntosp = teclado.nextInt();
//								System.out.println("Ingresar precio en efectivo: ");
//								Double precioRealp = teclado.nextDouble();
//								Producto pAModificar = new Producto(descripcionp, codigop, nombrep, precioRealp,
//										precioPuntosp);
								System.out.println("\nIngrese codigo del producto a modificar: ");
								Integer cod = teclado.nextInt();
								System.out.println("\nAhora ingrese los datos para modificar el producto: ");
								System.out.println("Ingresar nueva descripcion: ");
								String nuevaDescripcion = teclado.next();
								System.out.println("Ingresar nuevo codigo del producto: ");
								Integer nuevoCodigo = teclado.nextInt();
								System.out.println("Ingresar nuevo nombre del producto: ");
								String nuevoNombreProducto = teclado.next();
								System.out.println("Ingresar nuevo precio en puntos: ");
								Integer nuevoPrecioPuntos = teclado.nextInt();
								System.out.println("Ingresar nuevo precio en efectivo: ");
								Double nuevoPrecioReal = teclado.nextDouble();
								Producto pNuevo = new Producto(nuevaDescripcion, nuevoCodigo, nuevoNombreProducto,
										nuevoPrecioReal, nuevoPrecioPuntos);

								try {
									s1.modificarProducto(cod, pNuevo);
								} catch (ProductoInexistenteException | NoEsAdminException e) {
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
								System.out.println("\nIngresar email del cliente a eliminar: ");
								String emailUsr = teclado.next();
								try {
									s1.eliminarUsuario(emailUsr);
								} catch (UsuarioInexistenteException | NoEsAdminException e) {
									e.printStackTrace();
								}
							} else if (option1 == '2') {
								s1.mostarListaUsuarios();
							}
						} while (option1 != '0');
						break;
					case '6':
						System.out.println("Ingresar email: ");
						String emailC = teclado.next();
						System.out.println("Ingresar contrasenia: ");
						String contraseniaAntigua = teclado.next();
						System.out.println("Ingresar nueva contrasenia: ");
						String nuevaContrasenia = teclado.next();
						try {
							s1.modificarContrasenia(emailC, contraseniaAntigua, nuevaContrasenia);
						} catch (UsuarioInexistenteException e) {
							e.printStackTrace();
						}
						break;
					}
				} while (opcion != '0');
			} else if (opcion == '2') {
				do {
					System.out.println(
							"\n1-Crear usuario." + "\n2- Registarse." + "\n3- Loguearse." + "\n4- Opciones de compra."
									+ "\n5- Recargar saldo." + "\n0- Volver a la seleccion de admin/cliente.");
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

						Usuario cliente = new Cliente(nombre, apellido, mail, contrasenia);
						client = cliente;
						break;
					case '2':
						try {
							s1.registrarUsuario(client);
						} catch (EmailYaRegistradoException e) {
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
									+ "\n2- Realizar una compra." + "\n3- Anular compra." + "\n4- Ver recibo."
									+ "\n0- Salir de la pantalla de compra.");
							option = teclado.next().charAt(0);
							if (option == '1') {
								s1.mostrarListaProductos();
							} else if (option == '2') {
								System.out.println("\nIngresar cantidad del producto a comprar:");
								Integer cantidadp = teclado.nextInt();
								System.out.println("Ingresar codigo del producto a comprar: ");
								Integer idProd = teclado.nextInt();
								System.out.println(
										"Ingresar medio de pago: ('puntos' para pagara con puntos o 'saldo' para pagar en efectivo)");
								String medioDePagop = teclado.next();

								try {
									DetallesDePago nuevo = s1.comprarProducto(client, cantidadp, idProd, medioDePagop);
									recibo = nuevo;
								} catch (CompraFallidaException e1) {
									e1.printStackTrace();
								}
								if (medioDePagop.equals("puntos")) {
									try {
										s1.pagarConPuntos(recibo.getIdPago(), recibo.getPrecioPuntos());
									} catch (SaldoInsuficienteException | VentaFallidaException e) {
										e.printStackTrace();
									}
								} else if (medioDePagop.equals("saldo")) {
									try {
										s1.pagarConSaldo(recibo.getIdPago(), recibo.getPrecioSaldo());
									} catch (ProductoInexistenteException | SaldoInsuficienteException
											| VentaFallidaException e) {
										e.printStackTrace();
									}
								}
							} else if (option == '3') {
								s1.anularCompra(recibo.getIdPago());
							} else if (option == '4') {
								System.out.println(recibo);
							}
						} while (option != '0');
						break;
					case '5':
						System.out.println("\nIngrese monto para la recarga: (Valor con coma)");
						Double monto = teclado.nextDouble();
						try {
							s1.cargarSaldo(client, monto);
						} catch (LaRecargaHaFalladoException e) {
							e.printStackTrace();
						}
						break;
					}
				} while (opcion != '0');
			}
		} while (opcion != '3');
		if (opcion == '3')

		{
			teclado.close();
			System.out.println("Usted ha salido.");
		}
	}
}