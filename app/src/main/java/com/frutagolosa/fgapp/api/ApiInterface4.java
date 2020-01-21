package com.frutagolosa.fgapp.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface ApiInterface4 {

  @FormUrlEncoded
  @POST("/compras.php")
  public void comprarver(

          @Field("c") String c,
          @Field("t") String t,


          Callback<Response> callback
  );
}
