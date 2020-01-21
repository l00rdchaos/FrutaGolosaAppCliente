package com.frutagolosa.fgapp.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface BorrarIntentoApi {
  @FormUrlEncoded
  @POST("/borrarpendiente.php")
  public void borrarpendiente(
          @Field("a") String a,

          Callback<Response> callback


  );
}
