package com.mobiversal.practica.proiectpractica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatDialogActivity extends AppCompatActivity {

    private static final String TAG = "ChatDialogActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;
    private TextView mCurrentDate;

    private String mUsername;



    public String mUsername;
    public String mDate;

    private DatabaseReference mMessagesDatabaseReference;
    private DatabaseReference mUsernameDatabaseReference;

    private ChildEventListener mChildEventListener;

    private FirebaseUser mCurentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatmessage);

        mUsername = ANONYMOUS;
        mCurentUser = FirebaseAuth.getInstance().getCurrentUser();
        // mUsername = ANONYMOUS;

        String groupId = getIntent().getExtras().getString("groupId");
        assert mCurentUser != null;
        final String curtentId = mCurentUser.getUid();
        Utils.logD("mCurrentUser string=" + mCurentUser.getUid());

        String groupId = getIntent().getExtras().getString("groupId");

        FirebaseDatabase mFireBaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFireBaseDatabase.getReference()
                .child("Messages").child(groupId);
        // mMessagesDatabaseReference.keepSynced(true);
        final FirebaseDatabase mFireBaseDatabase = FirebaseDatabase.getInstance();
        mUsernameDatabaseReference = mFireBaseDatabase.getReference().child("users").child(curtentId);

        mMessagesDatabaseReference = mFireBaseDatabase.getReference()
                .child("Messages").child(groupId);

        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);


        // Initialize message ListView and its adapter
        List<ChatMessage> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);
        mMessageAdapter.setNotifyOnChange(true);
        mMessageListView.setAdapter(mMessageAdapter);





        mMessagesDatabaseReference.child("userId").setValue(mCurentUser);


        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Fire an intent to show an image picker
            }
        });

        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click

                ChatMessage chatMessage = new ChatMessage(mMessageEditText.getText().toString(), mUsername, null);
                mMessagesDatabaseReference.push().setValue(chatMessage);


                // Clear input box
                mMessageEditText.setText("");
            }
        });

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                mMessageAdapter.add(chatMessage);
                mMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
package com.mobiversal.practica.proiectpractica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatDialogActivity extends AppCompatActivity {

    private static final String TAG = "ChatDialogActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;


    private String mUsername;

    private DatabaseReference mMessagesDatabaseReference;
    private DatabaseReference mUsernameDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatmessage);


       // mUsername = ANONYMOUS;

        String groupId = getIntent().getExtras().getString("groupId");

        final FirebaseDatabase mFireBaseDatabase = FirebaseDatabase.getInstance();
        mUsernameDatabaseReference = mFireBaseDatabase.getReference().child("users").child("displayName");

        mMessagesDatabaseReference = mFireBaseDatabase.getReference()
                .child("Messages").child(groupId);
        // mMessagesDatabaseReference.keepSynced(true);










        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);
//        mCurrentDate = (TextView) findViewById(R.id.message_date);


        // Initialize message ListView and its adapter
        List<ChatMessage> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);
        mMessageAdapter.setNotifyOnChange(true);
        mMessageListView.setAdapter(mMessageAdapter);



        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Fire an intent to show an image picker

            }
        });

        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
        mSendButton.setEnabled(false);

        mUsernameDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click
            public void onDataChange(DataSnapshot dataSnapshot) {

//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("users");
//                myRef.orderByChild("name").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//                            Log.d(TAG, "PARENT: "+ childDataSnapshot.getKey());
//                            Log.d(TAG,""+ childDataSnapshot.child("name").getValue());
//                        }
                mUsernameDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                mSendButton.setEnabled(true);

                mUsername = (String) dataSnapshot.child("displayName").getValue();

//                long date = System.currentTimeMillis();
//                SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
//                String dateString = sdf.format(date);
//                mCurrentDate.setText(dateString);


                // Send button sends a message and clears the EditText
                mSendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String userName = (String) dataSnapshot.getValue(String.class);
                        ChatMessage chatMessage = new ChatMessage(mMessageEditText.getText().toString(), userName, null, true);
                        mMessagesDatabaseReference.push().setValue(chatMessage);
                    }
                        ChatMessage chatMessage = new ChatMessage(mMessageEditText.getText().toString(), mUsername, null, null, mCurentUser.getUid());
                        mMessagesDatabaseReference.push().setValue(chatMessage);

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        mMessageEditText.setText("");









                    }
                });
//                ChatMessage chatMessage = new ChatMessage(mMessageEditText.getText().toString(), userName, null, true);
//                mMessagesDatabaseReference.push().setValue(chatMessage);
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


                // Clear input box
                mMessageEditText.setText("");

            }
        });


                });





        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                mMessageAdapter.add(chatMessage);
                mMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
}

