package com.mobiversal.practica.proiectpractica;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobiversal.practica.proiectpractica.adapters.ViewPagerAdapterMain;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
 private ViewPagerAdapterMain viewPagerAdapterMain;
    private static final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private FirebaseAuth mAuth;
    private ViewPager viewPager;
    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

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
    public void gotoChatRoom(View view){
        Intent intent = new Intent (this, ChatDialogActivity.class);
        ImageButton btn = (ImageButton) findViewById(R.id.imageButton2) ;
        startActivity(intent);
    }
 public void ViewProfil(View view){
        Intent intent = new Intent(this, ViewProfill.class);
        startActivity(intent);
 }




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
