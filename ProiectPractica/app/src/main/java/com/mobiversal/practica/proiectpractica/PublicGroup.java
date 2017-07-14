package com.mobiversal.practica.proiectpractica;

/**
 * Created by user on 7/12/2017.
 */

public class PublicGroup {
    private String uuid;
    private String PublicGroupName;
    private String userName;
    private String phoneNumber;

    public PublicGroup(String PublicGroupName, String userName, String phoneNumber)
    {
        this.PublicGroupName = PublicGroupName;
        this.userName = userName;
        this.phoneNumber = phoneNumber;

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPublicGroupName() {
        return PublicGroupName;
    }

    public void setPublicGroupName(String PublicGroupName) {
        this.PublicGroupName = PublicGroupName;
    }

}
