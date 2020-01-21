package com.frutagolosa.fgapp.api;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
public interface RegisterAPI2 {


  @FormUrlEncoded
  @POST("/insertcliente.php")
  public void inserCliente(
          @Field("telefono") String telefono,
          @Field("nombre") String nombre,
          @Field("correo") String correo,

          Callback<Response> callback


  );
}
