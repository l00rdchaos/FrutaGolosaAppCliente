package com.frutagolosa.fgapp.api;

import com.frutagolosa.fgapp.model.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface apinterfaceConChooco {
    @GET
    Call<List<Contact>> getContacts(@Url String url);
}
