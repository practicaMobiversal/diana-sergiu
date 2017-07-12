package com.mobiversal.practica.proiectpractica;

/**
 * Created by user on 7/11/2017.
 */

public class User {

    private String display_name;
    private String display_phone;

    public User(String name, String phone_number){

        this.display_name = name;
        this.display_phone = phone_number;

    }


    public String getDisplay_name() {
        return display_name;
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    public void setName(String name) {
        this.display_name = name ;
    }

    public void setPhone(String phone_number){
        this.display_phone = phone_number;
    }

}
