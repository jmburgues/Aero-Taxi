package com.company;

import java.util.Date;
import java.util.ArrayList;

public class Vuelo {
    public Ciudad origen;
    public Ciudad destino;
    public Avion tipoAvion;
    public Date partida;
    public Date llegada;
    public ArrayList<Usuario> pasajeros;

    public Vuelo(Ciudad origen, Ciudad destino, Avion tipoAvion, Date partida, Date llegada) {
        this.origen = origen;
        this.destino = destino;
        this.tipoAvion = tipoAvion;
        this.partida = partida;
        this.llegada = llegada;
        this.pasajeros = new ArrayList<Usuario>();
    }

    public int agregarPasajero(Usuario pasajero){
        // agrega un pasajero al vuelo y devuelve total de pasajeros o -1 si no pudo agregar
        int rta = -1;
            if(this.pasajeros.add(pasajero))
                rta = this.pasajeros.size();
        return rta;
    }

    public boolean eliminarPasajero(Usuario pasajero){
        return this.pasajeros.remove(pasajero);
    }

    public int obtenerDistancia(){
        // Devuelve distancia en Km o -1 si no existe destino.

        int distancia = 0;

        /*
        Modificar equals por ==
         */

        if(origen.equals(Ciudad.BUE)){
            if(destino.equals(Ciudad.COR))
                distancia = 695;
            if(destino.equals(Ciudad.SCL))
                distancia = 1400;
            if(destino.equals(Ciudad.MVD))
                distancia = 950;
            else
                distancia = -1;
        }
        else if(origen.equals(Ciudad.COR)){
            if(destino.equals(Ciudad.MVD))
                distancia = 1190;
            if(destino.equals(Ciudad.SCL))
                distancia = 1050;
        }
        else if(origen.equals(Ciudad.MVD)){
            if(destino.equals(Ciudad.SCL))
                distancia = 2100;
            else
                distancia = -1;
        }
        else if(origen.equals(Ciudad.SCL)){
            distancia = -1;
        }

        return distancia;
    }

    public float calcularCosto(){

        int tarifa = 0;
        if(tipoAvion instanceof Gold)
            tarifa = ((Gold) tipoAvion).getTarifa();
        else if(tipoAvion instanceof Silver)
            tarifa = ((Silver) tipoAvion).getTarifa();
        else if (tipoAvion instanceof Bronze)
            tarifa = ((Bronze) tipoAvion).getTarifa();

        return (( this.obtenerDistancia() * this.tipoAvion.getCostoXkm() ) + ( this.pasajeros.size() * 3500 ) + tarifa );
    }

    public int cantidadPasajeros(){
        return this.pasajeros.size();
    }

    public void listarPasajeros(){
        for(Usuario unUsuario : pasajeros){
            System.out.println(unUsuario);
        }
    }

    public Avion getTipoAvion() {
        return tipoAvion;
    }

    public void setTipoAvion(Avion tipoAvion) {
        this.tipoAvion = tipoAvion;
    }

    public Date getPartida() {
        return partida;
    }

    public void setPartida(Date partida) {
        this.partida = partida;
    }

    public Date getLlegada() {
        return llegada;
    }

    public void setLlegada(Date llegada) {
        this.llegada = llegada;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    @Override
    public String toString(){
        return ("[VUELO] " + this.origen + " - " + this.destino + ": Partida: " + this.partida +
                ", llegada: " + this.llegada + ", Avion: " + this.tipoAvion + ", Cantidad de Pasajeros: " + this.cantidadPasajeros());
    }
}

