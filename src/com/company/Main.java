package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	ArrayList<Usuario> clientes = new ArrayList<Usuario>();
    	ArrayList<Avion> flotaAviones = new ArrayList<Avion>();
    	ArrayList<Vuelo> vuelosPactados = new ArrayList<Vuelo>();

    	/*
		cargar variables en memoria desde archivo
		 */
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
					/* metodo crear usuario */
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

}
