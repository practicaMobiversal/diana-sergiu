package com.mobiversal.practica.proiectpractica;

/**
 * Created by user on 7/11/2017.
 */

public class User {

    private String uuid;
    private String displayName;
    private String displayPhone;


    public User(String displayName, String displayPhone){

        this.displayName = displayName;
        this.displayPhone = displayPhone;

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
