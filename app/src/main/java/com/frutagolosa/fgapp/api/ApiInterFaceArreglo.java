package com.frutagolosa.fgapp.api;

import java.util.List;
import com.frutagolosa.fgapp.model.Arreglos;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterFaceArreglo {
    @GET
    Call<List<Arreglos>> getArreglos(@Url String url);
}
