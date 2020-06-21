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
					bre
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
		LocalDate fecha = solicitarFecha();
		System.out.println("Seleccione origen:");
		System.out.println("1- Buenos aires \n" +
				"2- Cordoba \n" +
				"3- Montevideo \n" +
				"4- Santiago de chile \n");
		Scanner nuevo = new Scanner(System.in);
		int num = nuevo.nextInt();
		Ciudad origen;
		Ciudad destino;
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
		}
		Main.mostrarAvionesDisp(vuelosPactados, aviones, fecha);
		Avion reservado = new Avion();
		reservado = Main.reservar();
		int cantpas = Main.cantPasajeros(reservado);
		int dni = Main.solicitarDni();
		Usuario nuevoUser = new Usuario();
		nuevoUser = Main.obtenerUsr(usuarios, dni);
		Vuelo vuelonuevo = new Vuelo(origen, destino, reservado, llegada, cantpas, nuevoUser);
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

	public static int cantPasajeros (Avion reservado)
	{
		int i = 1;
		int flag = 0;
		while (flag == 0) {
			System.out.println("¿Numero de acompañantes?");
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

	public static Avion reservar(ArrayList<Avion>flotaAviones, int avion) {
		Scanner aviondes = new Scanner(System.in);
		int flag = 0;
		while (flag == 0) {                                                    //Pide el numero de avion a reservar
			System.out.println("Ingrese el numero del avion disponible que desee:");
			int avion = aviondes.nextInt();                                    //Si ya esta reservado te pide que busques otro
			if (flotaAviones.get(avion).setEnVuelo(true)) {
				System.out.println("El avion esta ocupado en esa fecha, elija otro");
			} else                                                            //Si no lo reserva cambiando enVuelo a true
			{
				System.out.println("Avion elegido: " + flotaAviones.get(avion).toString());
				flotaAviones.get(avion).setEnVuelo(true);
				flag = 1;
			}
		}																	
		return flotaAviones.get(avion); 		 		 		 	 		//Devuelve el avion que se reservo

	}
	/*
	//Tratar de que solo muestre los aviones disponibles
	public static void mostrarAvionesDisp(ArrayList<Avion> flotaAviones, ArrayList<Vuelo> vuelosPactados, LocalDate fecha) {
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
						flotaAviones.get(i). = true;
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
	}*/

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