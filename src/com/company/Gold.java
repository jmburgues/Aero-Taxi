package com.company;

import java.io.Serializable;
import java.util.Scanner;

public class Gold extends Avion implements Serializable {
    private boolean wifi;
    private boolean catering;
    private int tarifa;


    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public int getTarifa() {
        return tarifa;
    }

    public Gold (){}

    public Gold (int combustible,int capacidadMax,Propulsion propulsion, boolean enVuelo, boolean wifi, boolean catering)
    {
        super(combustible, capacidadMax, propulsion);
        this.wifi = wifi;
        this.catering = catering;
        this.tarifa = 6000;
    }

    @Override
    public String toString() {
        return "Gold: "+ super.toString() + "wifi=" + wifi + "Tarifa: " + tarifa;
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
