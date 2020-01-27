package com.frutagolosa.fgapp.api;

import com.frutagolosa.fgapp.model.horas;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface apiInterfaceFranjas {

    @GET
    Call<List<horas>> getHora(@Url String url);
}
