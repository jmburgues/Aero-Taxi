package com.company;

import java.io.Serializable;

public abstract class Avion implements Serializable {
    private int combustible;
    private float costoXkm;
    private int capacidadMax;
    private int velocidadMax;
    private Propulsion propulsion;
    private boolean enVuelo;

    public Avion () {}

    public Avion (int  combustible,int capacidadMax, Propulsion propulsion, boolean disponible)
    {
        this.capacidadMax = capacidadMax;
        this.combustible = combustible;
        this.costoXkm = (float) (Math.random() * (300 - 150) + 150);
        this.propulsion = propulsion;
        this.velocidadMax = propulsion.getVelocidadMax();
        this.enVuelo = disponible;

    }

    @Override
    public String toString() {
        return "Avion" +
                "combustible=" + combustible +
                ", costoXkm=" + costoXkm +
                ", capacidadMax=" + capacidadMax +
                ", velocidadMax=" + velocidadMax +
                ", propulsion=" + propulsion +
                ", disponible=" + enVuelo
                ;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public void setCostoXkm(float costoXkm) {
        this.costoXkm = costoXkm;
    }

    public void setDisponible(boolean disponible) {
        this.enVuelo = disponible;
    }

    public void setPropulsion(Propulsion propulsion) {
        this.propulsion = propulsion;
    }

    public void setVelocidadMax(int velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public float getCostoXkm() {
        return costoXkm;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public int getCombustible() {
        return combustible;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public Propulsion getPropulsion() {
        return propulsion;
    }
}
