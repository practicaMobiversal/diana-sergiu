package com.mobiversal.practica.proiectpractica;

/**
 * Created by Lenovo on 13.07.2017.
 */

public class Users {

    private String id;
    public String displayName;
    public String image;
    public String status;
    public String thumb_image;

    public String getThumb_image() {
        return thumb_image;
    }

    public void setThumb_image(String thumb_image) {
        this.thumb_image = thumb_image;
    }

    public Users(){

    }

    public Users(String id, String displayName, String image, String status, String thumb_image) {
        this.id = id;
        this.displayName = displayName;
        this.image = image;
        this.status = status;
        this.thumb_image = thumb_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
