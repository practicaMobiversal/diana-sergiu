package com.mobiversal.practica.proiectpractica;

import android.media.Image;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class GroupAdapter {

    private String title, nr_util;
    ImageButton imageButton;
    ImageView imageView;

    public GroupAdapter() {
    }

    public GroupAdapter(String title, String nr_util,ImageButton imageButton, ImageView imageView) {
        this.title = title;
        this.nr_util=nr_util;
        this.imageButton=imageButton;
        this.imageView=imageView;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getNr_util() {
        return nr_util;
    }
    public void setNr_util(String numar){
        this.nr_util=numar;
}

}
