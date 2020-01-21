package com.frutagolosa.fgapp.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface RegisterImage {


  @FormUrlEncoded
  @POST("/Upload.php")
  public void InstarIMG(
          @Field("imagen") String imagen,
          @Field("nombre") String nombre,

          Callback<Response> callback


  );

}
