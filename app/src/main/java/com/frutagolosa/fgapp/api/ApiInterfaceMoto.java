package com.frutagolosa.fgapp.api;

import com.frutagolosa.fgapp.model.Motorizado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterfaceMoto {
  @GET
  Call<List<Motorizado>> getContacts(@Url String url);
}
