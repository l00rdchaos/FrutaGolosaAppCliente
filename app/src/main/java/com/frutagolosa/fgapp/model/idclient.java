package com.frutagolosa.fgapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class idclient {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("cardType")
    @Expose
    private String cardType;
    @SerializedName("clientUserId")
    @Expose
    private String clientUserId;
    @SerializedName("processor")
    @Expose
    private String processor;
    @SerializedName("bin")
    @Expose
    private String bin;
    @SerializedName("lastDigits")
    @Expose
    private String lastDigits;
    @SerializedName("deferredCode")
    @Expose
    private String deferredCode;
    @SerializedName("deferredMessage")
    @Expose
    private String deferredMessage;
    @SerializedName("deferred")
    @Expose
    private boolean deferred;
    @SerializedName("cardBrandCode")
    @Expose
    private String cardBrandCode;
    @SerializedName("cardBrand")
    @Expose
    private String cardBrand;
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("clientTransactionId")
    @Expose
    private String clientTransactionId;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("transactionStatus")
    @Expose
    private String transactionStatus;
    @SerializedName("authorizationCode")
    @Expose
    private String authorizationCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("messageCode")
    @Expose
    private int messageCode;
    @SerializedName("transactionId")
    @Expose
    private int transactionId;
    @SerializedName("document")
    @Expose
    private String document;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("optionalParameter1")
    @Expose
    private String optionalParameter1;
    @SerializedName("optionalParameter2")
    @Expose
    private String optionalParameter2;
    @SerializedName("optionalParameter3")
    @Expose
    private String optionalParameter3;
    @SerializedName("optionalParameter4")
    @Expose
    private String optionalParameter4;
    @SerializedName("storeName")
    @Expose
    private String storeName;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("regionIso")
    @Expose
    private String regionIso;
    @SerializedName("transactionType")
    @Expose
    private String transactionType;
    @SerializedName("recap")
    @Expose
    private String recap;

    /**
     * No args constructor for use in serialization
     *
     */
    public idclient() {
    }

    /**
     *
     * @param clientUserId
     * @param date
     * @param deferred
     * @param deferredCode
     * @param authorizationCode
     * @param bin
     * @param document
     * @param recap
     * @param clientTransactionId
     * @param deferredMessage
     * @param currency
     * @param storeName
     * @param cardBrand
     * @param email
     * @param regionIso
     * @param amount
     * @param cardBrandCode
     * @param transactionStatus
     * @param cardType
     * @param message
     * @param processor
     * @param transactionId
     * @param transactionType
     * @param phoneNumber
     * @param lastDigits
     * @param messageCode
     * @param optionalParameter2
     * @param optionalParameter1
     * @param optionalParameter4
     * @param statusCode
     * @param optionalParameter3
     */
    public idclient(String email, String cardType, String clientUserId, String processor, String bin, String lastDigits, String deferredCode, String deferredMessage, boolean deferred, String cardBrandCode, String cardBrand, int amount, String clientTransactionId, String phoneNumber, int statusCode, String transactionStatus, String authorizationCode, String message, int messageCode, int transactionId, String document, String currency, String optionalParameter1, String optionalParameter2, String optionalParameter3, String optionalParameter4, String storeName, String date, String regionIso, String transactionType, String recap) {
        super();
        this.email = email;
        this.cardType = cardType;
        this.clientUserId = clientUserId;
        this.processor = processor;
        this.bin = bin;
        this.lastDigits = lastDigits;
        this.deferredCode = deferredCode;
        this.deferredMessage = deferredMessage;
        this.deferred = deferred;
        this.cardBrandCode = cardBrandCode;
        this.cardBrand = cardBrand;
        this.amount = amount;
        this.clientTransactionId = clientTransactionId;
        this.phoneNumber = phoneNumber;
        this.statusCode = statusCode;
        this.transactionStatus = transactionStatus;
        this.authorizationCode = authorizationCode;
        this.message = message;
        this.messageCode = messageCode;
        this.transactionId = transactionId;
        this.document = document;
        this.currency = currency;
        this.optionalParameter1 = optionalParameter1;
        this.optionalParameter2 = optionalParameter2;
        this.optionalParameter3 = optionalParameter3;
        this.optionalParameter4 = optionalParameter4;
        this.storeName = storeName;
        this.date = date;
        this.regionIso = regionIso;
        this.transactionType = transactionType;
        this.recap = recap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(String clientUserId) {
        this.clientUserId = clientUserId;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    public String getDeferredCode() {
        return deferredCode;
    }

    public void setDeferredCode(String deferredCode) {
        this.deferredCode = deferredCode;
    }

    public String getDeferredMessage() {
        return deferredMessage;
    }

    public void setDeferredMessage(String deferredMessage) {
        this.deferredMessage = deferredMessage;
    }

    public boolean isDeferred() {
        return deferred;
    }

    public void setDeferred(boolean deferred) {
        this.deferred = deferred;
    }

    public String getCardBrandCode() {
        return cardBrandCode;
    }

    public void setCardBrandCode(String cardBrandCode) {
        this.cardBrandCode = cardBrandCode;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getClientTransactionId() {
        return clientTransactionId;
    }

    public void setClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOptionalParameter1() {
        return optionalParameter1;
    }

    public void setOptionalParameter1(String optionalParameter1) {
        this.optionalParameter1 = optionalParameter1;
    }

    public String getOptionalParameter2() {
        return optionalParameter2;
    }

    public void setOptionalParameter2(String optionalParameter2) {
        this.optionalParameter2 = optionalParameter2;
    }

    public String getOptionalParameter3() {
        return optionalParameter3;
    }

    public void setOptionalParameter3(String optionalParameter3) {
        this.optionalParameter3 = optionalParameter3;
    }

    public String getOptionalParameter4() {
        return optionalParameter4;
    }

    public void setOptionalParameter4(String optionalParameter4) {
        this.optionalParameter4 = optionalParameter4;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegionIso() {
        return regionIso;
    }

    public void setRegionIso(String regionIso) {
        this.regionIso = regionIso;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getRecap() {
        return recap;
    }

    public void setRecap(String recap) {
        this.recap = recap;
    }

}