package com.company;

public enum Ciudad {
    BUE("Buenos Aires"),
    COR("Cordoba"),
    MVD("Montevideo"),
    SCL("Santiago de Chile");

    private String nombre;

    Ciudad(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
