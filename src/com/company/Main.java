package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		// declaro archivos de respaldo
		Archivo<Usuario> clientesFile = new Archivo<>("baseClientes.dat");
		Archivo<Avion> avionesFile = new Archivo<>("flotaAviones.dat");
		Archivo<Vuelo> vuelosFile = new Archivo<>("vuelosPactados.dat");

		// declaro base de datos en memoria ppal y recupero datos de archivo
		ArrayList<Avion> flotaAviones = avionesFile.recuperar();
		ArrayList<Usuario> baseClientes = clientesFile.recuperar();
		ArrayList<Vuelo> vuelosPactados = vuelosFile.recuperar();

		// Interfaz del usuario
		System.out.println("Sistema de Contratación de Vuelos << AERO-TAXI >>\n");
		int dni = Main.solicitarDni();
		Usuario usuarioValidado = obtenerUsr(baseClientes, dni);

		if(!baseClientes.contains(usuarioValidado)){
			baseClientes.add(usuarioValidado);
			clientesFile.persistir(baseClientes);
		}

		Scanner teclado = new Scanner(System.in);
		int opcionMenu;

		do {
			System.out.println("1- Contratar vuelo.\n" +
					"2- Cancelar vuelo.\n" +
					"3- Ver base de clientes.\n" +
					"4- Ver vuelos programados.\n" +
					"5- Salir");

			opcionMenu = teclado.nextInt();

			switch (opcionMenu) {
				case 1:
					/* metodo contratar vuelo */
					break;
				case 2:
					/* metodo cancelar vuelo */
					break;
				case 3:
					Main.listarClientes(baseClientes);
					/* falta agregar:
						Todos los datos personales.
						La categoría del mejor avión utilizado (Gold, Silver o Bronze).
						Total gastado de todos sus vuelos.
					 */
					break;
				case 4:
					LocalDate fecha = solicitarFecha();
					Main.verVuelos(vuelosPactados,fecha);
					break;
			}
		}
		while (opcionMenu != 5);
	}

	// funciones de la clase Main
	public static int solicitarDni() {
		Scanner teclado = new Scanner(System.in);

		int dni = 0;
		boolean dniInvalido = false;

		do {
			try {
				System.out.println("Ingrese su DNI: ");
				dni = teclado.nextInt();
				dniInvalido = false;
			} catch (InputMismatchException e) {
				System.out.println("Ingrese un valor numerico valido.");
				dniInvalido = true;
			}
		}
		while (dniInvalido || (dni < 1000000 && dni > 60000000));

		return dni;
	}

	public static Usuario obtenerUsr(ArrayList<Usuario> baseClientes, int dni) {
		Usuario unUsuario = null;
		boolean usrRegistrado = false;

		if (!baseClientes.isEmpty()) {
			for (int i = 0; i < baseClientes.size(); i++) {
				unUsuario = baseClientes.get(i);
				if (unUsuario != null && unUsuario.getDni() == dni) {
					usrRegistrado = true;
					break;
				}
			}
		}
				/*
				Aqui se puede implementar control por contraseña
				 */
		if (baseClientes.isEmpty() || !usrRegistrado) {
			unUsuario = Main.nuevoUsuario();
			baseClientes.add(unUsuario);
		}

		return unUsuario;
	}

	public static Usuario nuevoUsuario(ArrayList<Usuario> baseClientes, int dni) {

		Scanner teclado = new Scanner(System.in);
		String nombre, apellido;
		int edad;

		while (true) {
			try {
				System.out.println("Ingrese primer nombre: ");
				nombre = teclado.nextLine();
				System.out.println("Ingrese apellido: ");
				apellido = teclado.nextLine();
				System.out.println("Ingrese edad: ");
				edad = teclado.nextInt();

				Usuario nuevoUsuario = new Usuario(nombre, apellido, dni, edad);
				return nuevoUsuario;

			} catch (InputMismatchException e) {
				System.out.println("Los datos ingresados son incorrectos: (" + e + ")");
				teclado.nextLine(); // flush del buffer
			}
		}
	}

	public static void contratarVuelo() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingrese fecha y hora de partida (aaaa-mm-dd): ");
		LocalDate fecha = solicitarFecha();
		// mostrar destinos que no coincidan con el origen
		System.out.println("Seleccione origen:");
		System.out.println("1- Buenos aires \n" +
				"2- Cordoba \n" +
				"3- Montevideo \n" +
				"4- Santiago de chile \n");
		Scanner nuevo = new Scanner(System.in);
		int num = nuevo.nextInt();
		Ciudad origen;
		if (num == 1) {
			origen = Ciudad.BUE;
		}
		if (num == 2) {
			origen = Ciudad.COR;
		}
		if (num == 3) {
			origen = Ciudad.MVD;
		}
		if (num == 4) {
			origen = Ciudad.SCL;
		}
		int a = nuevo.nextInt();
		Ciudad destino;
		do {
			System.out.println("Seleccione destino diferente al origen");
			System.out.println("1- Buenos aires \n" +
					"2- Cordoba \n" +
					"3- Montevideo \n" +
					"4- Santiago de chile \n");
			if (num == 1) {
				destino = Ciudad.BUE;
			}
			if (num == 2) {
				destino = Ciudad.COR;
			}
			if (num == 3) {
				destino = Ciudad.MVD;
			}
			if (num == 4) {
				destino = Ciudad.SCL;
			}
		} while (origen == destino);
		Main.mostrarAvionesDisp(fecha);
		Avion reservado = new Avion();
		reservado = Main.reservar();
		Vuelo vuelonuevo = new Vuelo(origen, destino, reservado, llegada);
		float costo = vuelonuevo.calcularCosto();
		System.out.println("El costo todal del vuelo es de :" + costo);
		System.out.println("¿Que desea realizar?\n" +
				"1- Contratar. \n" +
				"2- Cancelar.");
		Scanner conf = new Scanner(System.in);
		int confirmacion = conf.nextInt();
		if (confirmacion == 1) {
			System.out.println("Tu vuelo se ha reservado con exito, detalles del vuelo:" + vuelonuevo.toString());
			vuelosPactados.add(vuelonuevo);
		}
	}

	public static LocalDate solicitarFecha() {
		Scanner teclado = new Scanner(System.in);
		LocalDate fecha = null;
		boolean fechaOk;
		do {
			try {
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				fecha = LocalDate.parse(teclado.nextLine(), formato);
				System.out.println(fecha);
				fechaOk = true;
			} catch (DateTimeParseException e) {
				System.out.println("Formato de fecha incorrecto.");
				fechaOk = false;
			}
		}
		while (!fechaOk);

		return fecha;
	}

	public static Avion reservar() {
		Scanner aviondes = new Scanner(System.in);
		int flag = 0;
		while (flag == 0) {                                                    //Pide el numero de avion a reservar
			System.out.println("Ingrese el numero del avion disponible que desee:");
			int avion = aviondes.nextInt();                                    //Si ya esta reservado te pide que busques otro
			if (flotaAviones.get(avion).enVuelo == true) {
				System.out.println("EL avion esta ocupado en esa fecha, elija otro");
			} else                                                            //Si no lo reserva cambiando enVuelo a true
			{
				System.out.println("Avion elegido: " + flotaAviones.get(avion).toString());
				flotaAviones.get(avion).enVuelo = true;
				flag = 1;
			}
		}
		return flotaAviones.get(avion);                                    //Devuelve el avion que se reservo
	}

	//Tratar de que solo muestre los aviones disponibles
	public static void mostrarAvionesDisp(LocalDate fecha) {
		int a = flotaAviones.size();
		int j = vuelosPactados.size();
		for (int i = 0; i <= a; i++)      //recorre la flota de aviones
		{
			System.out.println("Avion n*" + i);
			System.out.println(flotaAviones.get(i).tostring());   //la imprime
			int flag = 0;
			int p = 0;
			while (flag == 0 && a <= j)                                                //recorre los vuelos pactados
			{
				if (flotaAviones.get(i) == vuelosPactados.get(p).tipoAvion)            //si el avion esta en la lista de vuelos pactados se fija si en esa fecha esta ocupado
				{
					if (fecha == vuelosPactados.get(p).partida) {                    //si esta ocupado enVuelo pasa a ser true
						System.out.println("Avion no disponible para esa fecha");
						flag = 1;
						flotaAviones.get(i).enVuelo = true;
					}
					if (fecha == vuelosPactados.get(p).llegada) {
						System.out.println("Avion no disponible para esa fecha");
						flag = 1;
						flotaAviones.get(i).enVuelo = true;
					}
				}
				a++;
			}

		}
	}

	public static void listarClientes(ArrayList<Usuario> base) {
		if (!base.isEmpty()) {
			for (Usuario aux : base) {
				System.out.println(aux);
			}
		} else
			System.out.println("La base de clientes está vacía.");
	}

	public static void verVuelos(ArrayList<Vuelo> vuelosPactados, LocalDate fecha) {
		for (Vuelo aux : vuelosPactados) {
			if (aux.getPartida().equals(fecha))
				System.out.println(aux);
		}
	}
}
