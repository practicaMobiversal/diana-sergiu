package com.mobiversal.practica.proiectpractica;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.sql.Ref;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lenovo on 17.07.2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<Users> usersList;
    Context context;
    UsersAdapter(List<Users> usersList){
        this.usersList = usersList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.users_single_layout, parent, false );
        return new MyViewHolder( itemView );

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Users users = usersList.get( position );
        holder.name.setText( users.getDisplayName() );
        holder.status.setText( users.getStatus() );
        holder.image.setImageResource( R.drawable.default_avatar );

        if (!holder.image.equals( "default" )) {

            Picasso.with( holder.image.getContext() ).load( users.getThumb_image() ).placeholder( R.drawable.default_avatar ).into( holder.image );
        }

        Log.i("UsersProfil", "Populate view holder " + position);
        View imageView = holder.itemView.findViewById(R.id.user_single_image);
        imageView.setTag(users.getId());
        holder.name.setText( users.getDisplayName() );

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, status;
        public CircleImageView image;

        public MyViewHolder(View view) {
            super( view );
            name = (TextView) view.findViewById( R.id.user_single_namr );
            status = (TextView) view.findViewById( R.id.user_single_status );
            image = (CircleImageView) view.findViewById( R.id.user_single_image );
        }
    }
}

