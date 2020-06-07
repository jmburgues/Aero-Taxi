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
}
