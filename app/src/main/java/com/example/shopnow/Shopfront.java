package com.example.shopnow;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class Shopfront extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

private FrameLayout frameLayout;

TextView Username;

int[] images={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopfront);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        Username=view.findViewById(R.id.user_id1);
        String UserName=getIntent().getStringExtra("username");
        Username.setText(UserName);

        frameLayout=findViewById(R.id.main_framelayout);
        setfragment(new HomeFragment());
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shopfront, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();



         if (id == R.id.action_cart) {

            startActivity(new Intent(Shopfront.this, CartListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_item1) {
            Intent intent=new Intent(Shopfront.this,Shopfront.class);
            startActivity(intent);

        } else if (id == R.id.nav_item2) {
            Intent intent=new Intent(Shopfront.this,Electronics.class);
            startActivity(intent);


        } else if (id == R.id.nav_item3) {
            Intent intent=new Intent(Shopfront.this,LifeStyles.class);
            startActivity(intent);

        } else if (id == R.id.nav_item4) {
            Intent intent=new Intent(Shopfront.this,Mobiles.class);
            startActivity(intent);

        } else if (id == R.id.nav_item5) {
            Intent intent=new Intent(Shopfront.this,Laptops.class);
            startActivity(intent);


        } else if (id == R.id.nav_item6) {
            Intent intent=new Intent(Shopfront.this,Books.class);
            startActivity(intent);


        }
        else if (id == R.id.my_cart) {
            Intent intent=new Intent(Shopfront.this,CartListActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.logout) {
            Intent intent=new Intent(Shopfront.this,login.class);
            startActivity(intent);
            finishAffinity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setfragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}
