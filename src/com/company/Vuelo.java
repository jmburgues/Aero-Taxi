package com.company;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;

public class Vuelo implements Serializable {
    private static final long serialVersionUID = 500;
    public Ciudad origen;
    public Ciudad destino;
    public Avion tipoAvion;
    public LocalDate partida;
    public LocalDate llegada;
    public int cantPasajeros;
    public Usuario clienteContratante;

    public Vuelo(Ciudad origen, Ciudad destino, Avion tipoAvion, LocalDate partida, int cantPasajeros, Usuario clienteContratante) {
        this.origen = origen;
        this.destino = destino;
        this.tipoAvion = tipoAvion;
        this.partida = partida;
        this.llegada = partida.plusDays(this.obtenerDistancia() / this.tipoAvion.getVelocidadMax() / 60);
        this.cantPasajeros = cantPasajeros;
        this.clienteContratante = clienteContratante;
    }

    public int obtenerDistancia(){
		HashMap<Integer,Integer> tablaDistancias = new HashMap<>();

		tablaDistancias.put((Ciudad.BUE).hashCode()+(Ciudad.COR).hashCode(),695);
		tablaDistancias.put((Ciudad.BUE).hashCode()+(Ciudad.SCL).hashCode(),1400);
		tablaDistancias.put((Ciudad.BUE).hashCode()+(Ciudad.MVD).hashCode(),950);
		tablaDistancias.put((Ciudad.COR).hashCode()+(Ciudad.MVD).hashCode(),1190);
		tablaDistancias.put((Ciudad.COR).hashCode()+(Ciudad.SCL).hashCode(),1050);
		tablaDistancias.put((Ciudad.MVD).hashCode()+(Ciudad.SCL).hashCode(),2100);

		int clave = origen.hashCode() + destino.hashCode();

		return tablaDistancias.get(clave);
    }

    public float calcularCosto(){

        int tarifa = 0;
        if(tipoAvion instanceof Gold)
            tarifa = ((Gold) tipoAvion).getTarifa();
        else if(tipoAvion instanceof Silver)
            tarifa = ((Silver) tipoAvion).getTarifa();
        else if (tipoAvion instanceof Bronze)
            tarifa = ((Bronze) tipoAvion).getTarifa();

        return (( this.obtenerDistancia() * this.tipoAvion.getCostoXkm() ) + ( cantPasajeros * 3500 ) + tarifa );
    }

    public Avion getTipoAvion() {
        return tipoAvion;
    }


    public Ciudad getOrigen() {
        return origen;
    }

    public LocalDate getPartida(){
        return partida;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public Usuario getClienteContratante() {
        return clienteContratante;
    }

    @Override
    public String toString(){
        return ("[VUELO] " + this.origen + " - " + this.destino + ", Partida: " + this.partida + ", llegada: " + this.llegada + "\n" +
                "  - AVION: " + this.tipoAvion + ", Cantidad de Pasajeros: " + this.cantPasajeros + "\n" +
                "  - COSTO TOTAL: " + this.calcularCosto() + "\n");

    }
}

