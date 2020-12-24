package com.frutagolosa.fgapp.api;

import com.frutagolosa.fgapp.model.BienResponse;
import com.frutagolosa.fgapp.model.Post2;
import com.frutagolosa.fgapp.model.PostUser;
import com.frutagolosa.fgapp.model.idclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {





    @FormUrlEncoded
    @POST("Sale")
    Call<BienResponse> getPost(@Header("Authorization") String authHeader,
                               @Field("phoneNumber") String phoneNumber,
                               @Field("countryCode") String countryCode,
                               @Field("amount") Integer amount,
                               @Field("amountWithoutTax") Integer amountWithoutTax,
                               @Field("clientTransactionId") String clientTransactionId,
                               @Field("storeId") String storeId
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("Sale/{id}")
    Call<idclient> getIdUser(@Header("Authorization") String authHeader,
                             @Path("id") String id);



    @GET("Users/{number}/region/{prefix}")
    Call<PostUser> getUser(@Header("Authorization") String authHeader,
                           @Path("number") String number,
                           @Path("prefix") int prefix);




}
