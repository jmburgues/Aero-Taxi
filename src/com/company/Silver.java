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

    @Override
    public void setVelocidadMax(int velocidadMax) {
        super.setVelocidadMax(velocidadMax);
    }

    @Override
    public void setPropulsion(Propulsion propulsion) {
        super.setPropulsion(propulsion);
    }

    @Override
    public void setDisponible(boolean disponible) {
        super.setDisponible(disponible);
    }

    @Override
    public void setCostoXkm(float costoXkm) {
        super.setCostoXkm(costoXkm);
    }

    @Override
    public void setCombustible(int combustible) {
        super.setCombustible(combustible);
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public void setCapacidadMax(int capacidadMax) {
        super.setCapacidadMax(capacidadMax);
    }

    @Override
    public Propulsion getPropulsion() {
        return super.getPropulsion();
    }

    @Override
    public int getVelocidadMax() {
        return super.getVelocidadMax();
    }

    @Override
    public int getCombustible() {
        return super.getCombustible();
    }

    @Override
    public int getCapacidadMax() {
        return super.getCapacidadMax();
    }

    @Override
    public float getCostoXkm() {
        return super.getCostoXkm();
    }

    public int getTarifa() {
        return tarifa;
    }
}
