package com.company;

public abstract class Avion {
    private int combustible;
    private float costoXkm;
    private int capacidadMax;
    private int velocidadMax;
    private Propulsion propulsion;
    private boolean disponible;

    public Avion () {}

    public Avion (int  combustible, float costoXkm,int capacidadMax,int velocidadMax, Propulsion propulsion, boolean disponible)
    {
        this.capacidadMax = capacidadMax;
        this.combustible = combustible;
        this.costoXkm = costoXkm;
        this.velocidadMax = velocidadMax;
        this.propulsion = propulsion;
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Avion" +
                "combustible=" + combustible +
                ", costoXkm=" + costoXkm +
                ", capacidadMax=" + capacidadMax +
                ", velocidadMax=" + velocidadMax +
                ", propulsion=" + propulsion +
                ", disponible=" + disponible
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
        this.disponible = disponible;
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
