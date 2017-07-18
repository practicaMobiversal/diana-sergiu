package com.mobiversal.practica.proiectpractica.fragments;

/**
 * Created by Lenovo on 14.07.2017.
 */

public class PublicGroup {
    private String uuid;
    private String publicGroupName;
    private String userName;
    private String phoneNumber;

    public PublicGroup(String publicGroupName, String userName, String phoneNumber)
    {
        this.publicGroupName = publicGroupName;
        this.userName = userName;
        this.phoneNumber = phoneNumber;

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getpublicGroupName() {
        return publicGroupName;
    }

    public void setPublicGroupName(String publicGroupName) {
        this.publicGroupName = publicGroupName;
    }


}
