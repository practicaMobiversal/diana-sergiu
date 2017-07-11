package com.mobiversal.practica.proiectpractica;

import android.media.Image;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class GroupAdapter {

    private String title;
    private int nr_util;
    ImageButton imageButton;
    ImageView imageView;

    public GroupAdapter() {
    }

    public GroupAdapter(String title, int nr_util) {
        this.title = title;
        this.nr_util=nr_util;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getNr_util() {
        return nr_util;
    }
    public void setNr_util(int numar){
        this.nr_util=numar;
}
}
