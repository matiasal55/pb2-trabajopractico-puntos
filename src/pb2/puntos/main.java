package pb2.puntos;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
	public static void main(String[] args) throws ProductoInexistenteException, SaldoInsuficienteException, UsuarioExistenteException {
//		Sistema miSistema = new Sistema();
//		Usuario nuevo = new Cliente("Cosme", "Fulanito", "hotmail.com", "1234A");
//		Usuario admin = new Administrador("Matias", "Alarcon", "gmail.com", "1234A");
//		Producto nuevoProducto = new Producto("Chocolate", 123, "Blanco", 21.0, 100);
//		miSistema.registrarUsuario(nuevo);
////		miSistema.agregarProducto(nuevoProducto);
//		miSistema.comprarProducto(nuevo, 1, nuevoProducto, "Saldo");
//		miSistema.comprarProducto(nuevo, 1, nuevoProducto, "Puntos");
		
//		Usuario nuevo1=new Cliente("Cosme", "Fulanito", "hotmail.com", "1234A");
////		Administrador admin=new Administrador("Roberto", "Funes", "airbook.com", "1234A");
//		Producto p1=new Producto("c", 123, "Choco", 21.0, 200);
//		try {
//			miSistema.comprarProducto(nuevo1, 1, p1, "Puntos");
//		} catch (productoInexistenteException | saldoInsuficienteException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}
	
		
		
////		if(nuevo1.getClass().getSimpleName().equals("Administrador"))
//			System.out.println("Soy administrador");
//		else
//			System.out.println("Soy cliente");
//		System.out.println(miSistema.registrarUsuario(nuevo1));
//		System.out.println(miSistema.registrarUsuario(admin));
//		((Administrador)admin).agregarProducto(p1);
//		File nuevoArchivo=new File("productos.txt");
//		if(!nuevoArchivo.exists())
//			nuevoArchivo.createNewFile();
//		ArrayList<File> copiarLista=new ArrayList<>();
//		Scanner s=null;
//		s=new Scanner(nuevoArchivo);
//		while(s.hasNext()) {
//			String dato=s.next();
//			String [] cortarString = dato.split("::");
//		}
		
//		for(Usuario lista:miSistema.listaDeUsuarios)
//			System.out.println(lista.getId());
//		admin.modificarFactorDePuntos(30, "Cliente");
//		for(Usuario lista:miSistema.listaDeUsuarios)
//			System.out.println(lista.getFactorDePuntos());
//		Integer opcion;
//		System.out.println("Bienvenidos a la perfumería!!!");
//		System.out.println("1 - Login");
//		System.out.println("2 - Registrarse");
//		System.out.println("3 - Solo Administradores");
//		opcion = teclado.nextInt();
//		switch (opcion) {
//		case 1:
//			opcionUno();break;
//		case 2: {
//			opcionDos();
//			opcionUno();
//		} break;
//		case 3: opcionTres();
//		}
//		System.exit(0);
	}
//
//	private static void opcionTres() {
//		System.out.println("Ingrese su nombre de usuario: ");
//		String mail = teclado.next();
//		System.out.println("Ingrese su contraseña: ");
//		String contrasenia = teclado.next();
//		if (miSistema.loginUsuario(mail, contrasenia)) {
//			System.out.println("Ingreso correcto");
//			panelGeneral();
//		}
//		else {
//			System.out.println("Usuario no registrado. ¿Desea registrarse? Y / N");
//			String opcion = teclado.next();
//			if (opcion.equals("Y")) {
//				System.out.println("Ingrese su nombre: ");
//				String nombre = teclado.next();
//				System.out.println("Ingrese su apellido: ");
//				String apellido = teclado.next();
//				System.out.println("Ingrese su email: ");
//				mail = teclado.next();
//				System.out.println("Ingrese su contrasenia: ");
//				contrasenia = teclado.next();
//				Usuario nuevo = new Administrador(nombre, apellido, mail, contrasenia);
//				Boolean registro=miSistema.registrarUsuario(nuevo);
//				if (registro) {
//					System.out.println("Usuario registrado con exito.");
//					main(null);
//				}
//				else {
//					System.out.println("Usuario existente");
//					main(null);
//				}
//			}
//			else {
//
//				System.out.println("Debe contar con un usuario para poder usar el sistema");
//				main(null);
//			}
//		}
//		
//	}
//
//	public static void opcionUno() {
//		System.out.println("Ingrese su nombre de usuario: ");
//		String mail = teclado.next();
//		System.out.println("Ingrese su contraseña: ");
//		String contrasenia = teclado.next();
//		if (miSistema.loginUsuario(mail, contrasenia)) {
//			System.out.println("Ingreso correcto");
//			panelGeneral();
//		}
//		else {
//			System.out.println("Usuario no registrado. ¿Desea registrarse? Y / N");
//			String opcion = teclado.next();
//			if (opcion.equals("Y"))
//				opcionDos();
//			else {
//
//				System.out.println("Debe contar con un usuario para poder usar el sistema");
//				main(null);
//			}
//		}
//	}
//
//	public static void opcionDos() {
//		System.out.println("Ingrese su nombre: ");
//		String nombre = teclado.next();
//		System.out.println("Ingrese su apellido: ");
//		String apellido = teclado.next();
//		System.out.println("Ingrese su email: ");
//		String mail = teclado.next();
//		System.out.println("Ingrese su contrasenia: ");
//		String contrasenia = teclado.next();
//		Usuario nuevo = new Cliente(nombre, apellido, mail, contrasenia);
//		Boolean registro=miSistema.registrarUsuario(nuevo);
//		if (registro) {
//			System.out.println("Usuario registrado con exito.");
//			main(null);
//		}
//		else {
//			System.out.println("Usuario existente");
//			main(null);
//		}
//	}
//	
//	public static void panelGeneral() {
//		System.out.println("Bienvenido al panel general!!!");
//	}

}
