package com.mobiversal.practica.proiectpractica;

/**
 * Created by Lenovo on 08.07.2017.
 */

public class ChatMessage {


    private String text;
    private String name;
    private String photoUrl;
    private String date;
    private String UserId;


    public ChatMessage() {
    }

    public ChatMessage(String text, String name, String photoUrl, String date, String UserId) {

        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.date = date;
        this.UserId = UserId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}