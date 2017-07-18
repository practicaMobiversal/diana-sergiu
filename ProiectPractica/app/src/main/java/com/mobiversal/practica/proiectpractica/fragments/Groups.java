package com.mobiversal.practica.proiectpractica.fragments;

/**
 * Created by Lenovo on 14.07.2017.
 */

public class Groups {

    private String publicGroupName, nr_util, imageButton, imageView;

    public Groups() {
    }

    public Groups(String publicGroupName, String nr_util,String imageButton, String imageView) {
        this.publicGroupName = publicGroupName;
        this.nr_util=nr_util;
        this.imageButton=imageButton;
        this.imageView=imageView;

    }

    public String getpublicGroupName() {
        return publicGroupName;
    }

    public void setPublicGroupName(String publicGroupName) {
        this.publicGroupName = publicGroupName;
    }

    public String getImageButton() {
        return imageButton;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageButton(String imageButton) {
        this.imageButton = imageButton;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getNr_util() {
        return nr_util;
    }
    public void setNr_util(String numar){
        this.nr_util=numar;
    }

}
