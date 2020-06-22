package com.company;

import java.io.Serializable;
import java.util.Objects;

public class Bronze extends Avion implements Serializable {
    private int tarifa;

    public Bronze (){}

    public Bronze (int combustible,int capacidadMax, Propulsion propulsion)
    {
        super(combustible, capacidadMax, propulsion);
        this.tarifa = 3000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bronze)) return false;
        if (!super.equals(o)) return false;
        Bronze bronze = (Bronze) o;
        return tarifa == bronze.tarifa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tarifa);
    }

    @Override
    public String toString() {
        return super.toString() + ", Tarifa: " + tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
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

    public int getTarifa() {
        return tarifa;
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
