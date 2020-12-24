package com.frutagolosa.fgapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post2 {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("amountWithoutTax")
    @Expose
    private Integer amountWithoutTax;



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountWithoutTax() {
        return amountWithoutTax;
    }

    public void setAmountWithoutTax(Integer amountWithoutTax) {
        this.amountWithoutTax = amountWithoutTax;
    }

    public String getClientTransactionId() {
        return clientTransactionId;
    }

    public void setClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Post2(String phoneNumber, String countryCode, Integer amount, Integer amountWithoutTax, String clientTransactionId, String storeId) {
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.amount = amount;
        this.amountWithoutTax = amountWithoutTax;
        this.clientTransactionId = clientTransactionId;
        this.storeId = storeId;
    }

    @SerializedName("clientTransactionId")
    @Expose
    private String clientTransactionId;

    @SerializedName("storeId")
    @Expose
    private String storeId;


}
