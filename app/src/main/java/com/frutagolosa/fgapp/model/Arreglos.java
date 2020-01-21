package com.frutagolosa.fgapp.model;

import com.google.gson.annotations.SerializedName;

public class Arreglos {

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("valor")
    private String valor;

    @SerializedName("Tipo")
    private String tipo;


    public String getNombre() {
        return nombre;
    }


    public String getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }



}
