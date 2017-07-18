package com.mobiversal.practica.proiectpractica;

import android.media.Image;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class GroupAdapter {

    private String publicGroupName, nr_util, imageButton, imageView;

    public GroupAdapter() {
    }

    public GroupAdapter(String publicGroupName, String nr_util,String imageButton, String imageView) {
        this.publicGroupName = publicGroupName;
        this.nr_util=nr_util;
        this.imageButton=imageButton;
        this.imageView=imageView;

    }

    public String getPublicGroupName() {
        return publicGroupName;
    }

    public void setPublicGroupName(String name) {
        this.publicGroupName = name;
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
