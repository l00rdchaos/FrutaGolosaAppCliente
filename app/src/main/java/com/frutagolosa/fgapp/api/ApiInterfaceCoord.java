package com.frutagolosa.fgapp.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface ApiInterfaceCoord {
  @FormUrlEncoded
  @POST("/ObtenCoord.php")
  public void traecoord(

          @Field("m") String m,


          Callback<Response> callback
  );
}
