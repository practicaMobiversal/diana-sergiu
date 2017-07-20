package com.mobiversal.practica.proiectpractica;

/**
 * Created by Lenovo on 10.07.2017.
 */


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<PublicGroup> groupsList;
    private Button btn;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, nr_util;
        public ImageButton imageButton;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title );
            nr_util = (TextView) view.findViewById(R.id.genre);
            imageButton = (ImageButton) view.findViewById(R.id.imageButton2);
            imageView  = (ImageView) view.findViewById( R.id.imageView8 );



        }
    }


    public MyAdapter(List<PublicGroup> groupsList) {
        this.groupsList = groupsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_list_row, parent, false);

            //imageButton2 =

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PublicGroup group = groupsList.get( position );
        holder.title.setText(group.getPublicGroupName());
        holder.imageView.setImageResource( R.drawable.publicgroup );

        Log.i("GroupFragments", "Populate view holder " + position);
        View imageView = holder.itemView.findViewById(R.id.imageButton2);
        imageView.setTag(group.getPublicGroupName());
        holder.title.setText(group.getPublicGroupName());

    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }
}
