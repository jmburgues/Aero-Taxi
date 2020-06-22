package com.company;

import java.io.Serializable;
import java.util.Objects;

public class Avion implements Serializable {
    private int combustible;
    private float costoXkm;
    private int capacidadMax;
    private int velocidadMax;
    private Propulsion propulsion;

    public Avion () {}

    public Avion (int  combustible,int capacidadMax, Propulsion propulsion)
    {
        this.capacidadMax = capacidadMax;
        this.combustible = combustible;
        this.costoXkm = (float) (Math.random() * (300 - 150) + 150);
        this.propulsion = propulsion;
        this.velocidadMax = propulsion.getVelocidadMax();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avion)) return false;
        Avion avion = (Avion) o;
        return combustible == avion.combustible &&
                Float.compare(avion.costoXkm, costoXkm) == 0 &&
                capacidadMax == avion.capacidadMax &&
                velocidadMax == avion.velocidadMax &&
                propulsion == avion.propulsion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(combustible, costoXkm, capacidadMax, velocidadMax, propulsion);
    }

    @Override
    public String toString() {
        return "Avion" +
                "combustible=" + combustible +
                ", costoXkm=" + costoXkm +
                ", capacidadMax=" + capacidadMax +
                ", velocidadMax=" + velocidadMax +
                ", propulsion=" + propulsion;
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
