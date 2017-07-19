package com.mobiversal.practica.proiectpractica.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mobiversal.practica.proiectpractica.R;

/**
 * Created by user on 7/14/2017.
 */

public class GroupsViewHolder extends RecyclerView.ViewHolder{

    TextView groupNameView;

    public GroupsViewHolder(View itemView){
        super(itemView);
        groupNameView = (TextView) itemView.findViewById(R.id.title);

    }
    public void setName(String publicGroupName){
        groupNameView.setText(publicGroupName);
    }

}




