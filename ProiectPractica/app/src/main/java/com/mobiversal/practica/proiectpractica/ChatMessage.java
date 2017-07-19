package com.mobiversal.practica.proiectpractica;

/**
 * Created by Lenovo on 08.07.2017.
 */

public class ChatMessage {

    boolean isMine;
    private String text;
    private String name;
    private String photoUrl;


    public ChatMessage() {
    }

    public ChatMessage(String text, String name, String photoUrl, boolean isMine) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.isMine = isMine;
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

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    };
}