package com.frutagolosa.fgapp.model;

import com.google.gson.annotations.SerializedName;

public class Motorizado {

    @SerializedName("id")
    private String id;

    @SerializedName("telefono")
    private String telefono;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("correo")
    private String correo;

    @SerializedName("ubicacion")
    private String ubicacion;

    @SerializedName("coordenadas")
    private String coordenadas;

    @SerializedName("foto")
    private String foto;

    public String getId() {
        return id;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public String getFoto() {
        return foto;
    }

}


