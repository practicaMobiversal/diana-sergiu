package com.mobiversal.practica.proiectpractica;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobiversal.practica.proiectpractica.adapters.ViewPagerAdapterMain;
import com.mobiversal.practica.proiectpractica.fragments.ConversationFragments;
import com.mobiversal.practica.proiectpractica.fragments.GroupFragments;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
private  static final String TAG="MainActivity";
private ViewPagerAdapterMain viewPagerAdapterMain;
    private ViewPager viewPager;
    private TabLayout tabLayout;private FirebaseAuth mAuth;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG , "onCreate");
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message").child("2");
        DatabaseReference newusers = database.getReference("users").child("222005555");

        Log.d("App", newusers.getKey());
        newusers.child("profil").child("nume").setValue("ana");

        DatabaseReference groupspublic = database.getReference("groups").push();
        groupspublic.child("public").child("nume").setValue("Oradea - Cluj");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById( R.id.viewpager );
        viewPagerAdapterMain = new ViewPagerAdapterMain( getSupportFragmentManager() );

        viewPager.setAdapter( viewPagerAdapterMain );

        tabLayout = (TabLayout) findViewById( R.id.tab_layout );
        tabLayout.setupWithViewPager( viewPager );

        //Intent intent = new Intent (this, SecondActivity.class);
        //startActivity(intent);

        //populateViews();

    }


//    private void populateViews(){
//        Fragment[] fragments = new Fragment[2];
//        fragments[0] = new GroupFragments();
//        fragments[1] = new ConversationFragments();
//
//        ViewPagerAdapterMain adapter = new ViewPagerAdapterMain(getSupportFragmentManager(),fragments );
//        viewPager.setAdapter(adapter);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_main,menu );
        return super.onCreateOptionsMenu( menu );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            Intent intent = new Intent(this, CreateGroup.class);
            startActivity(intent);
        }

        if (id== R.id.remove){
            Intent intent = new Intent( this, ViewProfill.class);
            startActivity( intent );

        }
        return super.onOptionsItemSelected(item);
    }



    public void CreateNewGroup(View view){
        Intent intent = new Intent(this, CreateGroup.class);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton) ;

        startActivity(intent);
    }

 public void ViewProfil(View view){
        Intent intent = new Intent(this, ViewProfill.class);
        startActivity(intent);
 }    @Override




    protected void onStart() {
        super.onStart();
        Log.d(TAG , "onStart");
        FirebaseUser user =mAuth.getCurrentUser();

        if(user == null){
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG , "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG , "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG , "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG , "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG , "onDestroy");
    }




}
