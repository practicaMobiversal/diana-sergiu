package com.mobiversal.practica.proiectpractica;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;

import static android.R.attr.bitmap;

/**
 * Created by Lenovo on 19.07.2017.
 */

public class ChatPrivat extends AppCompatActivity {

    private static final String TAG = "ChatPrivat";

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
    private DatabaseReference mMessages;
    private DatabaseReference mUsernameDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseUser mUserCurrent;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    private static final int GALLERY_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chatmessage );

        // String user_is = getIntent().getStringExtra( "user_is" );


        // mUsername = ANONYMOUS;

        final String privatId = getIntent().getExtras().getString( "privatId" );

        mUserCurrent = FirebaseAuth.getInstance().getCurrentUser();
        assert mUserCurrent != null;
        String current_user = mUserCurrent.getUid();
        mAuth = FirebaseAuth.getInstance();
        mUsername = mAuth.getCurrentUser().getUid();

        mUsernameDatabaseReference = FirebaseDatabase.getInstance().getReference().child( "users" ).child( current_user );

        mRootRef = FirebaseDatabase.getInstance().getReference();


        final FirebaseDatabase mFireBaseDatabase = FirebaseDatabase.getInstance();
        // mUsernameDatabaseReference = mFireBaseDatabase.getReference().child("users").child("displayName");

        mMessagesDatabaseReference = mFireBaseDatabase.getReference()
                .child( "Messages" ).child( privatId );

        //mMessages = mFireBaseDatabase.getReference().child( "Messages" ).child( user_is);
        // mMessagesDatabaseReference.keepSynced(true);

        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById( R.id.progressBar );
        mMessageListView = (ListView) findViewById( R.id.messageListView );
        mPhotoPickerButton = (ImageButton) findViewById( R.id.photoPickerButton );
        mMessageEditText = (EditText) findViewById( R.id.messageEditText );
        mSendButton = (Button) findViewById( R.id.sendButton );

        // Initialize message ListView and its adapter
        List<ChatMessage> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter( this, R.layout.item_message, friendlyMessages );
        mMessageAdapter.setNotifyOnChange( true );
        mMessageListView.setAdapter( mMessageAdapter );


        // Initialize progress bar
        mProgressBar.setVisibility( ProgressBar.INVISIBLE );

        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Fire an intent to show an image picker
            }
        } );

        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled( true );
                } else {
                    mSendButton.setEnabled( false );
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        } );
        mMessageEditText.setFilters( new InputFilter[]{new InputFilter.LengthFilter( DEFAULT_MSG_LENGTH_LIMIT )} );


        mPhotoPickerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType( "image/*" );
                galleryIntent.setAction( Intent.ACTION_GET_CONTENT );

                startActivityForResult( Intent.createChooser( galleryIntent, "SELECT IMAGE" ), GALLERY_PICK );
//                CropImage.activity()
//                        .setGuidelines( CropImageView.Guidelines.ON)
//                        .start(ViewProfill.this);
            }
        } );


        mSendButton.setEnabled( false );
        mUsernameDatabaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mSendButton.setEnabled( true );

                mUsername = (String) dataSnapshot.child( "displayName" ).getValue();

                // Send button sends a message and clears the EditText
                mSendButton.setOnClickListener( new View.OnClickListener()

                {
                    @Override
                    public void onClick(View view) {
                        // TODO: Send messages on click
                        ChatMessage chatMessage = new ChatMessage( mMessageEditText.getText().toString(), mUsername, null, true );
                        mMessagesDatabaseReference.push().setValue( chatMessage );

                        mMessageEditText.setText( "" );
                    }


                } );
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                ChatMessage chatMessage = dataSnapshot.getValue( ChatMessage.class );
                mMessageAdapter.add( chatMessage );
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

        mMessagesDatabaseReference.addChildEventListener( mChildEventListener );
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult( requestCode, resultCode, data );
//
//
//        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
//            Uri imageUri = data.getData();
//            mMessageListView
//           // mSendButton.setEnabled( true );
            //ChatMessage chatMessage = new ChatMessage( imageUri.toString(), mUsername, null, true );
//            mMessagesDatabaseReference.push().setValue( chatMessage );
//            mMessageEditText.setText( "" );
//            Toast.makeText( ChatPrivat.this, "Succes", Toast.LENGTH_LONG ).show();
//
//        }
//    }
}



