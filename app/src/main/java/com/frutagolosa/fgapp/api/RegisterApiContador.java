package com.frutagolosa.fgapp.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit2.http.GET;

public interface RegisterApiContador {

    @FormUrlEncoded
    @POST("/contadores.php")
    public void inserContador(
            @Field("a") String contadorpedidos,
            @Field("b") String telefono,
            @Field("c") String correo,


            Callback<Response> callback


    );
}