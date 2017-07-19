package com.mobiversal.practica.proiectpractica;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<ChatMessage> {
    public MessageAdapter(Context context, int resource, List<ChatMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.chatbubble, parent, false);

        ChatMessage message = getItem(position);

        TextView msg = (TextView) convertView.findViewById(R.id.message_text);
        msg.setText(message.getText());



        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);








       // boolean isPhoto = message.getPhotoUrl() != null;

//        if (isPhoto) {
//            messageTextView.setVisibility(View.GONE);
//            photoImageView.setVisibility(View.VISIBLE);
//            Glide.with(photoImageView.getContext())
//                    .load(message.getPhotoUrl())
//                    .into(photoImageView);
//        } else {
//            messageTextView.setVisibility(View.VISIBLE);
//            photoImageView.setVisibility(View.GONE);
//            messageTextView.setText(message.getText());
//        }

        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.bubble_layout);
        LinearLayout parentLayout = (LinearLayout) convertView.findViewById(R.id.bubble_layout_parent);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) parentLayout.getLayoutParams();

        if (message.isMine) {
            Utils.logD("This is my mesasge");

            layout.setBackgroundResource(R.drawable.bubble_in);

            lp.gravity = Gravity.RIGHT;
        }
        // If not mine then align to left
        else {
            Utils.logD("This should be the another message");

            layout.setBackgroundResource(R.drawable.bubble_out);
            lp.gravity = Gravity.LEFT;
        }

        msg.setTextColor(Color.BLACK);

        return convertView;
    }
}