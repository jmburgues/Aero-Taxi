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
    	ArrayList<Usuario> clientes = new ArrayList<>();
    	ArrayList<Avion> flotaAviones = new ArrayList<>();
    	ArrayList<Vuelo> vuelosPactados = new ArrayList<>();

    	/*
		codigo para operaciones de carga de variables en memoria desde archivo
		 */

		if(flotaAviones.isEmpty()){
		flotaAviones.add(new Gold(10000,30,Propulsion.REACCION,false,true,true));
		flotaAviones.add(new Gold(7000,20,Propulsion.PISTONES,false,true,true));
		flotaAviones.add(new Silver(3000,15,Propulsion.HELICE,false,true));
		}

    	Scanner teclado = new Scanner(System.in);
		int opcionMenu;

    	do {
			System.out.println("Sistema de Contratación de Vuelos << AERO-TAXI >>\n" +
					"1- Crear usuario.\n" +
					"2- Contratar vuelo.\n" +
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
					clientes.add(Main.nuevoUsuario());
					break;
				case 2:
					/* metodo contratar vuelo */
					break;
				case 3:
					/* metodo ver base de clientes */
					break;
				case 4:
					/* metodo ver vuelos programados */
					break;
				default:
					break;
			}
		}
    	while(opcionMenu != 5);
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
				System.out.println("Los datos ingresados son incorrectos: (Error:  " + e + " )");
				teclado.nextLine(); // flush del buffer
			}
		}
	}
}
