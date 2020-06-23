package com.company;

/* A CORREGIR:

No se debe poder contratar para fecha actual o anteriores.

Mensaje: No hay aviones disponibles para esa fecha.

Opcion 3: Ver base de clientes, no lista categoria bronze

Opcion 4: Ver vuelos programados: agregar el usuario que contrató el vuelo

Revisar formula de tarifa. (por las dudas)

* */


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		// archivos de respaldo
		Archivo<Usuario> clientesFile = new Archivo<>("baseClientes.dat");
		Archivo<Avion> avionesFile = new Archivo<>("flotaAviones.dat");
		Archivo<Vuelo> vuelosFile = new Archivo<>("vuelosPactados.dat");

		// base de datos en memoria ppal y recupero datos de archivo
		ArrayList<Avion> flotaAviones = avionesFile.recuperar();
		ArrayList<Usuario> baseClientes = clientesFile.recuperar();
		ArrayList<Vuelo> vuelosPactados = vuelosFile.recuperar();

		// Se crean los Aviones por primera vez
		if(flotaAviones.isEmpty()){
			flotaAviones.add(new Gold(15000,5,Propulsion.REACCION,true,true));
			flotaAviones.add(new Gold(10000,20,Propulsion.REACCION, true,true));
			flotaAviones.add(new Gold(10000,20,Propulsion.PISTONES,true,false));
			flotaAviones.add(new Silver(5000,15,Propulsion.PISTONES,true));
			flotaAviones.add(new Silver(6000,10,Propulsion.HELICE,false));
			flotaAviones.add(new Bronze(8000,30,Propulsion.HELICE));
			flotaAviones.add(new Bronze(9500,15,Propulsion.PISTONES));
			avionesFile.persistir(flotaAviones);
		}

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
			System.out.println("\nBienvenido/a " + usuarioValidado.getNombre() + ", elija la opción deseada: " +
					"\n1- Contratar vuelo.\n" +
					"2- Cancelar vuelo.\n" +
					"3- Ver base de clientes.\n" +
					"4- Ver vuelos programados.\n" +
					"5- Salir");

			opcionMenu = teclado.nextInt();

			switch (opcionMenu) {
				case 1:
					Vuelo vueloReservado = Main.contratarVuelo(usuarioValidado,vuelosPactados, flotaAviones);
					if(vueloReservado != null){
						System.out.println("Vuelo reservado! Gracias por volar con Aero-Taxi!\n" +
								vueloReservado);
						vuelosPactados.add(vueloReservado);
						vuelosFile.persistir(vuelosPactados);
					}
					break;
				case 2:
					/* metodo cancelar vuelo */
					break;
				case 3:
					Main.listarClientes(baseClientes,vuelosPactados);
					break;
				case 4:
					System.out.println("Ingrese fecha de partida (aaaa-mm-dd): ");
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
		boolean dniInvalido;

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
		while (dniInvalido);

		return dni;
	}

	public static LocalDate solicitarFecha() {
		Scanner teclado = new Scanner(System.in);
		LocalDate fecha = null;
		boolean fechaOk;
		do {
			try {
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				fecha = LocalDate.parse(teclado.nextLine(), formato);
				fechaOk = true;
			} catch (DateTimeParseException e) {
				System.out.println("Formato de fecha incorrecto. Vuelva a ingresar:");
				fechaOk = false;
			}
		}
		while (!fechaOk);

		return fecha;
	}

	public static Usuario obtenerUsr(ArrayList<Usuario> baseClientes, int dni) {
		Usuario unUsuario = null;
		boolean usrRegistrado = false;

		if (!baseClientes.isEmpty()) {
			for (Usuario auxUsuario : baseClientes) {
				if (auxUsuario != null && auxUsuario.getDni() == dni) {
					usrRegistrado = true;
					unUsuario = auxUsuario;
					break;
				}
			}
		}
		if (baseClientes.isEmpty() || !usrRegistrado) {
			unUsuario = Main.nuevoUsuario(dni);
		}

		return unUsuario;
	}

	public static Usuario nuevoUsuario(int dni) {

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

				return new Usuario(nombre, apellido, dni, edad);

			} catch (InputMismatchException e) {
				System.out.println("Los datos ingresados son incorrectos: (" + e + ")");
				teclado.nextLine(); // flush del buffer
			}
		}
	}

	public static Vuelo contratarVuelo(Usuario usuario,ArrayList<Vuelo> vuelosPactados, ArrayList<Avion> flotaAviones) {

		System.out.println("Ingrese fecha de partida (aaaa-mm-dd): ");
		LocalDate fechaPartida = Main.solicitarFecha();

		ArrayList<Ciudad> itinerario = new ArrayList<>();
		itinerario.add(Ciudad.BUE);
		itinerario.add(Ciudad.COR);
		itinerario.add(Ciudad.MVD);
		itinerario.add(Ciudad.SCL);

		Ciudad origen = null; Ciudad destino = null;

		System.out.println("Seleccione ciudad de origen: ");

		for(int e=0;e<2;e++) {
			int i = 0;
			for (Ciudad aux : itinerario) {
				System.out.println(i + "- " + aux.getNombre() + " (" + aux + ")");
				i++;
			}
			int opcion;
			do {
				System.out.println("Elija una opcion: ");
				opcion = solicitarInteger();
			}
			while ( (opcion < 0) || (opcion >= itinerario.size()));

			System.out.println(" - " + itinerario.get(opcion).getNombre() + " - \n");

			if(e==0){
				origen = itinerario.get(opcion);
				System.out.println("Seleccione ciudad de destino: ");
			}
			else
				destino = itinerario.get(opcion);

			itinerario.remove(opcion);
		}

		System.out.println("Ingrese cantidad de acompañantes o 0 si viaja solo: ");
		int cantPasajeros = solicitarInteger();
		Avion avionReservado = Main.seleccionarAvion(vuelosPactados,flotaAviones,fechaPartida,cantPasajeros);

		Vuelo vueloReservado = null;
		int confirmacion = 2;

		if(avionReservado != null) {
			Vuelo vueloNuevo = new Vuelo(origen, destino, avionReservado, fechaPartida, cantPasajeros, usuario);
			System.out.println("Costo total del vuelo: $" + vueloNuevo.calcularCosto());
			System.out.println("¿Desea confirmar?\n" +
					"1- Confirmar. \n" +
					"2- Cancelar.");
			do {
				confirmacion = solicitarInteger();
			} while (confirmacion != 1 && confirmacion != 2);

			if (confirmacion == 1)
				vueloReservado = vueloNuevo;
		}
		if (confirmacion == 2)
			System.out.println("Reserva cancelada.");

		return vueloReservado;
	}

	public static Avion seleccionarAvion(ArrayList<Vuelo> vuelosPactados, ArrayList<Avion> flotaAviones,LocalDate fechaPartida,int cantPasajeros){
		int i = 0;
		boolean avionReservado = false;
		boolean avionesDisponibles = false;

		System.out.println("Aviones disponibles: ");
		for(Avion unAvion : flotaAviones){ // busco en la flota de aviones, uno por uno
			for(Vuelo unVuelo : vuelosPactados){ // busco en el registro de vuelos pactados uno por uno
				if(unVuelo.getPartida().equals(fechaPartida) && unVuelo.getTipoAvion().equals(unAvion)){
					// si en este registro de vuelo pactado se encuentra la fecha y el avion elegidos,
					// entonces el avion ya esta reservado y no se puede usar
					avionReservado = true;
				}
			}
			if(!avionReservado && unAvion.getCapacidadMax() >= cantPasajeros) {// si no esta reservado, lo imprimo.
				System.out.println("Avion ID: " + i + " - " + unAvion);
				avionesDisponibles = true;
			}
			i++;
		}

		Avion avionSeleccionado = null;

		if(avionesDisponibles) {
			System.out.println("Ingrese el ID del avion:");
			int opcion = solicitarInteger();
			avionSeleccionado = flotaAviones.get(opcion);
		}
		else {
			System.out.println("Lo sentimos, no hay aviones disponibles para los requerimientos deseados.");
		}

		return avionSeleccionado;
	}

	public static void listarClientes(ArrayList<Usuario> baseClientes, ArrayList<Vuelo> vuelosPactados) {
		if (!baseClientes.isEmpty()) {
			for (Usuario auxCliente : baseClientes) {
				if(auxCliente != null) {
					System.out.println(auxCliente);
					System.out.println(mejorAvionUsado(vuelosPactados, auxCliente));
				}
			}
		} else
			System.out.println("La base de clientes está vacía.");
	}

	public static void verVuelos(ArrayList<Vuelo> vuelosPactados, LocalDate fecha) {
		boolean existenVuelos = false;
		for (Vuelo aux : vuelosPactados) {
			if (aux.getPartida().equals(fecha)) {
				System.out.println(aux);
				existenVuelos = true;
			}
		}
		if(!existenVuelos)
			System.out.println("No existen vuelos programados para la fecha " + fecha);
	}

	public static String mejorAvionUsado(ArrayList<Vuelo> vuelosPactados,Usuario unUsuario){

		String mejorAvion = "El cliente no ha contratado vuelos aun.";
		boolean silver = false;

		for(Vuelo unVuelo : vuelosPactados){
			if(unVuelo.getClienteContratante().equals(unUsuario)){
				Avion unAvion = unVuelo.getTipoAvion();
				if(unAvion instanceof Gold) {
					mejorAvion = "El cliente ha contratado categoria Gold";
					break;
				}
				else if(unAvion instanceof Silver) {
					mejorAvion = "El cliente ha contratado hasta categoria Silver";
					silver = true;
				}
				else if(unAvion instanceof Bronze && !silver)
					mejorAvion = "El cliente ha contratado hasta categoria Bronze";
			}
		}

		return mejorAvion;
	}

	public static int solicitarInteger(){
		// solicita un numero por teclado y valida excepcion InputMismatchException
		int rta = -1;
		boolean continuar = false;
		Scanner teclado = new Scanner(System.in);
		do {
			try {
				rta = teclado.nextInt();
				continuar = false;
			}
			catch (InputMismatchException e){
				System.out.println("El valor ingresado no es un numero, reintente: ");
				teclado.nextLine();
				continuar = true;
			}
		}
		while(continuar);

		return rta;
	}

}


