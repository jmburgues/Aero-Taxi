package com.company;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 400;
    private String nombre;
    private String apellido;
    private int dni;
    private int edad;

    public Usuario(){}

    public Usuario (String nombre,String apellido, int dni, int edad){
        this.nombre=nombre;
        this.apellido=apellido;
        this.dni=dni;
        this.edad=edad;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public int getEdad() {
        return edad;
    }
    public int getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }

    public String toString(){
        return "[USUARIO] Nombre: " + this.nombre + " " + this.apellido +
                ", Edad: " + this.edad + ", DNI: " + this.dni ;
    }
}
