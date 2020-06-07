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
}
