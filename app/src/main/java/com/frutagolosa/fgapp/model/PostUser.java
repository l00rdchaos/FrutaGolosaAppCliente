package com.frutagolosa.fgapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostUser {

    @SerializedName("documentId")
    @Expose
    private String documentId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phoneNumbers")
    @Expose
    private List<String> phoneNumbers = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public PostUser() {
    }

    /**
     *
     * @param lastName
     * @param name
     * @param documentId
     * @param email
     * @param phoneNumbers
     */
    public PostUser(String documentId, String name, String lastName, String email, List<String> phoneNumbers) {
        super();
        this.documentId = documentId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public String toString() {
        return "PostUser{" +
                "documentId='" + documentId + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

}