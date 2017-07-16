package com.mobiversal.practica.proiectpractica;

/**
 * Created by user on 7/12/2017.
 */

public class PublicGroup {
    private String uuid;
    private String publicGroupName;
    private String userName;
    private String phoneNumber;

    public PublicGroup(){

    }
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

    public String getPublicGroupName() {
        return publicGroupName;
    }

    public void setPublicGroupName(String PublicGroupName) {
        this.publicGroupName = PublicGroupName;
    }

}
