package com.frutagolosa.fgapp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class Pedido implements Serializable {
    private String precio_arreglo;
    private String nombre_arreglo;
    private String tipo_arreglo;


    private String nombre_recibe;
    private String telefono_recibe;
    private String dia_entrega;
    private String franja_horaria;
    private String ciudad;

    private String precio_viaje;
    private String sector;
    private String coordenadas;

    private String calle_principal;
    private String calle_secundaria;
    private String referencia;
    private String detalle_ubicacion;
    private String detaAgg;
    private String motivo;
    private String numeracion;
    private String Especificacion;

    private String precioTotal;
    private String tipo;

    private String banco;
    private String ppa;


}