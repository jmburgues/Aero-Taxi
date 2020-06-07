package com.company;

import java.util.Scanner;

public class Silver extends Avion implements ServiciosABordo {
    private int tarifa;

    public Silver(){}

    public Silver (int  combustible, float costoXkm,int capacidadMax,int velocidadMax, Propulsion propulsion, boolean disponible)
    {
        super(combustible, costoXkm, capacidadMax, velocidadMax, propulsion, disponible);
        this.tarifa = 4000;
    }

    @Override
    public String toString() {
        return super.toString() + "tarifa=" + tarifa;
    }

    @Override
    public void wifi() {
        System.out.println("El avion de clase Silver no posee servicio de WI-FI");
    }

    @Override
    public void catering() {
        System.out.println("El servicio de catering esta disponible en este avion. ¿Que menu desea?");
        int a;
        Scanner dato = new Scanner(System.in);
        System.out.println("1- Menu adulto");
        System.out.println("2- Menu niño");
        System.out.println("3- Menu vegetariano");
        a = dato.nextInt();
        switch (a){
            case 1:
                System.out.println("Se ha entregado un menu de adulto");
                break;
            case 2:
                System.out.println("Se ha entregado un menu de niño");
                break;
            case 3:
                System.out.println("Se ha entregado un menu vegetariano");
                break;
            default:
                System.out.println("Ingresar una opcion valida");
                break;
        }
    }
}
