package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	/* CORRECCIONES:
	Verificar la utilidad del boolean "enVuelo" de la clase Avion.
	Para comprobar si esta disponible en una fecha dada debería implementarse una busqueda
	en vuelosPactados y si existe devolver false. El atributo de clase enVuelo no serviría.
	 */

    public static void main(String[] args) {

    	// declaro bases de datos en memoria
		ArrayList<Usuario> baseClientes = new ArrayList<>();
		ArrayList<Avion> flotaAviones = new ArrayList<>();
		ArrayList<Vuelo> vuelosPactados = new ArrayList<>();

		// leo persistencias en archivos y cargo las bases en memoria
		File archivo = new File("baseDatos.dat");
		try {
			if (archivo.exists()) {
				FileInputStream flujoEntrada = new FileInputStream(archivo);
				ObjectInputStream entradaObjeto = new ObjectInputStream(flujoEntrada);

				boolean finArchivo = false;
				while (!finArchivo) {
					Object leido = entradaObjeto.readObject();
					if (leido != null) {
						if (leido instanceof Usuario)
							baseClientes.add((Usuario) leido);
						else if (leido instanceof Avion)
							flotaAviones.add((Avion) leido);
						else if (leido instanceof Vuelo)
							vuelosPactados.add((Vuelo) leido);
					} else
						finArchivo = true;
				}
			}
			else { // si no hay archivo, cargo datos de ejemplo
				flotaAviones.add(new Gold(10000, 30, Propulsion.REACCION, false, true, true));
				flotaAviones.add(new Gold(7000, 20, Propulsion.PISTONES, false, true, true));
				flotaAviones.add(new Silver(3000, 15, Propulsion.HELICE, false, true));
			}
		}catch(IOException | ClassNotFoundException e){
			System.out.println("No se puede leer la base de datos: " + e.getMessage());
			e.printStackTrace();
		}
	/*	finaly{
			// cerrar archivo
		}
	*/
		// Interfaz del usuario
		System.out.println("Sistema de Contratación de Vuelos << AERO-TAXI >>\n");

		int dni = Main.solicitarDni();
		Usuario usuarioAutorizado = obtenerUsr(baseClientes,dni);
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
					/* metodo ver vuelos programados */
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

			if(!baseClientes.isEmpty()) {
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
			if(baseClientes.isEmpty() || !usrRegistrado){
				unUsuario = Main.nuevoUsuario();
				baseClientes.add(unUsuario);
			}

			return unUsuario;
		}

    public static Usuario nuevoUsuario(){

    	Scanner teclado = new Scanner(System.in);
    	String nombre, apellido;
    	int dni, edad;

    	while(true){
			try {
				System.out.println("Ingrese primer nombre: ");
				nombre = teclado.nextLine();
				System.out.println("Ingrese apellido: ");
				apellido = teclado.nextLine();
				System.out.println("Ingrese DNI: ");
				dni = teclado.nextInt();
				System.out.println("Ingrese edad: ");
				edad = teclado.nextInt();

				return new Usuario(nombre,apellido,dni,edad);

			} catch (InputMismatchException e) {
				System.out.println("Los datos ingresados son incorrectos: (" + e + ")");
				teclado.nextLine(); // flush del buffer
			}
		}
	}

	public static void contratarVuelo(){
    	Scanner teclado = new Scanner(System.in);
    	System.out.println("Ingrese fecha de viaje (dd/mm/aaaa): ");
    	String fecha = teclado.nextLine();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

		/**
		 *
		 *  CONTINUAR
		 */

	}

	public static void listarClientes(ArrayList<Usuario> base) {
		if (!base.isEmpty()) {
			for (Usuario aux : base) {
				System.out.println(aux);
			}
		} else
			System.out.println("La base de clientes está vacía.");
	}
}
