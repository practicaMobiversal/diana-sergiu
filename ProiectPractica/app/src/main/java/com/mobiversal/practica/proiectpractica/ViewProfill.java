package com.mobiversal.practica.proiectpractica;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

public class ViewProfill extends AppCompatActivity {
    private DatabaseReference pUserReference;
    private FirebaseUser mCurrentUser;

    private TextView mName;
    private TextView mStatus;
    private CircleImageView mDisplayImage;

    private Button mStatusButton;
    private Button mImageButton;

    private static final int GALLERY_PICK=1;

    private StorageReference mImageStorage;
    private ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profill);
        Intent intent = getIntent();

        mDisplayImage = (CircleImageView) findViewById( R.id.circleImageView );
        mName = (TextView) findViewById( R.id.profil_name );
        mStatus = (TextView) findViewById( R.id.profil_status ) ;
        mImageButton =(Button) findViewById( R.id.profil_b_img ) ;
        mStatusButton = (Button) findViewById( R.id.button4 );

        mImageStorage  = FirebaseStorage.getInstance().getReference();



        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        assert mCurrentUser != null;
        String current_user = mCurrentUser.getUid();

        pUserReference = FirebaseDatabase.getInstance().getReference().child( "users").child( current_user );

        pUserReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child( "displayName" ).getValue().toString();
                String status = dataSnapshot.child( "status" ).getValue().toString();
                String image = dataSnapshot.child( "image" ).getValue().toString();
                String thumb_image = dataSnapshot.child( "thumb_image" ).toString();

                mName.setText( name );
                mStatus.setText( status );

                if (!image.equals( "default" )){

                Picasso.with( ViewProfill.this ).load( image ).placeholder( R.drawable.default_avatar ).into( mDisplayImage );}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );

        mStatusButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent status_intent  = new Intent( ViewProfill.this, StatusActivity.class );
                startActivity( status_intent );

            }
        } );


        mImageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(  );
                galleryIntent.setType( "image/*" );
                galleryIntent.setAction( Intent.ACTION_GET_CONTENT );

               startActivityForResult( Intent.createChooser( galleryIntent, "SELECT IMAGE" ),GALLERY_PICK );


//                CropImage.activity()
//                        .setGuidelines( CropImageView.Guidelines.ON)
//                        .start(ViewProfill.this);
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );


        if (requestCode==GALLERY_PICK && resultCode==RESULT_OK){

            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio( 1,1 )
                    .setMinCropWindowSize( 500, 500 )
                    .start(this);


            //Toast.makeText( ViewProfill.this, imageUri, Toast.LENGTH_LONG).show();
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {


                mProgressDialog = new ProgressDialog( ViewProfill.this );
                mProgressDialog.setTitle( "Modificare imagine" );
                mProgressDialog.setMessage( "Va rugam asteptati" );
                mProgressDialog.setCanceledOnTouchOutside( false );
                mProgressDialog.show();

                Uri resultUri = result.getUri();

                File thumb_filePath = new File( resultUri.getPath() );

                String current_user_id = mCurrentUser.getUid();

                Bitmap thumb_bitmat  = new Compressor(this).compressToBitmap(thumb_filePath);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumb_bitmat.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                final byte[] thumb_byte = baos.toByteArray();

                StorageReference filepath = mImageStorage.child( "profil_images" ).child( current_user_id +  ".jpg" );
                final StorageReference thumb_filepath = mImageStorage.child( "profil_images" ).child( "thumb" ).child( current_user_id + ".jpg" );


                filepath.putFile( resultUri ).addOnCompleteListener( new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()){

                            final String download_url = task.getResult().getDownloadUrl().toString();

                            UploadTask uploadTask = thumb_filepath.putBytes(thumb_byte);
                            uploadTask.addOnCompleteListener( new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> thumb_task) {

                                    String thumb_downloadUrl = thumb_task.getResult().getDownloadUrl().toString();

                                    if (thumb_task.isSuccessful()){

                                        Map update_hashMap = new HashMap() ;
                                        update_hashMap.put( "image", download_url );
                                        update_hashMap.put( "thumb_image", thumb_downloadUrl );

                                        pUserReference.updateChildren( update_hashMap ).addOnCompleteListener( new OnCompleteListener<Void>() {
                             @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){

                                        mProgressDialog.dismiss();
                                        Toast.makeText( ViewProfill.this, "Incarcare cu succes", Toast.LENGTH_LONG ).show();

                                    }
                                }
                            } );

                                    }else {
                                        Toast.makeText( ViewProfill.this, "Eroare la incarcarea thumb", Toast.LENGTH_LONG ).show();
                                        mProgressDialog.dismiss();

                                    }

                                }
                            } );

                        }else {
                            Toast.makeText( ViewProfill.this, "Eroare la incarcare", Toast.LENGTH_LONG ).show();
                            mProgressDialog.dismiss();
                        }
                    }
                } );

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public static String random(){
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLenght = generator.nextInt( 10 );
        char tempChar;
        for (int i = 0; i<randomLenght; i++){
            tempChar = (char) (generator.nextInt( 96 ) + 32);
            randomStringBuilder.append( tempChar );
        }

        return randomStringBuilder.toString();
    }
}
