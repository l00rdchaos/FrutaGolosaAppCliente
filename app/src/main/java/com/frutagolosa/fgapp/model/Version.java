package com.frutagolosa.fgapp.model;

import com.google.gson.annotations.SerializedName;

public class Version {

    @SerializedName("version")
    private String version;

    public String getVersion() {
        return version;
    }
}
