package com.company;

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

		ArrayList<Usuario> baseClientes = new ArrayList<>();
		ArrayList<Avion> flotaAviones = new ArrayList<>();
		ArrayList<Vuelo> vuelosPactados = new ArrayList<>();

    	/*
			codigo para operaciones de carga de variables en memoria desde archivo
		 */

		if (flotaAviones.isEmpty()) {
			flotaAviones.add(new Gold(10000, 30, Propulsion.REACCION, false, true, true));
			flotaAviones.add(new Gold(7000, 20, Propulsion.PISTONES, false, true, true));
			flotaAviones.add(new Silver(3000, 15, Propulsion.HELICE, false, true));
		}

		System.out.println("Sistema de Contratación de Vuelos << AERO-TAXI >>\n");

		Usuario usrLogin = Main.authLogin(baseClientes);

		if (usrLogin != null) {
			Scanner teclado = new Scanner(System.in);
			int opcionMenu;

			do {
				System.out.println("1- Contratar vuelo.\n" +
						"2- Cancelar vuelo.\n" +
						"3- Ver base de clientes.\n" +
						"4- Ver vuelos programados.\n" +
						"5- Salir");

				try {
					opcionMenu = teclado.nextInt();
				} catch (InputMismatchException mensaje) {
					System.out.println("El valor ingresado debe ser un numero");
					opcionMenu = 0;
				}

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
					default:
						break;
				}
			}
			while (opcionMenu != 5);
		}
	}

    public static Usuario authLogin(ArrayList<Usuario> baseClientes){

			Scanner teclado = new Scanner(System.in);

			int dni = 0;
			boolean dniInvalido;

			do{
				try {
					System.out.println("Ingrese su DNI: ");
					dni = teclado.nextInt();
					dniInvalido = false;
				} catch (InputMismatchException e) {
					System.out.println("Ingrese un valor numerico valido.");
					dniInvalido = true;
				}
			}
			while(dniInvalido);

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
    	System.out.println("Ingrese fecha: ");
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
