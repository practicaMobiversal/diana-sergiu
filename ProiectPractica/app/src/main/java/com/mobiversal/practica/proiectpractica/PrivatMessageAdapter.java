package com.mobiversal.practica.proiectpractica;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Lenovo on 20.07.2017.
 */

public class PrivatMessageAdapter extends ArrayAdapter<ChatMessage> {
    private LayoutInflater mInflater;

    public PrivatMessageAdapter(@NonNull Context context, @LayoutRes int resource) {
        super( context, resource );
    }


    static class ViewHolder {
        private TextView nameTextView;
        private TextView surnameTextView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.chatbubble, null);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.message_text);
            holder.surnameTextView = (TextView) convertView.findViewById(R.id.message_user);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ChatMessage message = getItem(position);

        holder.nameTextView.setText(message.getName());
        holder.surnameTextView.setText(message.getText());
        //holder.personImageView.setImageBitmap(person.getImage());

        return convertView;
    }
}

