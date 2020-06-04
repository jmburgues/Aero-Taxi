package com.company;

import java.util.Date;
import java.util.ArrayList;

/*
    La clase Vuelo debe ser una clase privada anidada dentro de la clase AeroTaxi,
    porque un registro de tipo Vuelo solo le debe interesar a esta primer clase.
 */

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
        this.pasajeros = new ArrayList<Usuario>;
        // comparo los valores de origen y destino para establecer la distancia
    }

    public int agregarPasajero(Usuario pasajero){  // agrega un pasajero al vuelo y devuelve total de pasajeros
        int rta = -1;
            if(this.pasajeros.add(pasajeros))
                rta = this.pasajeros.size();
        return rta;
    }

    public boolean eliminarPasajero(Usuario pasajero){
        return this.pasajeros.remove(pasajero);
    }

    public obtenerDistancia(){

    }

    public int calcularCosto(){
        //implementar
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
                ", llegada: " + this.llegada + ", Avion: " + this.tipoAvion);
}

