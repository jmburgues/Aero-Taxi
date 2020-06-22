package com.company;

import java.lang.reflect.Array;
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
					System.out.println("que fechad deseas viajar?");
					Main.contratarVuelo(vuelosPactados, flotaAviones, baseClientes);
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
		while (dniInvalido || (dni < 1000000 && dni > 60000000));

		return dni;
	}

	public static Usuario obtenerUsr(ArrayList<Usuario> baseClientes, int dni) {
		Usuario unUsuario = null;
		boolean usrRegistrado = false;

		if (!baseClientes.isEmpty()) {
			for (Usuario auxUsuario : baseClientes) {
				if (auxUsuario != null && auxUsuario.getDni() == dni) {
					usrRegistrado = true;
					break;
				}
			}
		}
				/*
				Aqui se puede implementar control por contraseña
				 */
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

	public static void contratarVuelo(ArrayList<Vuelo> vuelosPactados, ArrayList<Avion> aviones, ArrayList<Usuario> usuarios) {
		System.out.println("Ingrese fecha de partida (aaaa-mm-dd): ");
		LocalDate fecha = Main.solicitarFecha();
		LocalDate llegada = fecha;
		System.out.println("Seleccione origen:");
		System.out.println("1- Buenos aires \n" +
				"2- Cordoba \n" +
				"3- Montevideo \n" +
				"4- Santiago de chile \n");
		Scanner nuevo = new Scanner(System.in);
		int num = nuevo.nextInt();
		Ciudad origen = null;
		Ciudad destino = null;
		switch (num){
			case 1:
				origen = Ciudad.BUE;
				System.out.println("Seleccione destino: ");
				System.out.println("1- Cordoba \n" +
						"2- Montevideo \n" +
						"3- Santiago de chile \n");
				int uno;
				uno = nuevo.nextInt();
				switch (uno)
				{
					case 1:
						destino = Ciudad.COR;
						break;
					case 2:
						destino = Ciudad.MVD;
						break;
					case 3:
						destino = Ciudad.SCL;
						break;

				}
				break;
			case 2:
				origen = Ciudad.COR;
				System.out.println("Seleccione destino:");
				System.out.println("1- Buenos Aires \n" +
						"2- Montevideo \n" +
						"3- Santiago de chile \n");
				int dos;
				dos = nuevo.nextInt();
				switch (dos)
				{
					case 1:
						destino = Ciudad.BUE;
						break;
					case 2:
						destino = Ciudad.MVD;
						break;
					case 3:
						destino = Ciudad.SCL;
						break;

				}
				break;
			case 3:
				origen = Ciudad.MVD;
				System.out.println("Seleccione destino: ");
				System.out.println("1- Cordoba \n" +
						"2- Buenos Aires \n" +
						"3- Santiago de chile \n");
				int tres;
				tres = nuevo.nextInt();
				switch (tres)
				{
					case 1:
						destino = Ciudad.COR;
						break;
					case 2:
						destino = Ciudad.BUE;
						break;
					case 3:
						destino = Ciudad.SCL;
						break;

				}
				break;
			case 4:
				origen = Ciudad.SCL;
				System.out.println("Seleccione destino: ");
				System.out.println("1- Cordoba \n" +
						"2- Montevideo \n" +
						"3- Buenos Aires \n");
				int cua;
				cua = nuevo.nextInt();
				switch (cua)
				{
					case 1:
						destino = Ciudad.COR;
						break;
					case 2:
						destino = Ciudad.MVD;
						break;
					case 3:
						destino = Ciudad.BUE;
						break;

				}
				break;
			default:

		}
		ArrayList<Integer>IDSarray = Main.mostrarAvionesDisp(aviones, vuelosPactados, fecha);
		Avion reservado = Main.reservar(aviones, IDSarray);
		int cantpas = Main.cantPasajeros(reservado);
		int dni = Main.solicitarDni();
		Usuario nuevoUser = Main.obtenerUsr(usuarios, dni);
		Vuelo vueloNuevo = new Vuelo(origen, destino, reservado, fecha, llegada, cantpas, nuevoUser);
		float costo = vueloNuevo.calcularCosto();
		System.out.println("El costo todal del vuelo es de :" + costo);
		System.out.println("¿Que desea realizar?\n" +
				"1- Contratar. \n" +
				"2- Cancelar.");
		Scanner conf = new Scanner(System.in);
		int confirmacion = conf.nextInt();
		if (confirmacion == 1) {
			System.out.println("Tu vuelo se ha reservado con exito, detalles del vuelo:" + vueloNuevo.toString());
			vuelosPactados.add(vueloNuevo);
		}
		else {
			System.out.println("Vuelo cancelado con exito");
		}
	}


	public static int cantPasajeros (Avion reservado)
	{
		int i = 1;
		int flag = 0;
		while (flag == 0) {
			System.out.println("¿Numero de acompañantes? maximo de personas en este avion: " + reservado.getCapacidadMax());
			Scanner acomp = new Scanner(System.in);
			int num = acomp.nextInt();
			if (num+1 > reservado.getCapacidadMax())
			{
				System.out.println("Numero de pasajeros excede la capacidad maxima");
			}
			else
			{
				i = i + num;
				flag = 1;
			}
		}
		return i;
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

	public static Avion reservar(ArrayList<Avion>flotaAviones, ArrayList<Integer> listaIDS) {
		Scanner aviondes = new Scanner(System.in);
		int id;
		//Pide el numero de avion a reservar
		int flag = 0;
		System.out.println("Ingrese el ID del avion que desee:");
		do {
			id = aviondes.nextInt();
			flag = Main.comprobarId(id,listaIDS);
			if(flag == 0)
			{
				System.out.println("El numero ingresado no es un id valido de un avion disponible. Vuelva a ingresar un id valido:");
			}
		}while (flag == 0);
		return flotaAviones.get(id); 		 		 		 	 		//Devuelve el avion que se reservo

	}
	public static int comprobarId(int id, ArrayList<Integer> listaIDs)
	{
		int flag = 0;
		for (int i = 0; i< listaIDs.size(); i++){
			if(id == listaIDs.get(i))
			{
				flag++;
			}
		}
		return flag;
	}

	//Tratar de que solo muestre los aviones disponibles
	public static ArrayList<Integer> mostrarAvionesDisp(ArrayList<Avion> flotaAviones, ArrayList<Vuelo> vuelosPactados, LocalDate fecha) {
		int a = flotaAviones.size();
		int j = vuelosPactados.size();
		ArrayList<Integer> Ides = new ArrayList<>();

		for (int i = 0; i < a; i++)      //recorre la flota de aviones
		{
			int flag = 0;
			for(int p = 0; p<j && flag == 0; p++)                                              // no entra al while
			{
				if (flotaAviones.get(i).equals(vuelosPactados.get(p).tipoAvion))           //si el avion esta en la lista de vuelos pactados se fija si en esa fecha esta ocupado
				{
					if (fecha.compareTo(vuelosPactados.get(p).partida) == 0) {                    //si esta ocupado flag cambia a 1
						flag = 1;
					}
				}
			}
			if (flag == 0)
			{
				System.out.println("Avion id n* "+ i);
				if (flotaAviones.get(i) instanceof Gold)
				{
					System.out.println("Avion tipo GOlD");
				}
				if (flotaAviones.get(i) instanceof Bronze)
				{
					System.out.println("Avion tipo BRONZE");
				}
				if (flotaAviones.get(i) instanceof Silver)
				{
					System.out.println("Avion tipo SILVER");
				}
				System.out.println(flotaAviones.get(i).toString());
				Ides.add(i);
			}

		}
		return Ides;
	}

	public static void listarClientes(ArrayList<Usuario> baseClientes, ArrayList<Vuelo> vuelosPactados) {
		if (!baseClientes.isEmpty()) {
			for (Usuario auxCliente : baseClientes) {
				System.out.println(auxCliente);
				System.out.println(mejorAvionUsado(vuelosPactados,auxCliente));
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
}

/*
* FALTA PONER UN TOPE DE PASAJEROS EN EL AVION
*
* */