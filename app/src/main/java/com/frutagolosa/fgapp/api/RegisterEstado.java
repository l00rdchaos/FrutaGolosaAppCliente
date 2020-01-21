package com.frutagolosa.fgapp.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface RegisterEstado {




  @FormUrlEncoded
  @POST("/BorrarPedidoCliente.php")
  public void inserBorrado(
          @Field("a") String a,
          @Field("b") String b,


          Callback<Response> callback


  );

}
