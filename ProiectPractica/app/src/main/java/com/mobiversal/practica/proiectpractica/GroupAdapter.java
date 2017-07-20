package com.mobiversal.practica.proiectpractica;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiversal.practica.proiectpractica.fragments.*;

import java.util.List;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {

    private List<PrivatGroup> groupsPList;
    private Button btn;
     public GroupAdapter(List<PrivatGroup> groupsPList) {
        this.groupsPList = groupsPList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.group_list_vieww, parent, false );

        return new GroupAdapter.MyViewHolder( itemView );
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PrivatGroup group = groupsPList.get( position );
        holder.groupNameView.setText( group.getPrivatGroupName() );


        Log.i( "ConversationFragments", "Populate view holder " + position );
        View imageView = holder.itemView.findViewById( R.id.group_privat_title );
        imageView.setTag( group.getPrivatGroupName() );
        holder.groupNameView.setText( group.getPrivatGroupName() );

    }

    @Override
    public int getItemCount() {
        return groupsPList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView groupNameView;

        public MyViewHolder(View itemView) {
            super( itemView );
            groupNameView = (TextView) itemView.findViewById( R.id.group_privat_title );

        }

        public void setName(String privatGroupName) {
            groupNameView.setText( privatGroupName );
        }

    }
}
