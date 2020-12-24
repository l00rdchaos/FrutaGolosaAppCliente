package com.frutagolosa.fgapp.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BienResponse {



    @SerializedName("transactionId")
    @Expose
    private int transactionId;


    public int getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "BienResponse{" +
                "transactionId=" + transactionId +
                '}';
    }
}



