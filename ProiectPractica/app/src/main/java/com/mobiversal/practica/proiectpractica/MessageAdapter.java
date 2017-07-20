package com.mobiversal.practica.proiectpractica;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import static android.view.Gravity.END;

public class MessageAdapter extends ArrayAdapter<ChatMessage> {
    private DatabaseReference mUsernameDatabaseReference;
    public String mUsername;
    private FirebaseUser mCurentUser;

    public MessageAdapter(Context context, int resource, List<ChatMessage> objects) {
        super(context, resource, objects);
        mCurentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    static class ViewHolder {
        private TextView nameTextView;
        private TextView messageTextView;
        private ImageView personImageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.chatbubble, parent, false);


//        final ChatMessage message = getItem(position);
//        //String sender = message.getName();
//
//        TextView msg = (TextView) convertView.findViewById(R.id.message_text);
//
//        msg.setText(message.getText());
//        Utils.logD("getUserId =" + message.getText());
//
//        TextView name1 = (TextView) convertView.findViewById(R.id.message_user);
//        name1.setText(message.getName());
//
//        TextView mDate = (TextView) convertView.findViewById(R.id.message_date);
//        mDate.setText(message.getDate());
//
//
//        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
//        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
//        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
//
//
//        // mUsername = ANONYMOUS;
//
//        String curtentId = mCurentUser.getUid();
//        String userId = message.getUserId();
//
//        final FirebaseDatabase mFireBaseDatabase = FirebaseDatabase.getInstance();
//        mUsernameDatabaseReference = mFireBaseDatabase.getReference().child("users").child(curtentId);


            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.message_user);
            holder.messageTextView = (TextView) convertView.findViewById(R.id.message_text);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        ChatMessage message = getItem(position);

        holder.nameTextView.setText(message.getName());
        holder.messageTextView.setText(message.getText());
        //holder.personImageView.setImageBitmap(person.getImage());


        String curtentId = mCurentUser.getUid();
        String userId = message.getUserId();


        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.bubble_layout);
        LinearLayout parentLayout = (LinearLayout) convertView.findViewById(R.id.bubble_layout_parent);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) parentLayout.getLayoutParams();

        Utils.logD("getUserId =" + message.getUserId() + "currentId = " + curtentId);

        if (curtentId.equals(userId)) {

            // This is my message

            Utils.logD("This is my mesasge");

            layout.setBackgroundResource(R.drawable.bubble_in);
            lp.gravity = Gravity.RIGHT | END;

        } else {
            // Other user's message

            Utils.logD("This should be the another message");

            layout.setBackgroundResource(R.drawable.bubble_out);

            lp.gravity = Gravity.LEFT | END;

        }

        return convertView;
    }
}

//        mUsernameDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                mUsername = (String) dataSnapshot.child("displayName").getValue();
//
//
//                // boolean isPhoto = message.getPhotoUrl() != null;
//            }
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

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

//        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.bubble_layout);
//        LinearLayout parentLayout = (LinearLayout) convertView.findViewById(R.id.bubble_layout_parent);
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) parentLayout.getLayoutParams();
//
//        String sender = message.getName();
//
//        if (sender != null && sender.equals(mUsername)) {
//            Utils.logD("This is my mesasge");
//
//            layout.setBackgroundResource(R.drawable.bubble_in);
//            lp.gravity = Gravity.RIGHT | END;
//
//        }
//        // If not mine then align to left
//        else {
//            Utils.logD("This should be the another message");
//
//            layout.setBackgroundResource(R.drawable.bubble_out);
//
//            lp.gravity = Gravity.LEFT | END;
//        }

//        message.setTextColor(Color.BLACK);
//
//        return convertView;
//    }
//}
