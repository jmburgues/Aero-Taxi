package com.company;

public enum Propulsion {
    REACCION("Motor a reaccion",800),
    HELICE("Motor turbohelice",500),
    PISTONES("Motor a pistones",300);

    private String tipoMotor;
    private int velocidadMax;

    private Propulsion(String tipoMotor, int velocidadMax){
        this.tipoMotor = tipoMotor;
        this.velocidadMax = velocidadMax;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }
}
