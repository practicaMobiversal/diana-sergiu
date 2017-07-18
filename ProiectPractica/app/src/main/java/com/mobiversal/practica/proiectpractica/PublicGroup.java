package com.mobiversal.practica.proiectpractica;

/**
 * Created by user on 7/12/2017.
 */

public class PublicGroup {
    private String publicGroupName;

    public PublicGroup(){

    }
    public PublicGroup(String publicGroupName)
    {
        this.publicGroupName = publicGroupName;

    }

    public String getPublicGroupName() {
        return publicGroupName;
    }

    public void setPublicGroupName(String publicGroupName) {
        this.publicGroupName = publicGroupName;
    }

    @Override
    public String toString() {
        return "PublicGroup{" +
                "publicGroupName='" + publicGroupName + '\'' +
                '}';
    }
}
