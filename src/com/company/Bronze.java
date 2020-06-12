package com.company;

public class Bronze extends Avion implements ServiciosABordo {
    private int tarifa;

    public Bronze (){}

    public Bronze (int  combustible, float costoXkm,int capacidadMax,int velocidadMax, Propulsion propulsion, boolean disponible)
    {
        super(combustible, costoXkm, capacidadMax, velocidadMax, propulsion, disponible);
        this.tarifa = 3000;
    }

    @Override
    public String toString() {
        return super.toString() + "Tarifa: " + tarifa;
    }

    @Override
    public void wifi() {
        System.out.println("El avion clase Bronze no posee servicio de WI-FI");
    }

    @Override
    public void catering() {
        System.out.println("El avion de clase Bronze no posee servicio de catering");
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
