package com.mobiversal.practica.proiectpractica;

/**
 * Created by Lenovo on 19.07.2017.
 */

public class PrivatGroup {

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPrivatGroupName() {
        return privatGroupName;
    }

    public void setPrivatGroupName(String privatGroupName) {
        this.privatGroupName = privatGroupName;
    }

    private String privatGroupName;

    public PrivatGroup(){}
    public PrivatGroup(String privatGroupName){
        this.privatGroupName = privatGroupName;
    }
}