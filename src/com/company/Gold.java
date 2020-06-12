package com.company;

import java.util.Scanner;

public class Gold extends Avion implements ServiciosABordo {
    private boolean wifi;
    private int tarifa;


    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public int getTarifa() {
        return tarifa;
    }

    public Gold (){}

    public Gold (int  combustible, float costoXkm,int capacidadMax,int velocidadMax, Propulsion propulsion, boolean disponible, boolean wifi)
    {
        super(combustible, costoXkm, capacidadMax, velocidadMax, propulsion, disponible);
        this.wifi = wifi;
        this.tarifa = 6000;
    }


    @Override
    public String toString() {
        return "Gold: "+ super.toString() + "wifi=" + wifi + "Tarifa: " + tarifa;
    }

    @Override
    public void wifi() {
        if (this.wifi == true)
        {
            System.out.println("El avion tiene wifi");
        }
        else
        {
            System.out.println("Este avion no posee wifi");
        }
    }

    @Override
    public void catering() {
        System.out.println("El servicio de catering esta disponible en este avion. ¿Que menu desea?");
        Scanner dato = new Scanner(System.in);
        System.out.println("1- Menu adulto");
        System.out.println("2- Menu niño");
        System.out.println("3- Menu vegetariano");
        int a = dato.nextInt();
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
    public void setCapacidadMax(int capacidadMax) {
        super.setCapacidadMax(capacidadMax);
    }

    @Override
    public void setCombustible(int combustible) {
        super.setCombustible(combustible);
    }

    @Override
    public void setCostoXkm(float costoXkm) {
        super.setCostoXkm(costoXkm);
    }

    @Override
    public void setDisponible(boolean disponible) {
        super.setDisponible(disponible);
    }

    @Override
    public void setPropulsion(Propulsion propulsion) {
        super.setPropulsion(propulsion);
    }

    @Override
    public void setVelocidadMax(int velocidadMax) {
        super.setVelocidadMax(velocidadMax);
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @Override
    public float getCostoXkm() {
        return super.getCostoXkm();
    }

    @Override
    public int getCapacidadMax() {
        return super.getCapacidadMax();
    }

    @Override
    public int getCombustible() {
        return super.getCombustible();
    }

    @Override
    public int getVelocidadMax() {
        return super.getVelocidadMax();
    }

    @Override
    public Propulsion getPropulsion() {
        return super.getPropulsion();
    }
}
