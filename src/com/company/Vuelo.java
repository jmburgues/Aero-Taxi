package com.company;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Vuelo implements Serializable {
    public Ciudad origen;
    public Ciudad destino;
    public Avion tipoAvion;
    public LocalDateTime partida;
    public LocalDateTime llegada;
    public ArrayList<Usuario> pasajeros;

    public Vuelo(Ciudad origen, Ciudad destino, Avion tipoAvion, LocalDateTime partida) {
        this.origen = origen;
        this.destino = destino;
        this.tipoAvion = tipoAvion;
        this.partida = partida;
        this.llegada = partida.plusHours(this.obtenerDistancia() / this.tipoAvion.getVelocidadMax());
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
		HashMap<Integer,Integer> tablaDistancias = new HashMap<Integer, Integer>();

		tablaDistancias.put((Ciudad.BUE).hashCode()+(Ciudad.COR).hashCode(),695);
		tablaDistancias.put((Ciudad.BUE).hashCode()+(Ciudad.SCL).hashCode(),1400);
		tablaDistancias.put((Ciudad.BUE).hashCode()+(Ciudad.MVD).hashCode(),950);
		tablaDistancias.put((Ciudad.COR).hashCode()+(Ciudad.MVD).hashCode(),1190);
		tablaDistancias.put((Ciudad.COR).hashCode()+(Ciudad.SCL).hashCode(),1050);
		tablaDistancias.put((Ciudad.MVD).hashCode()+(Ciudad.SCL).hashCode(),2100);

		int clave = origen.hashCode() + destino.hashCode();

		return tablaDistancias.get(clave).intValue();
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

    public LocalDateTime getPartida() {
        return partida;
    }

    public void setPartida(LocalDateTime partida) {
        this.partida = partida;
    }

    public LocalDateTime getLlegada() {
        return llegada;
    }

    public void setLlegada(LocalDateTime llegada) {
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

