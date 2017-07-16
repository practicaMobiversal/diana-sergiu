package com.mobiversal.practica.proiectpractica;

/**
 * Created by user on 7/11/2017.
 */

public class User {

    private String uuid;
    private String displayName;
    private String displayPhone;
    private String status;
    private String image;
    private String thumb_image;

    public User(){

    }

    public User(String displayName, String displayPhone, String status,String image, String thumb_image){

        this.displayName = displayName;
        this.displayPhone = displayPhone;
        this.status = status;
        this.thumb_image = thumb_image;
        this.image = image;

    }

    public String getStatus() {
        return status;
    }

    public String getThumb_image() {
        return thumb_image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setThumb_image(String thumb_image) {
        this.thumb_image = thumb_image;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayPhone() {
        return displayPhone;
    }

    public void setDisplayPhone(String displayPhone) {
        this.displayPhone = displayPhone;
    }
}
